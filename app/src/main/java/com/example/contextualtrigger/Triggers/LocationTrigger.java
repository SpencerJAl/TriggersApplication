package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Database.LocationTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;
import com.pradeep.notification_lib.NotificationBuilder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LocationTrigger implements TriggerTemplate {

    TriggerDatabase triggerDatabase;
    Context MainContext;

    private double lastKnownLat;
    private double lastKnownLong;
    private double currentLat;
    private double currentLong;


    public LocationTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData(Context context) {
        triggerDatabase = TriggerDatabase.getInstance(context);
        String date = getDate();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        try {
            List<LocationTable> location = triggerDatabase.locationDao().getTodayLocations(date);

            lastKnownLat = location.get(0).getLat();
            lastKnownLong = location.get(0).getLng();
            currentLat = location.get(0).getNewLat();
            currentLong = location.get(0).getNewLng();

            checkTriggerData();

        } catch (NullPointerException | IndexOutOfBoundsException e){
            System.out.println("Nothing Stored will try later.....");
        }
    }

    @Override
    public void checkTriggerData() {
        if(lastKnownLat == currentLat && lastKnownLong == currentLong){
            informNotificationManager();
        }
    }

    @Override
    public void informNotificationManager() {
       // NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
       // notiManager.sendNotification("5", "Location Hasn't Changed", "Your location hasn't changed in some time, get active and go for a walk");
        NotificationBuilder.Companion.with(MainContext).content(
                text->{
                    text.setTitle("Location Hasn't Changed");
                    text.setText("Your location hasn't changed in some time, get active and go for a walk");
                    return null;
                }
        ).show();
    }

    //Gets the Current date and returns it
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
