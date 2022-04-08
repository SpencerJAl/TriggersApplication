package com.example.contextualtrigger.DataSources;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherAPIinfo {

    private static int CITYID = 21125;


    public WeatherAPIinfo(Context mainContext) {

        RequestQueue queue = Volley.newRequestQueue(mainContext);
        String url = "https://www.metaweather.com/api/location/" + CITYID;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    JSONArray weather = response.getJSONArray("consolidated_weather");

                    JSONObject day1 = (JSONObject) weather.get(0);

                    String weatherState = day1.getString("weather_state_name");
                    String weatherDate = day1.getString("applicable_date");

                    System.out.println("State:" + weatherState);
                    System.out.println("Date" + weatherDate);

                }catch (JSONException E){
                    E.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }
}
