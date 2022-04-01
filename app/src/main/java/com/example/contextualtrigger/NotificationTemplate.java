package com.example.contextualtrigger;

import android.content.Context;

import androidx.core.app.NotificationCompat;

public interface NotificationTemplate {
    void createBuilder(Context MainContext, String CHANNEL_ID, String title, String content);

    NotificationCompat.Builder getNotificationBuilder();
}
