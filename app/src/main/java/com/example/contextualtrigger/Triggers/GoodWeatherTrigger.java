package com.example.contextualtrigger.Triggers;

import android.content.Context;

import com.example.contextualtrigger.Database.TriggerDatabase;
import com.example.contextualtrigger.Database.WeatherTable;
import com.example.contextualtrigger.Managers.NotiManager;
import com.example.contextualtrigger.Interfaces.TriggerTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GoodWeatherTrigger implements TriggerTemplate {

    Context MainContext;
    TriggerDatabase triggerDatabase;
    List<WeatherTable> currentWeather;
    String notificationWeather = "";
    String notificationTemp = "";

    public GoodWeatherTrigger(Context context){
        MainContext = context;
    }

    @Override
    public void getTriggerData(Context context) {
        //gets the weather data from the database
        triggerDatabase = TriggerDatabase.getInstance(context);
        try {
            currentWeather = triggerDatabase.weatherDao().getWeatherByDate(getDate());
            checkTriggerData();
        }catch (NullPointerException e){
            System.out.println("Nothing Stored will try later.....");
        }
    }

    @Override
    public void checkTriggerData() {
        //checks if the weather has been retrieved
        if(currentWeather.size() > 0) {
            Double current_Temp = currentWeather.get(0).getCurrentTemp();
            String current_Conditions = currentWeather.get(0).getWeatherDesc();

            //if there is weather data, check to see if the temp > 10 and is one of the weather conditions.
            if (current_Temp > 10.0) {
                if (current_Conditions.equals("Light Cloud") || current_Conditions.equals("Clear") || current_Conditions.equals("Heavy Cloud")) {
                    notificationWeather = current_Conditions;
                    notificationTemp = String.valueOf(Math.round(current_Temp));
                    informNotificationManager();
                }
            }
        }
        }

    @Override
    public void informNotificationManager() { //Notify the Notification Manager to send a notification to the user
        NotiManager notiManager = NotiManager.getNotiManagerInstance(MainContext);
        notiManager.sendNotification("4", "Perfect Weather Conditions", "The weather conditions are " + notificationWeather + " with a temperature of " +notificationTemp +". Lets go for a walk");
    }


    //Gets the Current date and returns it
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        return date;
    }
}
