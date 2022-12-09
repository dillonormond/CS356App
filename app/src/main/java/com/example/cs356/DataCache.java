package com.example.cs356;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataCache {
    ArrayList<Bet> betList = new ArrayList<>();
    Set<String> winningTeams = new HashSet<>();
    String topLeft;
    String topRight;
    String bottomLeft;
    String bottomRight;
    String username;
    boolean simulated = false;
    int BANK_ROLL_VALUE = 500;
    private static DataCache instance;

    public static DataCache getInstance(){
        if(instance == null){
            instance = new DataCache();
        }
        return instance;
    }





}

