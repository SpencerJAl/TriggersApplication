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
    public String Food;
    int stepcount = 0;
    List<Food> food;
    List<StepTable> LastestStep;



    public CalorieTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData(Context context) {
        System.out.println("In monument trigger");
        //gets the weather data from the database
        triggerDatabase = TriggerDatabase.getInstance(context);
        try {
            LastestStep = triggerDatabase.stepDao().getStepsFromDate(getDate());
            checkTriggerData();
        }catch (NullPointerException e){
            System.out.println("Nothing Stored will try later.....");
        }

        FoodDataSource foodData = new FoodDataSource();
        food = foodData.getFood();
    }

    @Override
    public void checkTriggerData() {
        Calories = stepcount * 0.04;
        for(int i = 1, j = 0; i < food.size(); i++, j++){
            // Check if step counter is greater then monument, if it is then it continues to the next monument.
            if(Calories < food.get(i).getCalorie()){
                System.out.println("Too Many Steps for this monument");
            }else{
                //Sets the monument name from the previous monument
                Food = food.get(j).getName();
                System.out.println("Too Many Steps for this monument");
            }
        }
    }

    @Override
    public void informNotificationManager() {
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("1", "Calories Burned", "Well done you have burned " + Calories + " calories keep going, it's enough for a " + Food);
    }

    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
