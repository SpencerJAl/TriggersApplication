package com.example.contextualtrigger;

import android.content.Context;

public class GoodWeatherTrigger implements TriggerTemplate{

    Context MainContext;

    public GoodWeatherTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData() {

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
