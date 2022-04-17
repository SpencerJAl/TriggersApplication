package com.example.contextualtrigger.Interfaces;

import android.content.Context;

import androidx.core.app.NotificationCompat;

public interface NotificationTemplate {
    //Interface for each notification class type

    void createBuilder(Context MainContext, String CHANNEL_ID, String title, String content);

    NotificationCompat.Builder getNotificationBuilder();
}
