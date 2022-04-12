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



public class LocationLatLong extends BroadcastReceiver implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private int LOCATION_PERMISSION_CODE = 1;
    private Context MainContext;


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

        System.out.println(Longitude + " : " + Latitude);
    }


    @Override
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

}
