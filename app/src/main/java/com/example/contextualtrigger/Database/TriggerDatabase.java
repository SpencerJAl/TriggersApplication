package com.example.contextualtrigger.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StepTable.class,WeatherTable.class}, exportSchema = false, version = 2)
public abstract class TriggerDatabase extends RoomDatabase {

    private static final String DB_Name = "triggerDatabase";
    private static TriggerDatabase instance;

    public static synchronized TriggerDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TriggerDatabase.class, DB_Name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract StepDao stepDao();

    public abstract WeatherDao weatherDao();

}
