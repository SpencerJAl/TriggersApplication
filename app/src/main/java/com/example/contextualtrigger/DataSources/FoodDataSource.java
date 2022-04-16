package com.example.contextualtrigger.DataSources;

import com.example.contextualtrigger.CustomDataTypes.Food;

import java.util.ArrayList;

public class FoodDataSource {

    ArrayList<Food> food;

    public FoodDataSource() {
        food = new ArrayList<>();
        createList();
    }

    //Could add more
    private void createList(){
        Food f1 = new Food("Apple", 200, 1);
        Food f2 = new Food("Bar of Chocolate", 400, 2);
        Food f3 = new Food("Slice of Pizza", 285, 3);
        Food f4 = new Food("Bottle of Coke", 180,4);
        Food f5 = new Food("Bag of Kettle Chips", 154, 5);

        food.add(f1);
        food.add(f2);
        food.add(f3);
        food.add(f4);
        food.add(f5);
    }


    //call this within the calorie trigger to get info
    //instead of using the DB
    public ArrayList<Food> getFood(){
        return food;
    }
}
