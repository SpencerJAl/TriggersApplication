package com.example.contextualtrigger.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM Locations")
    List<LocationTable> getLocations();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLocation(LocationTable locationTable);

    @Query("UPDATE Locations SET newLat=:lat, newLng=:lng WHERE date=:current_date")
    int updateNewLatLng(double lat, double lng, String current_date);

    @Query("UPDATE Locations SET lat=:oldLat, long=:oldLng, newLat=:Lat, newLng=:Lng WHERE date=:current_date")
    int updateAllLatLng(double oldLat, double oldLng, double Lat, double Lng, String current_date);

    @Query("SELECT * FROM Locations WHERE date=:current_date")
    List<LocationTable> getTodayLocations(String current_date);

}
