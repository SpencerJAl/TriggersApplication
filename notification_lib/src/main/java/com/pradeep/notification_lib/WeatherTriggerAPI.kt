package com.pradeep.notification_lib

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pradeep.notification_lib.NotificationBuilder.Companion.with
import org.json.JSONException
import org.json.JSONObject

class WeatherTriggerAPI(val context: Context) {
    private var cityId = 0

    @Synchronized
    fun fetchLocationIDFromApi(mLat:String, mLong:String) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://www.metaweather.com/api/location/search/?lattlong=$mLat,$mLong"
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val cityInfo = response.getJSONObject(0)
                    cityId = Integer.valueOf(cityInfo.getString("woeid"))
                } catch (E: JSONException) {
                    E.printStackTrace()
                }
                fetchWeatherFromApi()
            }) { }
        queue.add(request)
    }

    @Synchronized
    private fun fetchWeatherFromApi() {
        if (cityId != 0) {
            val queue = Volley.newRequestQueue(context)
            val url = "https://www.metaweather.com/api/location/$cityId"
            val request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {
                        val weather = response.getJSONArray("consolidated_weather")
                        val weatherJSON = weather[0] as JSONObject
                        var desc = weatherJSON.optString("weather_state_name","")
                        var minTemp = weatherJSON.optDouble("min_temp",0.0)
                        var maxTemp = weatherJSON.optDouble("max_temp",0.0)
                        var currentTemp = weatherJSON.optDouble("the_temp",0.0)
                        var humidity = weatherJSON.optInt("humidity",0)
                        var visibility = weatherJSON.optDouble("visibility",0.0)

                        //if there is weather data, check to see if the temp > 10 and is one of the weather conditions.
                        if (currentTemp > 10.0) {
                            if (desc == "Light Cloud" || desc == "Clear" || desc == "Heavy Cloud") {
                                NotificationBuilder.with(context).content {
                                    title = "$desc Weather Conditions with ${currentTemp.toString().substring(0,4)} temp"
                                    text = "The weather is excellent for a walk"
                                }.show()
                            }
                        }
                    } catch (E: JSONException) {
                        E.printStackTrace()
                    }
                }) { }
            queue.add(request)
        } else {
        }
    }
}