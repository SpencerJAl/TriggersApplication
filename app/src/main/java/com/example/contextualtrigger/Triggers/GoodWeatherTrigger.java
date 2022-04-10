package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Database.WeatherTable;
import com.example.contextualtrigger.Notifications.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.util.List;

public class GoodWeatherTrigger implements TriggerTemplate {

    Context MainContext;
    TriggerDatabase triggerDatabase;
    List<WeatherTable> currentWeather;

    public GoodWeatherTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData() {
        triggerDatabase = TriggerDatabase.getInstance(MainContext);
        currentWeather = triggerDatabase.weatherDao().getWeather();
        checkTriggerData();
    }

    @Override
    public void checkTriggerData() {
        Double current_Temp = currentWeather.get(0).getCurrentTemp();
        String current_Conditions = currentWeather.get(0).getWeatherDesc();

        System.out.println(current_Conditions);

        if(current_Temp > 10.0){
            if(current_Conditions.equals("Light Cloud") || current_Conditions.equals("Clear")){
                informNotificationManager();
            }
        }
    }

    @Override
    public void informNotificationManager() {
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("4", "Perfect Weather Conditions", "The weather is excellent for a walk");
    }
}
