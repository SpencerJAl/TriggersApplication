package com.example.contextualtrigger.DataSources;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherAPIinfo extends IntentService {

    private static final String TAG = WeatherAPIinfo.class.getSimpleName();
    public static final String PENDING_RESULT_EXTRA = "pending_result";



    public WeatherAPIinfo() {
        super(TAG);

    }

    /**
     * @param intent
     * @deprecated
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT_EXTRA);





        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?q=Glasgow%2Cuk&lat=55.864239&lon=-4.251806&callback=test&id=2172797&lang=null&units=imperial&mode=xml")
                .get()
                .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "6e8e440a0emsh02c377a05b16ec2p15b1e8jsn1c98da9bea6d")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response);

    }
}
