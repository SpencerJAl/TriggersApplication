package com.example.contextualtrigger.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Locations")
public class LocationTable {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "lat")
    private double lat;

    @ColumnInfo(name = "long")
    private double lng;

    @ColumnInfo(name = "newLat")
    private double newLat;

    @ColumnInfo(name = "newLng")
    private double newLng;

    @ColumnInfo(name = "date")
    private String date;


    @Ignore
    public LocationTable(double lat, double lng, String date) {
        this.lat = lat;
        this.lng = lng;
        this.newLat = 0;
        this.newLng = 0;
        this.date = date;
    }

    public LocationTable(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getNewLat() {
        return newLat;
    }

    public void setNewLat(double newLat) {
        this.newLat = newLat;
    }

    public double getNewLng() {
        return newLng;
    }

    public void setNewLng(double newLng) {
        this.newLng = newLng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
