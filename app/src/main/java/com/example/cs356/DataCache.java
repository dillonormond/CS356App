package com.example.cs356;

import java.util.ArrayList;
import java.util.List;

public class DataCache {
    ArrayList<Bet> betList = new ArrayList<>();
    String topLeft;
    String topRight;
    String bottomLeft;
    String bottomRight;
    String username;
    private static DataCache instance;

    public static DataCache getInstance(){
        if(instance == null){
            instance = new DataCache();
        }
        return instance;
    }





}

