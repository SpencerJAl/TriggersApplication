package com.example.contextualtrigger.TriggerWorkers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.contextualtrigger.Triggers.GoodWeatherTrigger;

public class WeatherWorker extends Worker {

    Context mainContext;

    public WeatherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mainContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        GoodWeatherTrigger gd = new GoodWeatherTrigger(mainContext);
        gd.getTriggerData(mainContext);


        return Result.success();

    }
}
