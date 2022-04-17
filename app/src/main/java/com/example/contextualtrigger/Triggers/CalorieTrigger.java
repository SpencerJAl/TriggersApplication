package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.CustomDataTypes.Food;
import com.example.contextualtrigger.DataSources.FoodDataSource;
import com.example.contextualtrigger.Database.StepTable;
import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalorieTrigger implements TriggerTemplate {

    Context MainContext;
    TriggerDatabase triggerDatabase;
    public Double Calories;
    public String Food = "None";
    List<Food> food;
    List<StepTable> LastestStep;



    public CalorieTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData(Context context) { //Get the Step Info from DB and the food info from the Food Data Source Class
        //gets the weather data from the database
        triggerDatabase = TriggerDatabase.getInstance(context);

        LastestStep = triggerDatabase.stepDao().getStepsFromDate(getDate());

        FoodDataSource foodData = new FoodDataSource();
        food = foodData.getFood();

        checkTriggerData();
    }

    @Override
    public void checkTriggerData() { //Check to see if they have burned enough calories for each food type
        Calories = LastestStep.get(0).getStepCount() * 0.04;
        for(int i = 1, j = 0; i < food.size(); i++, j++){
            if(Calories < food.get(i).getCalorie()){

            }else{
                Food = food.get(j).getName();
            }
        }

        if(!Food.equals("None")){
            informNotificationManager();
        }
    }

    @Override
    public void informNotificationManager() { //Notify the Notification Manager to send a notification to the user
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("1", "Calories Burned", "You have burned " + Calories + " calories, it's the equivalent to a " + Food + " .Keep walking and burn more calories!");
    }

    //Get the current date
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
