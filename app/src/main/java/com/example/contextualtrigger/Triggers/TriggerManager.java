package com.example.contextualtrigger.Triggers;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.contextualtrigger.TriggerWorkers.CalorieWorker;
import com.example.contextualtrigger.TriggerWorkers.LocationWorker;
import com.example.contextualtrigger.TriggerWorkers.LowActivityWorker;
import com.example.contextualtrigger.TriggerWorkers.StepMonumentWorker;
import com.example.contextualtrigger.TriggerWorkers.WeatherWorker;

import java.util.concurrent.TimeUnit;

public class TriggerManager {
    private Context MainContext;

    private Constraints HighConstraints;
    private Constraints LowConstraints;

    private PeriodicWorkRequest weatherTriggerRequest;
    private PeriodicWorkRequest locationTriggerRequest;
    private PeriodicWorkRequest calorieTriggerRequest;
    private PeriodicWorkRequest stepMonumentTriggerRequest;
    private PeriodicWorkRequest activityTriggerRequest;

    public TriggerManager(Context context){
        MainContext = context;
    }


    public void startTriggerWorkers(){
        buildConstraints();
        buildRequests();
        queueRequests();
    }

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


    private void buildRequests(){
        weatherTriggerRequest = new PeriodicWorkRequest.Builder(WeatherWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(LowConstraints)
                .addTag("WeatherWorker")
                .setInitialDelay(2, TimeUnit.MINUTES)
                .build();


       locationTriggerRequest = new PeriodicWorkRequest.Builder(LocationWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(LowConstraints)
                .addTag("LocationWorker")
                .setInitialDelay(3, TimeUnit.MINUTES)
                .build();

       calorieTriggerRequest = new PeriodicWorkRequest.Builder(CalorieWorker.class, 1,TimeUnit.HOURS)
               .setConstraints(LowConstraints)
               .addTag("CalorieWorker")
               .setInitialDelay(4, TimeUnit.MINUTES)
               .build();

       stepMonumentTriggerRequest = new PeriodicWorkRequest.Builder(StepMonumentWorker.class, 1,TimeUnit.HOURS)
               .setConstraints(LowConstraints)
               .addTag("StepMonumentWorker")
               .setInitialDelay(5, TimeUnit.MINUTES)
               .build();

       activityTriggerRequest = new PeriodicWorkRequest.Builder(LowActivityWorker.class, 1 , TimeUnit.HOURS)
               .setConstraints(LowConstraints)
               .addTag("LowActivityWorker")
               .setInitialDelay(6, TimeUnit.MINUTES)
               .build();

    }

    private void queueRequests() {
        WorkManager.getInstance(MainContext).enqueue(weatherTriggerRequest);

        WorkManager.getInstance(MainContext).enqueue(locationTriggerRequest);

        //WorkManager.getInstance(MainContext).enqueue(calorieTriggerRequest);

        //WorkManager.getInstance(MainContext).enqueue(stepMonumentTriggerRequest);

        // WorkManager.getInstance(MainContext).enqueue(activityTriggerRequest);
    }
}
