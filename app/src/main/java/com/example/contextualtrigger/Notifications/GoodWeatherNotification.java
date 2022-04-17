package com.example.contextualtrigger.Notifications;

import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.example.contextualtrigger.Interfaces.NotificationTemplate;
import com.example.contextualtrigger.R;

public class GoodWeatherNotification implements NotificationTemplate {

    NotificationCompat.Builder notifyBuilder;

    public GoodWeatherNotification(Context context, String CHANNEL_ID, String title, String content){
        createBuilder(context, CHANNEL_ID,title,content);
    }

    public void createBuilder(Context MainContext, String CHANNEL_ID, String title, String content){
        notifyBuilder = new NotificationCompat.Builder(MainContext, CHANNEL_ID)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setSmallIcon(R.drawable.notification_icon);

    }
    public NotificationCompat.Builder getNotificationBuilder(){
        return notifyBuilder;
    }
}
