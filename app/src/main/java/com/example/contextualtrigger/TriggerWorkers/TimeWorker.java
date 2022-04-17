package com.example.contextualtrigger.TriggerWorkers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.contextualtrigger.Triggers.TimeTrigger;

public class TimeWorker extends Worker {

    Context mainContext;

    public TimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Context mainContext;
    }

    @NonNull
    @Override
    public Result doWork() {
        //Execute time trigger
        TimeTrigger TimeT = new TimeTrigger(mainContext);
        TimeT.getTriggerData(mainContext);
        return Result.success();
    }
}
