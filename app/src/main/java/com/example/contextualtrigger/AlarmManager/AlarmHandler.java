package com.example.contextualtrigger.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.contextualtrigger.DataSources.WeatherAPIinfo;

public class AlarmHandler {

    private Context context;

    public AlarmHandler(Context context) {
        this.context = context;
    }

    public void setAlarmManager(){
        Intent weatherAPIIntent = new Intent(context, WeatherAPIinfo.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 2,weatherAPIIntent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(am != null) {
            long runAfter = 60 * 60 * 1000; //Every hour
            long runEvery = 60 * 60 * 1000; //Every hour
            am.setRepeating(AlarmManager.RTC_WAKEUP, runAfter, runEvery, sender);
        }
    }

    public void  cancelAlarmManager(){
        Intent weatherAPIIntent = new Intent(context, WeatherAPIinfo.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 2,weatherAPIIntent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(am != null) {
            am.cancel(sender);
        }
    }
}
