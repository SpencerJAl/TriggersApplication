package com.example.contextualtrigger.Notifications;

import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.example.contextualtrigger.Interfaces.NotificationTemplate;
import com.example.contextualtrigger.R;

public class LowActivityNotification implements NotificationTemplate {

    NotificationCompat.Builder notifyBuilder;

    public LowActivityNotification(Context context, String CHANNEL_ID, String title, String content){
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
