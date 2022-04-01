package com.example.contextualtrigger;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.LocalDateTime;

public class NotiManager {

    private LocalDateTime lastNotificationTime;
    private LocalDateTime nowNotificationTime;
    private int timePassed = 0;
    private int timeDelay = 2;
    private boolean initialNotification = true;

    private static final String CHANNEL_1_ID = "1";
    private static final String CHANNEL_2_ID = "2";
    private static final String CHANNEL_3_ID = "1";

    private static final int NOTIFICATION_1_ID = 1;
    private static final int NOTIFICATION_2_ID = 2;
    private static final int NOTIFICATION_3_ID = 3;
    private static final int NOTIFICATION_4_ID = 4;
    private static final int NOTIFICATION_5_ID = 5;


    private NotificationManager mNotifyManager;
    private static NotiManager notiManagerInstance;
    private Context MainContext;

    public static synchronized NotiManager getNotiManagerInstance(Context context){
        if(notiManagerInstance == null){
            notiManagerInstance = new NotiManager(context);
        }
        return notiManagerInstance;
    }


    public NotiManager(Context context) {
        MainContext = context;
        createNotificationChannel1(context);
        createNotificationChannel2(context);
        createNotificationChannel3(context);
    }

    public void sendNotification(String triggerName, String Display_Title, String Display_Content) {
        getCurrentTime();
        if (!initialNotification) {
            calcTimeDifference();
        }
        if (timePassed < timeDelay & !initialNotification) { //if the time that has passed isnt as much as the delay then sleep.
            System.out.println(timePassed);
            System.out.println(timeDelay);
            //need to find a way to add delay between noti's
            //Time Delay - Time Passed = time to actual delay.


            //Comment from Alex- Could you not setup a second timer that count's down, run a if check and if time passed < time delay then you could go ahead and run it?
            //Or am I misunderstanding the issue?
        }

        if (triggerName.equals("1")) {
            displayNoti1(Display_Title, Display_Content);
        } else if (triggerName.equals("2")) {
            displayNoti2(Display_Title, Display_Content);
        } else if (triggerName.equals("3")) {
            displayNoti3(Display_Title, Display_Content);
        } else if (triggerName.equals("4")) {
            displayNoti4(Display_Title, Display_Content);
        } else if (triggerName.equals("5")) {
            displayNoti5(Display_Title, Display_Content);
        }

        initialNotification = false;

    }

    //Creates Instance of the needed notification
    //Get gets the builder for it
    //Then tells the notification manager to show it
    private void displayNoti1(String title, String content) {
        CalorieNotification notificationCalorie = new CalorieNotification(MainContext, CHANNEL_1_ID, title, content);
        NotificationCompat.Builder notifyBuilder = notificationCalorie.getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_1_ID, notifyBuilder.build());
    }

    private void displayNoti2(String title, String content) {
        StepMonumentNotification stepMonumentNotification = new StepMonumentNotification(MainContext, CHANNEL_2_ID, title, content);
        NotificationCompat.Builder notifyBuilder = stepMonumentNotification.getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_2_ID, notifyBuilder.build());
    }

    private void displayNoti3(String title, String content) {
        LowActivityNotification lowActivityNotification = new LowActivityNotification(MainContext, CHANNEL_1_ID, title, content);
        NotificationCompat.Builder notifyBuilder = lowActivityNotification.getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_3_ID, notifyBuilder.build());
    }

    private void displayNoti4(String title, String content) {
        GoodWeatherNotification goodWeatherNotification = new GoodWeatherNotification(MainContext, CHANNEL_3_ID, title, content);
        NotificationCompat.Builder notifyBuilder = goodWeatherNotification.getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_4_ID, notifyBuilder.build());
    }

    private void displayNoti5(String title, String content) {
        LocationNotification locationNotification = new LocationNotification(MainContext, CHANNEL_2_ID, title, content);
        NotificationCompat.Builder notifyBuilder = locationNotification.getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_5_ID, notifyBuilder.build());
    }

    //Notification Channel for High Importance notifications
    public void createNotificationChannel1(Context context) {
        mNotifyManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_1_ID, "High", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification Channel for High Importance");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    //Notification Channel for Default Importance notifications
    public void createNotificationChannel2(Context context) {
        mNotifyManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_2_ID, "Default", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification Channel for Default Importance");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    //Notification Channel for Low Importance notifications
    public void createNotificationChannel3(Context context) {
        mNotifyManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_3_ID, "Low", NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableLights(false);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(false);
            notificationChannel.setDescription("Notification Channel for Low Importance");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private void getCurrentTime() {
        if (initialNotification) {
            lastNotificationTime = LocalDateTime.now();
        } else {
            nowNotificationTime = LocalDateTime.now();
        }
    }

    private void calcTimeDifference() {
        if (initialNotification) {
            timePassed = timeDelay + 1;
        } else {
            timePassed = (int) java.time.Duration.between(lastNotificationTime, nowNotificationTime).toMinutes();
        }
    }
}
