package com.example.contextualtrigger.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.contextualtrigger.DataSources.LocationLatLong;
import com.example.contextualtrigger.DataSources.WeatherAPIinfo;

public class AlarmHandler {

    private Context context;

    public AlarmHandler(Context context) {
        this.context = context;
    }

    //Alarm manager to trigger the information sources to execute code at certain times (like every hour even when the application isn't being used)
    public void setAlarmManager(){

        Intent weatherAPIIntent = new Intent(context, WeatherAPIinfo.class); //weather api intent
        PendingIntent sender = PendingIntent.getBroadcast(context, 2,weatherAPIIntent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(am != null) {
            long runAfter = 60 * 60 * 1000; //Every hour
            long runEvery = 60 * 60 * 1000; //Every hour
            am.setRepeating(AlarmManager.RTC_WAKEUP, runAfter, runEvery, sender); //call the weather api intent every hour
        }


        Intent locationIntent = new Intent(context, LocationLatLong.class); //location sensor intent
        PendingIntent sender2 = PendingIntent.getBroadcast(context, 2, locationIntent, 0);
        AlarmManager am2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(am2 != null){
            long runAfter2 = 30 * 60 * 1000; //Run after 30 mins has past for testing
            long runEvery2 = 60 * 60 * 1000; //Run every hour after the initial 30 mins
            am.setRepeating(AlarmManager.RTC_WAKEUP, runAfter2, runEvery2,sender2); //call the location sensor every hour
        }
    }

    //used to cancel the background alarms so there is only ever 2 alarms
    public void  cancelAlarmManager(){

        Intent weatherAPIIntent = new Intent(context, WeatherAPIinfo.class);//weather api intent
        PendingIntent sender = PendingIntent.getBroadcast(context, 2,weatherAPIIntent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(am != null) {
            am.cancel(sender);
        }

        Intent locationIntent = new Intent(context, LocationLatLong.class);//location data source intent
        PendingIntent sender2 = PendingIntent.getBroadcast(context, 2, locationIntent, 0);
        AlarmManager am2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(am2 != null){
            am2.cancel(sender2);
        }

    }
}
