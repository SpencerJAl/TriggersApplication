package com.example.contextualtrigger.DataSources;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class StepCount extends AppCompatActivity implements SensorEventListener{
    // setting up variables for use in the application.
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    int stepCount = 0;

    // controls how to count the steps in the application.
    @Override
    public void onSensorChanged (SensorEvent sensorEvent){
        if (sensorEvent.sensor == mStepCounter) {
            stepCount = (int) sensorEvent.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}


