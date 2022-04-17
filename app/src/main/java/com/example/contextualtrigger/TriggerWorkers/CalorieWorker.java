package com.example.contextualtrigger.TriggerWorkers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.contextualtrigger.Triggers.CalorieTrigger;

public class CalorieWorker extends Worker {

    Context mainContext;

    public CalorieWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mainContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //Execute calorie trigger
        CalorieTrigger CT = new CalorieTrigger(mainContext);
        CT.getTriggerData(mainContext);

        return Result.success();
    }
}
