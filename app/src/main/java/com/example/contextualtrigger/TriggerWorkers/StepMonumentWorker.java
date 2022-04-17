package com.example.contextualtrigger.TriggerWorkers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.contextualtrigger.Triggers.StepMonumentTrigger;

public class StepMonumentWorker extends Worker {
    Context mainContext;

    public StepMonumentWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mainContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //Execute step monument trigger
        StepMonumentTrigger SMT = new StepMonumentTrigger(mainContext);
        SMT.getTriggerData(mainContext);
        return Result.success();
    }
}
