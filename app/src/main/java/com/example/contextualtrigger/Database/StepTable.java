package com.example.contextualtrigger.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Steps")

public class StepTable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "StepCount")
    private int steps;

    @ColumnInfo(name = "Target")
    private int target;

    @ColumnInfo(name = "date")
    private String date;


    public StepTable(int steps, int target, String date) {
        this.steps = steps;
        this.target = target;
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

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
