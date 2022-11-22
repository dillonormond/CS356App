package com.example.cs356;

public class Bet {
    String teamTaken;
    int teamTakenOdds;
    String teamAvoided;
    int teamAvoidedOdds;
    int amountBet;
    String bookmaker;

    public Bet(String teamTaken, int teamTakenOdds, String teamAvoided, int teamAvoidedOdds, int amountBet, String bookmaker){
        this.teamTaken = teamTaken;
        this.teamTakenOdds = teamTakenOdds;
        this.teamAvoided = teamAvoided;
        this.teamAvoidedOdds =teamAvoidedOdds;
        this.amountBet = amountBet;
        this.bookmaker = bookmaker;


    }




}
