package com.example.contextualtrigger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.contextualtrigger.DataSources.WeatherAPIinfo;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Triggers.GoodWeatherTrigger;
import com.pradeep.notification_lib.NotificationBuilder;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textViewStepCounter;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    private String textTitle,textContent, textStep,textContext;
    int stepCount = 0;
    int CalorieCounnt = 0;
    NotiManager notiManager;
    TriggerDatabase DB;
    //private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationBuilder.Companion.with(MainActivity.this).setWork();
        getSupportActionBar().hide();
        notiManager = NotiManager.getNotiManagerInstance(this); //use NotiManager.getNotiManagerInstance(context) to access the notifiaction manager, it makes sure that there is only ever 1 instance of it.


        DB = TriggerDatabase.getInstance(getApplicationContext());


        //Code for the step counter.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textViewStepCounter = findViewById(R.id.textViewStepCounter);
        textTitle = "You've burned" + CalorieCounnt + " Calories!";
        textContent = "That's the same as an apple";

        textStep = "You've walked " + stepCount + "steps!";
        textContext = "That's the same as walking up the eiffel tower";

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }else{
            textViewStepCounter.setText("Counter Sensor Not Found");
            isCounterSensorPresent = false;
        }
        //Calorie Calcaulation
        CalorieCounnt = (int) (stepCount * 0.04);

        if(CalorieCounnt > 52.00){

        //Calorie Counter Notification Builder
         NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());
        }

        //Monument Notification Builder
        if(stepCount > 1665 ){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"2")
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(textStep)
                    .setContentText(textContext)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(2, builder.build());
        }

        WeatherAPIinfo w = new WeatherAPIinfo(this);

        GoodWeatherTrigger gd = new GoodWeatherTrigger(this);
        gd.getTriggerData();

        findViewById(R.id.btn_notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationBuilder.Companion.with(MainActivity.this).asBigText(bigText -> {
                    bigText.setTitle("You have completed milestone one");
                    bigText.setText("1000 steps completed");
                    bigText.setExpandedText("");
                    bigText.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground));
                    return null;
                }).show();
            }
        });

    }

    //Sensor setup, including code for when the application is closed and when it is open.
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == mStepCounter){
            stepCount = (int) sensorEvent.values[0];
            textViewStepCounter.setText(String.valueOf(stepCount));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this,mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,mStepCounter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,mStepCounter);
    }


}