package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.DataSources.MonumentList;
import com.example.contextualtrigger.Database.StepTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.CustomDataTypes.MonList;
import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StepMonumentTrigger implements TriggerTemplate {

    Context MainContext;
    TriggerDatabase triggerDatabase;
    public String MonumentItem = "None";
    List<MonList> monumentList;
    List<StepTable> LastestStep;



    public StepMonumentTrigger(Context context){
        MainContext = context;

    }

    @Override
    public void getTriggerData(Context context) {
        triggerDatabase = TriggerDatabase.getInstance(context);
        LastestStep = triggerDatabase.stepDao().getStepsFromDate(getDate());

        MonumentList MonList = new MonumentList();
        monumentList = MonList.getMonList();

        checkTriggerData();
    }

    @Override
    public void checkTriggerData() {
        // for loop to keep checking in cases the step count is above a certain monument
        for(int i = 1, j = 0; i < monumentList.size(); i++, j++){
            // Check if step counter is greater then monument, if it is then it continues to the next monument.
         if(LastestStep.get(0).getStepCount() < monumentList.get(i).getStep()){

         }else{
             //Sets the monument name from the previous monument
             MonumentItem = monumentList.get(j).getName();
         }
        }

        if(!MonumentItem.equals("None")){
            informNotificationManager();
        }
    }

    @Override
    public void informNotificationManager() { //Notify the Notification Manager to send a notification to the user
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("2", "Monument Achieved", "Well done you have completed enough steps to walk up " + MonumentItem);

    }

    //Returns the current date when called
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
