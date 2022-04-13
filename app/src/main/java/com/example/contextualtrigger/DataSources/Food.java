package com.example.contextualtrigger.DataSources;

public class Food {

    private String Name;
    private int Calorie;

    public Food(String name, int calorie){
        Name = name;
        Calorie = calorie;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCalorie() {
        return Calorie;
    }

    public void setCalorie(int calorie) {
        Calorie = calorie;
    }
}
