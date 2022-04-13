package com.example.contextualtrigger.DataSources;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.contextualtrigger.Database.LocationTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Database.WeatherTable;
import com.example.contextualtrigger.Triggers.LocationTrigger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class LocationLatLong extends BroadcastReceiver implements LocationListener {

    protected LocationManager locationManager;
    private Context MainContext;
    TriggerDatabase triggerDatabase;


    //Using the in-built location sensors it get the lat and long of the user
    public void getCurrentLocation(Context context) {
        MainContext = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Permissions Failed");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double Latitude = 0;
        double Longitude = 0;
        if(lastKnownLocation != null) {
            Latitude = lastKnownLocation.getLatitude();
            Longitude = lastKnownLocation.getLongitude();
        }

        storeLocations(context,Latitude, Longitude);
    }

    //Store the lat and long into the database
    private void storeLocations(Context context, double Latitude, double Longitude){
        String date = getDate();
        triggerDatabase = TriggerDatabase.getInstance(context);
        List<LocationTable> locations = triggerDatabase.locationDao().getTodayLocations(date);

        if(locations.size() == 0){
            LocationTable entry = new LocationTable(Latitude,Longitude,date);
            triggerDatabase.locationDao().insertLocation(entry);
        } else if (locations.size() > 0){
            if(locations.get(0).getNewLat() == 0.0 && locations.get(0).getNewLng() == 0.0){
                triggerDatabase.locationDao().updateNewLatLng(Latitude,Longitude,date);
            } else {
                triggerDatabase.locationDao().updateAllLatLng(locations.get(0).getNewLat(), locations.get(0).getNewLng(), Latitude,Longitude, date);
            }
        }

        LocationTrigger lc = new LocationTrigger(context);
        lc.getTriggerData(context);
    }


    @Override
    //This is called by the alarm manager when the time has been reached to execute this (i.e an hour has gone by)
    public void onReceive(Context context, Intent intent) {
        getCurrentLocation(context);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {


    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    //Gets the Current date and returns it
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }

}
