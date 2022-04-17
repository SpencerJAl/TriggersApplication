package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;
import com.pradeep.notification_lib.NotificationBuilder;

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
      //  NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
       // notiManager.sendNotification("3", "Low Activity!", "Get up and go for a walk, haven't moved in some time!");
        NotificationBuilder.Companion.with(MainContext).content(
                text->{
                    text.setTitle("Low Activity!");
                    text.setText("Get up and go for a walk, haven't moved in some time!");
                    return null;
                }
        ).show();
    }
}
