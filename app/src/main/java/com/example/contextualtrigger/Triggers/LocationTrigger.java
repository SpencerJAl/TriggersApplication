package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Notifications.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

public class LocationTrigger implements TriggerTemplate {

    Context MainContext;

    public LocationTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData(Context context) {

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
