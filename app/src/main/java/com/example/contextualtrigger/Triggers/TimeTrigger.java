package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Database.StepTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeTrigger implements TriggerTemplate {

    Context MainContext;
    LocalTime currentHour;
    TriggerDatabase triggerDatabase;
    List<StepTable> StepsTable;

    int StepCount = 0;
    int Goal = 1;

    String notificationTitle = "";
    String notificationContent = "";



    public TimeTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData(Context context) {
        currentHour = LocalTime.now();

        if(currentHour.getHour() == 9 || (currentHour.getHour() >= 17 && currentHour.getHour() < 21)){ //if its 9am or 5 pm, get triiger info
            triggerDatabase = TriggerDatabase.getInstance(context);

            StepsTable = triggerDatabase.stepDao().getStepsFromDate(getDate());

            StepCount = StepsTable.get(0).getStepCount();
            Goal = StepsTable.get(0).getTarget();

            checkTriggerData();
        }
    }

    @Override
    public void checkTriggerData() { //Check the data and at certain times and step progress send a notification
        if(currentHour.getHour() == 9){ //9am
            notificationTitle = "Good Morning !";
            notificationContent = "Time to get up and hit that goal of " + Goal + " Steps";
            informNotificationManager();
        } else if(currentHour.getHour() >= 17 && currentHour.getHour() < 21 && StepCount < Goal){ //Between 5pm - 9pm
            int stepsLeft = Goal - StepCount;
            notificationTitle = "Good Afternoon !";
            notificationContent = "Still got " + stepsLeft + " steps to go. Lets get moving ";
            informNotificationManager();
        } else if(currentHour.getHour() == 17 && currentHour.getHour() < 21 && StepCount > Goal){ //Between 5pm - 9pm
            notificationTitle = "Good Afternoon !";
            notificationContent = "Well Done, " + Goal + " achieved!. Keep Going";
            informNotificationManager();
        }

    }

    @Override
    public void informNotificationManager() { //Notify the Notification Manager to send a notification to the user
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("3", notificationTitle, notificationContent);

    }

    //Gets the current date and returns it
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
