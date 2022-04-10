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


}


