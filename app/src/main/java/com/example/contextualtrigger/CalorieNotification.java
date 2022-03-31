package com.example.contextualtrigger;

import android.content.Context;

import androidx.core.app.NotificationCompat;

public class CalorieNotification {

    NotificationCompat.Builder notifyBuilder;

    public CalorieNotification(Context context, String CHANNEL_ID){
        createBuilder(context, CHANNEL_ID);
    }

    private void createBuilder(Context MainContext, String CHANNEL_ID){
        notifyBuilder = new NotificationCompat.Builder(MainContext, CHANNEL_ID)
                .setContentTitle("Calorie Burned")
                .setContentText("Well done you have burned x amount of Calories from walking.")
                .setSmallIcon(R.drawable.notification_icon);

    }
    public NotificationCompat.Builder getNotificationBuilder(){
        return notifyBuilder;
    }

}
