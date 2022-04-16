package com.example.contextualtrigger.DataSources;

import java.util.ArrayList;

public class MonumentList {

    ArrayList<MonList> MonList;

    public MonumentList() {
        MonList = new ArrayList<>();
        createList();
    }

    //Could add more
    private void createList(){
        MonList f1 = new MonList("Christ the Redeemer", 220, 0);
        MonList f2 = new MonList("Big Ben", 334, 1);
        MonList f3 = new MonList("Empire State Building", 1860, 2);
        MonList f4 = new MonList("Burj Khalifa - The Tallest Building in the world!", 2909, 3);
        MonList f5 = new MonList("Shanghai Tower", 3398, 4);
        MonList f6 = new MonList("Niesen Mountain - the worlds longest staircase!", 11674 ,5 );
        MonList f7 = new MonList("Ben Nevis", 22197, 6);
        MonList f8 = new MonList("Mt. Everest", 44250, 7);


        MonList.add(f1);
        MonList.add(f2);
        MonList.add(f3);
        MonList.add(f4);
        MonList.add(f5);
        MonList.add(f6);
        MonList.add(f7);
        MonList.add(f8);
    }
}
