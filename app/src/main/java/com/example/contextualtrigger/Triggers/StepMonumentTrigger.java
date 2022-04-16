package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Database.StepTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.DataSources.MonList;
import com.example.contextualtrigger.Notifications.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StepMonumentTrigger implements TriggerTemplate {

    Context MainContext;
    TriggerDatabase triggerDatabase;
    public String MonumentItem;
    int stepcount = 0;
    List<MonList> MonumentList;
    List<StepTable> LastestStep;



    public StepMonumentTrigger(Context context){
        MainContext = context;

    }

    @Override
    public void getTriggerData(Context context) {
        System.out.println("In monument trigger");
        //gets the weather data from the database
        triggerDatabase = TriggerDatabase.getInstance(context);
        try {
            LastestStep = triggerDatabase.stepDao().getStepByDate(getDate());
            checkTriggerData();
        }catch (NullPointerException e){
            System.out.println("Nothing Stored will try later.....");
        }
    }

    @Override
    public void checkTriggerData() {
        // for loop to keep checking in cases the step count is above a certain monument
        for(int i = 1, j = 0; i < MonumentList.size(); i++, j++){
            // Check if step counter is greater then monument, if it is then it continues to the next monument.
         if(stepcount < MonumentList.get(i).Step){
                System.out.println("Too Many Steps for this monument");
         }else{
             //Sets the monument name from the previous monument
             MonumentItem = MonumentList.get(j).Name;
             System.out.println("Monument Found! :)");
         }
        }
    }

    @Override
    public void informNotificationManager() {
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("2", "Monument Achieved", "Well done you have completed enough steps to walk up " + MonumentItem);

    }

    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
