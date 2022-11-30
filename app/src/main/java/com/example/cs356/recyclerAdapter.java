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

        public MyViewHolder(final View view){
            super(view);
            teamTakenText = view.findViewById(R.id.teamTakenRec);
            teamAvoidedText = view.findViewById(R.id.teamAvoidedRec);
            amountText = view.findViewById(R.id.amountBetRec);
            bookmakerText = view.findViewById(R.id.bookmakerRec);
            teamTakenOddsText = view.findViewById(R.id.teamTakenOddsRec);
            teamAvoidedOddsText = view.findViewById(R.id.teamAvoidedOddsRec);
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


    }

    @Override
    public int getItemCount() {
        return betList.size();
    }
}
