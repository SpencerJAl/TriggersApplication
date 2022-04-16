package com.example.contextualtrigger.DataSources;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.contextualtrigger.DataSources.StepCount;

public class StepCountReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context,StepCount.class);
        context.startService(service);
    }
}
