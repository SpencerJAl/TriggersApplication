package com.example.contextualtrigger.TriggerWorkers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.contextualtrigger.Triggers.LocationTrigger;

public class LocationWorker extends Worker {

    Context mainContext;


    public LocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mainContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //Execute location trigger
        LocationTrigger lc = new LocationTrigger(mainContext);
        lc.getTriggerData(mainContext);
        return Result.success();
    }
}
