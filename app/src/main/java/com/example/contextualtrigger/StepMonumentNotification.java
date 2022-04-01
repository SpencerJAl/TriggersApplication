package com.example.contextualtrigger;

import android.content.Context;

import androidx.core.app.NotificationCompat;

public class StepMonumentNotification implements NotificationTemplate{

    NotificationCompat.Builder notifyBuilder;

    public StepMonumentNotification(Context context, String CHANNEL_ID, String title, String content){
        createBuilder(context, CHANNEL_ID,title,content);
    }

    public void createBuilder(Context MainContext, String CHANNEL_ID, String title, String content){
        notifyBuilder = new NotificationCompat.Builder(MainContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.notification_icon);
    }
    public NotificationCompat.Builder getNotificationBuilder(){
        return notifyBuilder;
    }
}
