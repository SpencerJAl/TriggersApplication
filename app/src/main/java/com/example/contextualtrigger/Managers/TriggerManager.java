package com.example.contextualtrigger.Managers;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.contextualtrigger.TriggerWorkers.CalorieWorker;
import com.example.contextualtrigger.TriggerWorkers.LocationWorker;
import com.example.contextualtrigger.TriggerWorkers.TimeWorker;
import com.example.contextualtrigger.TriggerWorkers.StepMonumentWorker;
import com.example.contextualtrigger.TriggerWorkers.WeatherWorker;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TriggerManager {
    private Context MainContext;

    private Constraints HighConstraints;
    private Constraints LowConstraints;

    private PeriodicWorkRequest weatherTriggerRequest;
    private PeriodicWorkRequest locationTriggerRequest;
    private PeriodicWorkRequest calorieTriggerRequest;
    private PeriodicWorkRequest stepMonumentTriggerRequest;
    private PeriodicWorkRequest timeTriggerRequest;

    public TriggerManager(Context context){
        MainContext = context;
    }


    public void startTriggerWorkers(){
        buildConstraints();
        buildRequests();
        queueRequests();
    }

    //Constraints that each trigger worker has to meet to run
    private void buildConstraints(){
        HighConstraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        LowConstraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();
    }


    //Builds the different requests for each trigger
    private void buildRequests(){
        weatherTriggerRequest = new PeriodicWorkRequest.Builder(WeatherWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(LowConstraints)
                .addTag("WeatherWorker")
                .setInitialDelay(5, TimeUnit.MINUTES)
                .build();


       locationTriggerRequest = new PeriodicWorkRequest.Builder(LocationWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(LowConstraints)
                .addTag("LocationWorker")
                .setInitialDelay(4, TimeUnit.MINUTES)
                .build();

       calorieTriggerRequest = new PeriodicWorkRequest.Builder(CalorieWorker.class, 1,TimeUnit.HOURS)
               .setConstraints(LowConstraints)
               .addTag("CalorieWorker")
               .setInitialDelay(3, TimeUnit.MINUTES)
               .build();

       stepMonumentTriggerRequest = new PeriodicWorkRequest.Builder(StepMonumentWorker.class, 1,TimeUnit.HOURS)
               .setConstraints(LowConstraints)
               .addTag("StepMonumentWorker")
               .setInitialDelay(2, TimeUnit.MINUTES)
               .build();

        timeTriggerRequest = new PeriodicWorkRequest.Builder(TimeWorker.class, 1,TimeUnit.HOURS)
               .setConstraints(LowConstraints)
               .addTag("TimeWorker")
               .setInitialDelay(1, TimeUnit.MINUTES)
               .build();

    }

    //Queue each of the requests to the Work Manager
    private void queueRequests() {
        WorkManager.getInstance(MainContext).enqueue(timeTriggerRequest);

        WorkManager.getInstance(MainContext).enqueue(calorieTriggerRequest);

        WorkManager.getInstance(MainContext).enqueue(stepMonumentTriggerRequest);

        WorkManager.getInstance(MainContext).enqueue(locationTriggerRequest);

        WorkManager.getInstance(MainContext).enqueue(weatherTriggerRequest);
    }

    //Clears all the queued requests (Used on restart)
    public void clearAllWork(){
        WorkManager.getInstance(MainContext).cancelAllWork();
    }

}
