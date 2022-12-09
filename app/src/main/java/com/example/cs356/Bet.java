package com.example.cs356;

public class Bet {
    String teamTaken;
    int teamTakenOdds;
    String teamAvoided;
    int teamAvoidedOdds;
    int amountBet;
    int toWin;
    boolean successful = false;
    boolean simulated = false;
    String winningTeam = "TBD";
    String outcomeText = "TBD";


    public Bet(String teamTaken, int teamTakenOdds, String teamAvoided, int teamAvoidedOdds, int amountBet, int toWin){
        this.teamTaken = teamTaken;
        this.teamTakenOdds = teamTakenOdds;
        this.teamAvoided = teamAvoided;
        this.teamAvoidedOdds =teamAvoidedOdds;
        this.amountBet = amountBet;
        this.toWin = toWin;

        DataCache cache = DataCache.getInstance();
        if(cache.winningTeams.contains(teamTaken)){
            successful = true;
        }


    }




}
