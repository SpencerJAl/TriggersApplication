package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

public class CalorieTrigger implements TriggerTemplate {

    Context MainContext;

    int stepCounter;

    public CalorieTrigger(Context context){
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
        notiManager.sendNotification("1", "Calories Burned", "Well done you have burned x calories keep going.");
    }
}
