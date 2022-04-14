package com.example.contextualtrigger.TriggerWorkers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.contextualtrigger.Triggers.LowActivityTrigger;

public class LowActivityWorker extends Worker {

    Context mainContext;

    public LowActivityWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Context mainContext;
    }

    @NonNull
    @Override
    public Result doWork() {
        LowActivityTrigger LAT = new LowActivityTrigger(mainContext);
        LAT.getTriggerData(mainContext);
        return null;
    }
}
