package com.example.contextualtrigger.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    List<WeatherTable> getWeather();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWeather(WeatherTable weatherTable);

    @Query("UPDATE weather SET weatherDesc=:desc, minTemp=:mintemp, maxTemp=:maxtemp, currentTemp=:currenttemp,visibility=:vis ,humidity=:newHumidity WHERE date=:current_Date")
    int updateCurrentWeather(String desc, double mintemp, double maxtemp, double currenttemp, int newHumidity,double vis ,String current_Date);

    @Query("SELECT * FROM weather WHERE date=:current_date")
    List<WeatherTable> getWeatherByDate(String current_date);


}