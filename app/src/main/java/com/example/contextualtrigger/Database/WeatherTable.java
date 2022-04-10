package com.example.contextualtrigger.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = "weather")
public class WeatherTable {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "weatherDesc")
    private String weatherDesc;

    @ColumnInfo(name = "minTemp")
    private double minTemp;

    @ColumnInfo(name = "maxTemp")
    private double maxTemp;

    @ColumnInfo(name = "currentTemp")
    private double currentTemp;

    @ColumnInfo(name = "humidity")
    private int humidity;

    @ColumnInfo(name = "visibility")
    private double visibility;

    @ColumnInfo(name = "date")
    private String date;


    public WeatherTable(String weatherDesc, double minTemp, double maxTemp, double currentTemp, int humidity, double visibility,String date) {
        this.weatherDesc = weatherDesc;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.currentTemp = currentTemp;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public WeatherTable(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
