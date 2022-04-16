package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

public class LowActivityTrigger implements TriggerTemplate {

    Context MainContext;

    public LowActivityTrigger(Context context){
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
        notiManager.sendNotification("3", "Low Activity!", "Get up and go for a walk, haven't moved in some time!");

    }
}
