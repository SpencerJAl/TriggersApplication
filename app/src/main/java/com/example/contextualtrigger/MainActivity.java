package com.example.contextualtrigger;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.example.contextualtrigger.AlarmManager.AlarmHandler;
import com.example.contextualtrigger.DataSources.StepCount;
import com.example.contextualtrigger.Database.StepTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Notifications.NotiManager;
import com.example.contextualtrigger.Triggers.TriggerManager;
import com.pradeep.notification_lib.NotificationBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private TextView textViewStepCounter;
    private String textViewStepCount;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    private int LOCATION_PERMISSION_CODE = 1, counter = 0;
    StepCount stepCount;
    NotiManager notiManager;
    TriggerDatabase DB;
    //private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationBuilder.Companion.with(MainActivity.this).setWork();
        getSupportActionBar().hide();

        //sets up the notification manager
        notiManager = NotiManager.getNotiManagerInstance(this); //use NotiManager.getNotiManagerInstance(context) to access the notifiaction manager, it makes sure that there is only ever 1 instance of it.

        //sets up the database
        DB = TriggerDatabase.getInstance(getApplicationContext());
        List<StepTable> list = DB.stepDao().getSteps();

        //Sets up the alarm manager when the application is first ran
        AlarmHandler alarmHandler = new AlarmHandler(this);
        alarmHandler.cancelAlarmManager();
        alarmHandler.setAlarmManager();

        TriggerManager triggerManager = new TriggerManager(this);
        triggerManager.startTriggerWorkers();


        //Code for the step counter.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Adds the step counter to the front end UI (From StepCount)
        textViewStepCounter = findViewById(R.id.textViewStepCounter);
        textViewStepCounter.setText("Counter Sensor Found");
        //counter = stepCount;
        //textViewStepCount = Integer.toString(counter);
        //((TextView)textViewStepCounter).setText(textViewStepCount);

        //Determines if the sensor is found on the phone.
        //sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
         //   mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
          //  isCounterSensorPresent = true;
           // textViewStepCounter.setText("Counter Sensor Found");
        //}else{
         //   textViewStepCounter.setText("Counter Sensor Not Found");
          //  isCounterSensorPresent = false;
        //}




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

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestLocationPermission();
        }
    }

    //Used to request location permission's from the user
    private void requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)){
            new AlertDialog.Builder(this).setTitle("Permission Needed").setMessage("Allow location permission ?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    //needs to be implemented in order to check the permissions
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}