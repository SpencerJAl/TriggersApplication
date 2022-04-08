package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

public class StepMonumentTrigger implements TriggerTemplate {

    Context MainContext;

    public StepMonumentTrigger(Context context){
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
        notiManager.sendNotification("2", "Monument Achieved", "Well done you have completed enough steps to walk up x.");

    }
}
