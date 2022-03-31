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

public class NotiManager{

    private LocalDateTime lastNotificationTime;
    private LocalDateTime nowNotificationTime;
    private int timePassed = 0;
    private int timeDelay = 2;
    private boolean initialNotification = true;
    private static final String CHANNEL_1_ID = "1";
    private static final int NOTIFICATION_1_ID = 1;
    private static final String CHANNEL_2_ID = "2";
    private NotificationManager mNotifyManager;
    private Context MainContext;


    public NotiManager(Context context) {
        MainContext = context;
        createNotificationChannel1(context);
    }

    public void sendNotification(String triggerName) {
        getCurrentTime();
        if(!initialNotification) {
            calcTimeDifference();
        }
        if(timePassed < timeDelay & !initialNotification){ //if the time that has passed isnt as much as the delay then sleep.
            System.out.println(timePassed);
            System.out.println(timeDelay);
            //need to find a way to add delay between noti's
            //Time Delay - Time Passed = time to actual delay.


            //Comment from Alex- Could you not setup a second timer that count's down, run a if check and if time passed < time delay then you could go ahead and run it?
            //Or am I misunderstanding the issue?
        }

        if (triggerName.equals("1")) {
            displayNoti1();
        } else if (triggerName.equals("2")) {
            displayNoti2();
        } else if (triggerName.equals("3")) {
            displayNoti3();
        } else if (triggerName.equals("4")) {
            displayNoti4();
        } else if (triggerName.equals("5")) {
            displayNoti5();
        }

        initialNotification = false;

    }

    //Creates Instance of the needed notification
    //Get gets the builder for it
    //Then tells the notification manager to show it
    private void displayNoti1() {
        CalorieNotification notificationCalorie = new CalorieNotification(MainContext,CHANNEL_1_ID);
        NotificationCompat.Builder notifyBuilder = notificationCalorie.getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_1_ID, notifyBuilder.build());
    }

    private void displayNoti2() {
        System.out.println("Noti 2");
    }

    private void displayNoti3() {
        System.out.println("Noti 3");
    }

    private void displayNoti4() {
        System.out.println("Noti 4");
    }

    private void displayNoti5() {
        System.out.println("Noti 5");
    }


    public void createNotificationChannel1(Context context){//Notification Channel for the calorie trigger notification
        mNotifyManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_1_ID,"Calorie", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification For Calorie");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private void getCurrentTime() {
        if(initialNotification) {
            lastNotificationTime = LocalDateTime.now();
        } else{
            nowNotificationTime = LocalDateTime.now();
        }
    }

    private void calcTimeDifference(){
        if(initialNotification){
            timePassed = timeDelay+1;
        }else {
            timePassed = (int) java.time.Duration.between(lastNotificationTime, nowNotificationTime).toMinutes();
        }
    }
}
