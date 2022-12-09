package com.example.cs356;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{

    private ArrayList<Bet> betList;

    public recyclerAdapter(ArrayList<Bet> betList){
        this.betList = betList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView teamTakenText;
        private TextView teamAvoidedText;
        private TextView amountText;
        private TextView bookmakerText;
        private TextView teamTakenOddsText;
        private TextView teamAvoidedOddsText;
        private TextView winningTeamText;
        private TextView betStatusText;
        boolean stop = false;

        public MyViewHolder(final View view){
            super(view);
            teamTakenText = view.findViewById(R.id.teamTakenRec);
            teamAvoidedText = view.findViewById(R.id.teamAvoidedRec);
            amountText = view.findViewById(R.id.amountBetRec);
            bookmakerText = view.findViewById(R.id.bookmakerRec);
            teamTakenOddsText = view.findViewById(R.id.teamTakenOddsRec);
            teamAvoidedOddsText = view.findViewById(R.id.teamAvoidedOddsRec);
            winningTeamText = view.findViewById(R.id.gameOutcome);
            betStatusText = view.findViewById(R.id.betOutcome);
        }
    }


    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String teamTaken = betList.get(position).teamTaken;
        String teamAvoided = betList.get(position).teamAvoided;
        String teamTakenOdds = Integer.toString(betList.get(position).teamTakenOdds);
        String teamAvoidedOdds = Integer.toString(betList.get(position).teamAvoidedOdds);
        String winnings = Integer.toString(betList.get(position).toWin);

        String amountBet = Integer.toString(betList.get(position).amountBet);

        DataCache cache = DataCache.getInstance();

        if(cache.winningTeams.size() > 0){
            if(betList.get(position).teamTaken.equals("Jazz") || betList.get(position).teamTaken.equals("Knicks")){
                holder.winningTeamText.setText("Winner: Jazz");
                if(betList.get(position).teamTaken.equals("Jazz") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Jazz";
                    betList.get(position).outcomeText = "You won $" + betList.get(position).toWin + "!";
                    cache.BANK_ROLL_VALUE += betList.get(position).toWin + betList.get(position).amountBet;
                    betList.get(position).simulated = true;
                }
                else if(betList.get(position).teamTaken.equals("Knicks") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Jazz";
                    betList.get(position).outcomeText = "Bet Lost";
                    betList.get(position).simulated = true;
                }

            }else if(betList.get(position).teamTaken.equals("Lakers") || betList.get(position).teamTaken.equals("Warriors")){
                holder.winningTeamText.setText("Winner: Warriors");
                if(betList.get(position).teamTaken.equals("Warriors") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Warriors";
                    betList.get(position).outcomeText = "You won $" + betList.get(position).toWin + "!";
                    cache.BANK_ROLL_VALUE += betList.get(position).toWin + betList.get(position).amountBet;
                    betList.get(position).simulated = true;
                }
                else if(betList.get(position).teamTaken.equals("Lakers") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Warriors";
                    betList.get(position).outcomeText = "Bet Lost";
                    betList.get(position).simulated = true;
                }
            }
            else if(betList.get(position).teamTaken.equals("Heat") || betList.get(position).teamTaken.equals("Suns")){
                holder.winningTeamText.setText("Winner: Heat");
                if(betList.get(position).teamTaken.equals("Heat") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Heat";
                    betList.get(position).outcomeText = "You won $" + betList.get(position).toWin + "!";
                    cache.BANK_ROLL_VALUE += betList.get(position).toWin + betList.get(position).amountBet;
                    betList.get(position).simulated = true;
                }
                else if(betList.get(position).teamTaken.equals("Suns") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Heat";
                    betList.get(position).outcomeText = "Bet Lost";
                    betList.get(position).simulated = true;
                }
            }
            else if(betList.get(position).teamTaken.equals("Nuggets") || betList.get(position).teamTaken.equals("Spurs")){
                holder.winningTeamText.setText("Winner: Spurs");
                if(betList.get(position).teamTaken.equals("Spurs") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Spurs";
                    betList.get(position).outcomeText = "You won $" + betList.get(position).toWin + "!";
                    cache.BANK_ROLL_VALUE += betList.get(position).toWin + betList.get(position).amountBet;
                    betList.get(position).simulated = true;
                }
                else if(betList.get(position).teamTaken.equals("Nuggets") && !betList.get(position).simulated){
                    betList.get(position).winningTeam = "Spurs";
                    betList.get(position).outcomeText = "Bet Lost";
                    betList.get(position).simulated = true;
                }
            }
            cache.simulated = false;
        }

        if(!teamTakenOdds.contains("-")){
            teamTakenOdds = "+" + teamTakenOdds;
        }
        if(!teamAvoidedOdds.contains("-")){
            teamAvoidedOdds = "+" + teamAvoidedOdds;
        }

        holder.teamTakenText.setText(teamTaken);
        holder.teamAvoidedText.setText(teamAvoided);
        holder.bookmakerText.setText("To Win: $" + winnings);
        holder.amountText.setText("Wagered: $" + amountBet);
        holder.teamTakenOddsText.setText(teamTakenOdds);
        holder.teamAvoidedOddsText.setText(teamAvoidedOdds);
        holder.winningTeamText.setText("Winning Team: " + betList.get(position).winningTeam);
        holder.betStatusText.setText("Bet Status: " + betList.get(position).outcomeText);


    }

    @Override
    public int getItemCount() {
        return betList.size();
    }
}
