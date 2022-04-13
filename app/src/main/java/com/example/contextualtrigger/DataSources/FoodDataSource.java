package com.example.contextualtrigger.DataSources;

import java.util.ArrayList;

public class FoodDataSource {

    ArrayList<Food> food;

    public FoodDataSource() {
        food = new ArrayList<>();
        createList();
    }

    //Could add more
    private void createList(){
        Food f1 = new Food("Apple", 200);
        Food f2 = new Food("Chocolate", 400);
        Food f3 = new Food("Crisps", 340);
        Food f4 = new Food("Coke", 180);

        food.add(f1);
        food.add(f2);
        food.add(f3);
        food.add(f4);
    }


    //call this within the calorie trigger to get info
    //instead of using the DB
    public ArrayList<Food> getFood(){
        return food;
    }
}
