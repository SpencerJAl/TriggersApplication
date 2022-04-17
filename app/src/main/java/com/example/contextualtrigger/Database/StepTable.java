package com.example.contextualtrigger.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Steps")

public class StepTable {

    //Table used to store the users step total, goal etc
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "StepCount")
    private int StepCount;

    @ColumnInfo(name = "Target")
    private int Target;

    @ColumnInfo(name = "date")
    private String date;


    public StepTable(int steps, int target, String date) {
        this.StepCount = steps;
        this.Target = target;
        this.date = date;
    }

    public StepTable(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepCount() {
        return StepCount;
    }

    public void setStepCount(int stepCount) {
        StepCount = stepCount;
    }

    public int getTarget() {
        return Target;
    }

    public void setTarget(int target) {
        Target = target;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
