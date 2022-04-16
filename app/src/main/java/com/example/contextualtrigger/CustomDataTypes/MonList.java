package com.example.contextualtrigger.CustomDataTypes;

public class MonList {

    public int ID;
    public String Name;
    public int Step;

    public MonList(String name, int step, int id){
        ID = id;
        Name = name;
        Step = step;
    }
    public int getID() {return ID;}

    public void setID(int id) {ID = id;}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStep() {
        return Step;
    }

    public void setStep(int step) {
        Step = step;
    }
}
