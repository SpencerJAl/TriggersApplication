package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Database.WeatherTable;
import com.example.contextualtrigger.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.util.List;

public class GoodWeatherTrigger implements TriggerTemplate {

    Context MainContext;
    TriggerDatabase triggerDatabase;

    public GoodWeatherTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData() {
        triggerDatabase = TriggerDatabase.getInstance(MainContext);
        List<WeatherTable> weather = triggerDatabase.weatherDao().getWeather();

        System.out.println("Printed in trigger: " + weather.get(1).getCurrentTemp());

    }

    @Override
    public void checkTriggerData() {

    }

    @Override
    public void informNotificationManager() {
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("4", "Perfect Weather Conditions", "The weather is excellent for a walk lets hit that goal of x.");

    }
}
