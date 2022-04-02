package com.example.contextualtrigger;

import android.content.Context;

public class LocationTrigger implements TriggerTemplate{

    Context MainContext;

    public LocationTrigger(Context context){
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
        notiManager.sendNotification("5", "Location is Perfect", "You are in an excellent location to go for a walk.");

    }
}
