package com.example.contextualtrigger.Interfaces;

import android.content.Context;

public interface TriggerTemplate {

    void getTriggerData(Context C);

    void checkTriggerData();

    void informNotificationManager();
}
