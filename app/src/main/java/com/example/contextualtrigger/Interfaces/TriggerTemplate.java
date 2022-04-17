package com.example.contextualtrigger.Interfaces;

import android.content.Context;

public interface TriggerTemplate {
    //Trigger interface for each trigger class type

    void getTriggerData(Context C);

    void checkTriggerData();

    void informNotificationManager();
}
