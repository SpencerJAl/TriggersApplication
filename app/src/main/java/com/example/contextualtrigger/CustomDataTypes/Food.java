package com.example.contextualtrigger.CustomDataTypes;

public class Food {

    public int ID;
    public String Name;
    public int Calorie;

    public Food(String name, int calorie, int id){
        ID = id;
        Name = name;
        Calorie = calorie;
    }
    public int getID() {return ID;}

    public void setID(int id) {ID = id;}

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
