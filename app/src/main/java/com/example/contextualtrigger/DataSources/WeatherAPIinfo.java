package com.example.contextualtrigger.DataSources;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Database.WeatherTable;
import com.example.contextualtrigger.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherAPIinfo {

    Context MainContext;
    TriggerDatabase triggerDatabase;

    private static int CITYID = 21125; //ID of the city glasgow

    public WeatherAPIinfo(Context mainContext) {
        MainContext = mainContext;
        if (isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(mainContext);
            String url = "https://www.metaweather.com/api/location/" + CITYID;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray weather = response.getJSONArray("consolidated_weather");

                        JSONObject day1 = (JSONObject) weather.get(0);

                        storeWeatherData(day1, mainContext);
                    } catch (JSONException E) {
                        E.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            queue.add(request);
        }else {

        }
    }


    private void storeWeatherData(JSONObject weatherJSON, Context context){
        triggerDatabase = TriggerDatabase.getInstance(context);
        List<WeatherTable> weather = triggerDatabase.weatherDao().getWeather();


        String desc = "";
        double minTemp = 0.0;
        double maxTemp = 0.0;
        double currentTemp = 0.0;
        int humidity = 0;
        double visibility = 0.0;
        String date = "";

        try {
            desc = weatherJSON.getString("weather_state_name");
            minTemp = weatherJSON.getDouble("min_temp");
            maxTemp = weatherJSON.getDouble("max_temp");
            currentTemp = weatherJSON.getDouble("the_temp");
            humidity = weatherJSON.getInt("humidity");
            visibility = weatherJSON.getDouble("visibility");
            date= weatherJSON.getString("applicable_date");

        }catch (JSONException E){
            E.printStackTrace();
        }

        if(findDate(weather,weatherJSON)){
            triggerDatabase.weatherDao().updateCurrentWeather(desc,minTemp,maxTemp,currentTemp,humidity,visibility,date);
        } else {
            WeatherTable newEntry = new WeatherTable(desc,minTemp,maxTemp,currentTemp,humidity,visibility,date);
            triggerDatabase.weatherDao().insertWeather(newEntry);
        }

    }

    private boolean findDate(List<WeatherTable> weather, JSONObject weatherJSON){
        if(weather.size() == 0){ return true;}
        for(int i = 0; i < weather.size(); i++){
            try {
                if(weather.get(i).getDate().equals(weatherJSON.getString("applicable_date"))){
                    return true;
                }
            }catch (JSONException E){
                E.printStackTrace();
            }
        }
        return false;
    }

    private boolean isOnline(){
        ConnectivityManager connMgr = (ConnectivityManager) MainContext.getSystemService(MainContext.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
