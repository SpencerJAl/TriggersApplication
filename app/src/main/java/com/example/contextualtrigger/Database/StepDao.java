package com.example.contextualtrigger.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StepDao {

    @Query("SELECT * FROM Steps")
    List<StepTable> getSteps();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSteps(StepTable stepTable);

    @Query("SELECT * FROM Steps WHERE date=:current_date")
    List<StepTable> getStepsFromDate(String current_date);

    @Query("UPDATE Steps SET StepCount=:newCount WHERE date=:current_date")
    void updateStep(int newCount, String current_date);

    @Query("SELECT * FROM Steps ORDER BY date DESC LIMIT 1")
    List<StepTable> getStepByDate();

}


