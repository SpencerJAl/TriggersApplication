package com.example.contextualtrigger.DataSources;

import android.app.Activity;

import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.contextualtrigger.Database.StepTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class StepCount extends Activity implements SensorEventListener {
    // setting up variables for use in the application.
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    int stepCount = 0;
    TriggerDatabase triggerDatabase;
    boolean isCounterSensorPresent;
    Context MainContext;

    // controls how to count the steps in the application.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Found it");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        } else {
            isCounterSensorPresent = false;
        }

        sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //handle step count here
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if(values.length > 0){
            value = (int) values[0];
        }

        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            stepCount = stepCount + value;
        }

        storeData();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    private void storeData(){
        String date = getDate();
        List<StepTable> steps = triggerDatabase.stepDao().getStepsFromDate(date);
        if(steps.size() > 0){
            triggerDatabase.stepDao().updateStep(stepCount,date);
        } else {
            StepTable entry = new StepTable(stepCount,10000,date);
            triggerDatabase.stepDao().insertSteps(entry);
        }
    }

    //Gets the Current date and returns it
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}


