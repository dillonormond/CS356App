package com.example.cs356;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BetFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BetFragment() {
        // Required empty public constructor
    }

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BetFragment newInstance(String param1, String param2) {
        BetFragment fragment = new BetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bet, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        TextView topLeftHomeTeam = getView().findViewById(R.id.topLeftHomeTeam);
        TextView topLeftHomeOdds = getView().findViewById(R.id.topLeftHomeOdds);
        TextView topLeftAwayTeam = getView().findViewById(R.id.topLeftAwayTeam);
        TextView topLeftAwayOdds = getView().findViewById(R.id.topLeftAwayOdds);
        TextView topLeftWinnings = getView().findViewById(R.id.topLeftWinnings);
        EditText topLeftAmount = getView().findViewById(R.id.topLeftAmount);

        TextView topRightHomeTeam = getView().findViewById(R.id.topRightHomeTeam);
        TextView topRightHomeOdds = getView().findViewById(R.id.topRightHomeOdds);
        TextView topRightAwayTeam = getView().findViewById(R.id.topRightAwayTeam);
        TextView topRightAwayOdds = getView().findViewById(R.id.topRightAwayOdds);
        TextView topRightWinnings = getView().findViewById(R.id.topRightWinnings);
        EditText topRightAmount = getView().findViewById(R.id.topRightAmount);

        TextView bottomLeftHomeTeam = getView().findViewById(R.id.bottomLeftHomeTeam);
        TextView bottomLeftHomeOdds = getView().findViewById(R.id.bottomLeftHomeOdds);
        TextView bottomLeftAwayTeam = getView().findViewById(R.id.bottomLeftAwayTeam);
        TextView bottomLeftAwayOdds = getView().findViewById(R.id.bottomLeftAwayOdds);
        TextView bottomLeftWinnings = getView().findViewById(R.id.bottomLeftWinnings);
        EditText bottomLeftAmount = getView().findViewById(R.id.bottomLeftAmount);

        TextView bottomRightHomeTeam = getView().findViewById(R.id.bottomRightHomeTeam);
        TextView bottomRightHomeOdds = getView().findViewById(R.id.bottomRightHomeOdds);
        TextView bottomRightAwayTeam = getView().findViewById(R.id.bottomRightAwayTeam);
        TextView bottomRightAwayOdds = getView().findViewById(R.id.bottomRightAwayOdds);
        EditText bottomRightAmount = getView().findViewById(R.id.bottomRightAmount);
        TextView bottomRightWinnings = getView().findViewById(R.id.bottomRightWinnings);

        TextView clearButton = getView().findViewById(R.id.clear);

        TextView bankRoll = getView().findViewById(R.id.bankRollHome);
        DataCache cache = DataCache.getInstance();
        String bankRollString = "Bank Total: $" + Integer.toString(cache.BANK_ROLL_VALUE);
        bankRoll.setText(bankRollString);


        MaterialButton submit = getView().findViewById(R.id.submitbutton);
        ImageView infoButton = getView().findViewById(R.id.topLeftInfo);


        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOddsPopup();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cache.betList = new ArrayList<>();
                cache.winningTeams = new HashSet<>();

            }
        });



        topLeftAwayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topLeftAwayTeam.setBackgroundResource(R.drawable.customborderdark);
                topLeftAwayTeam.setPadding(14,14,14,14);
                topLeftHomeTeam.setBackgroundResource(R.drawable.customborderred);
                topLeftHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.topLeft = "away";
            }
        });

        topLeftHomeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topLeftAwayTeam.setBackgroundResource(R.drawable.customborderred);
                topLeftAwayTeam.setPadding(14,14,14,14);
                topLeftHomeTeam.setBackgroundResource(R.drawable.customborderdark);
                topLeftHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.topLeft = "home";
            }
        });

        topLeftAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
//TODO fix bugs when no amount is entered and when no team is selected
            @Override
            public void afterTextChanged(Editable editable) {
                int amountEntered;
                System.out.println("debug");
                if(!editable.toString().equals("")) {
                    amountEntered = Integer.parseInt(topLeftAmount.getText().toString());
                }
                else{
                    amountEntered = 0;
                }
                int odds;
                DataCache cache = DataCache.getInstance();
                if(cache.topLeft != null){
                    if(cache.topLeft.equals("home")){
                        odds = Integer.parseInt(topLeftHomeOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                topLeftWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                topLeftWinnings.setText(winnings);
                            }
                        }
                        else{
                            topLeftWinnings.setText("Enter Wager");
                        }

                    }
                    else if(cache.topLeft.equals("away")){
                        odds = Integer.parseInt(topLeftAwayOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                topLeftWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                topLeftWinnings.setText(winnings);
                            }
                        }
                        else{
                            topLeftWinnings.setText("Enter Wager");
                        }
                    }
                }
                else{
                    topLeftWinnings.setText("Choose Team");
                }
            }
        });


        topRightAwayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRightAwayTeam.setBackgroundResource(R.drawable.customborderdark);
                topRightAwayTeam.setPadding(14,14,14,14);
                topRightHomeTeam.setBackgroundResource(R.drawable.customborderred);
                topRightHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.topRight = "away";
            }
        });

        topRightHomeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRightAwayTeam.setBackgroundResource(R.drawable.customborderred);
                topRightAwayTeam.setPadding(14,14,14,14);
                topRightHomeTeam.setBackgroundResource(R.drawable.customborderdark);
                topRightHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.topRight = "home";
            }
        });

        topRightAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //TODO fix bugs when no amount is entered and when no team is selected
            @Override
            public void afterTextChanged(Editable editable) {
                int amountEntered;
                System.out.println("debug");
                if(!editable.toString().equals("")) {
                    amountEntered = Integer.parseInt(topRightAmount.getText().toString());
                }
                else{
                    amountEntered = 0;
                }
                int odds;
                DataCache cache = DataCache.getInstance();
                if(cache.topRight != null){
                    if(cache.topRight.equals("home")){
                        odds = Integer.parseInt(topRightHomeOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                topRightWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                topRightWinnings.setText(winnings);
                            }
                        }
                        else{
                            topRightWinnings.setText("Enter Wager");
                        }

                    }
                    else if(cache.topRight.equals("away")){
                        odds = Integer.parseInt(topRightAwayOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                topRightWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                topRightWinnings.setText(winnings);
                            }
                        }
                        else{
                            topRightWinnings.setText("Enter Wager");
                        }
                    }
                }
                else{
                    topRightWinnings.setText("Choose Team");
                }
            }
        });


        bottomLeftAwayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomLeftAwayTeam.setBackgroundResource(R.drawable.customborderdark);
                bottomLeftAwayTeam.setPadding(14,14,14,14);
                bottomLeftHomeTeam.setBackgroundResource(R.drawable.customborderred);
                bottomLeftHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.bottomLeft = "away";
            }
        });

        bottomLeftHomeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomLeftAwayTeam.setBackgroundResource(R.drawable.customborderred);
                bottomLeftAwayTeam.setPadding(14,14,14,14);
                bottomLeftHomeTeam.setBackgroundResource(R.drawable.customborderdark);
                bottomLeftHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.bottomLeft = "home";
            }
        });

        bottomLeftAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //TODO fix bugs when no amount is entered and when no team is selected
            @Override
            public void afterTextChanged(Editable editable) {
                int amountEntered;
                System.out.println("debug");
                if(!editable.toString().equals("")) {
                    amountEntered = Integer.parseInt(bottomLeftAmount.getText().toString());
                }
                else{
                    amountEntered = 0;
                }
                int odds;
                DataCache cache = DataCache.getInstance();
                if(cache.bottomLeft != null){
                    if(cache.bottomLeft.equals("home")){
                        odds = Integer.parseInt(bottomLeftHomeOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomLeftWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomLeftWinnings.setText(winnings);
                            }
                        }
                        else{
                            bottomLeftWinnings.setText("Enter Wager");
                        }

                    }
                    else if(cache.bottomLeft.equals("away")){
                        odds = Integer.parseInt(bottomLeftAwayOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomLeftWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomLeftWinnings.setText(winnings);
                            }
                        }
                        else{
                            bottomLeftWinnings.setText("Enter Wager");
                        }
                    }
                }
                else{
                    bottomLeftWinnings.setText("Choose Team");
                }
            }
        });


        bottomRightAwayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomRightAwayTeam.setBackgroundResource(R.drawable.customborderdark);
                bottomRightAwayTeam.setPadding(14,14,14,14);
                bottomRightHomeTeam.setBackgroundResource(R.drawable.customborderred);
                bottomRightHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.bottomRight = "away";
            }
        });

        bottomRightHomeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomRightAwayTeam.setBackgroundResource(R.drawable.customborderred);
                bottomRightAwayTeam.setPadding(14,14,14,14);
                bottomRightHomeTeam.setBackgroundResource(R.drawable.customborderdark);
                bottomRightHomeTeam.setPadding(14,14,14,14);
                DataCache cache = DataCache.getInstance();
                cache.bottomRight = "home";
            }
        });

        bottomRightAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //TODO fix bugs when no amount is entered and when no team is selected
            @Override
            public void afterTextChanged(Editable editable) {
                int amountEntered;
                System.out.println("debug");
                if(!editable.toString().equals("")) {
                    amountEntered = Integer.parseInt(bottomRightAmount.getText().toString());
                }
                else{
                    amountEntered = 0;
                }
                int odds;
                DataCache cache = DataCache.getInstance();
                if(cache.bottomRight != null){
                    if(cache.bottomRight.equals("home")){
                        odds = Integer.parseInt(bottomRightHomeOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomRightWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomRightWinnings.setText(winnings);
                            }
                        }
                        else{
                            bottomRightWinnings.setText("Enter Wager");
                        }

                    }
                    else if(cache.bottomRight.equals("away")){
                        odds = Integer.parseInt(bottomRightAwayOdds.getText().toString());
                        if(amountEntered > 0){
                            if(odds > 0){
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomRightWinnings.setText(winnings);
                            }
                            else{
                                String winnings = calculateWinnings(odds, amountEntered);
                                bottomRightWinnings.setText(winnings);
                            }
                        }
                        else{
                            bottomRightWinnings.setText("Enter Wager");
                        }
                    }
                }
                else{
                    bottomRightWinnings.setText("Choose Team");
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataCache cache = DataCache.getInstance();
                int runningTotal = 0;


                if(cache.topLeft != null && !topLeftAmount.getText().toString().equals("")){
                    runningTotal += Integer.parseInt(topLeftAmount.getText().toString());
                    if(cache.topLeft.equals("home")){
                        String debug = topLeftWinnings.getText().toString();
                        int test = Integer.parseInt(topLeftWinnings.getText().toString());
                        Bet newBet = new Bet(topLeftHomeTeam.getText().toString(), Integer.parseInt(topLeftHomeOdds.getText().toString()), topLeftAwayTeam.getText().toString(),
                                Integer.parseInt(topLeftAwayOdds.getText().toString()), Integer.parseInt(topLeftAmount.getText().toString()), Integer.parseInt(topLeftWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.topLeft="submitted";

                        topLeftAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        topLeftAwayTeam.setPadding(14,14,14,14);
                        topLeftHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        topLeftHomeTeam.setPadding(14,14,14,14);
                        topLeftAmount.setText("");
                        topLeftWinnings.setText("");

                    }
                    else if(cache.topLeft.equals("away")){
                        Bet newBet = new Bet(topLeftAwayTeam.getText().toString(), Integer.parseInt(topLeftAwayOdds.getText().toString()), topLeftHomeTeam.getText().toString(),
                                Integer.parseInt(topLeftHomeOdds.getText().toString()), Integer.parseInt(topLeftAmount.getText().toString()),Integer.parseInt(topLeftWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.topLeft="submitted";

                        topLeftAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        topLeftAwayTeam.setPadding(14,14,14,14);
                        topLeftHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        topLeftHomeTeam.setPadding(14,14,14,14);
                        topLeftAmount.setText("");
                        topLeftWinnings.setText("");
                    }
                }


                if(cache.topRight != null && !topRightAmount.getText().toString().equals("")){
                    runningTotal += Integer.parseInt(topRightAmount.getText().toString());
                    if(cache.topRight.equals("home")){
                        Bet newBet = new Bet(topRightHomeTeam.getText().toString(), Integer.parseInt(topRightHomeOdds.getText().toString()), topRightAwayTeam.getText().toString(),
                                Integer.parseInt(topRightAwayOdds.getText().toString()), Integer.parseInt(topRightAmount.getText().toString()),Integer.parseInt(topRightWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.topRight="submitted";

                        topRightAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        topRightAwayTeam.setPadding(14,14,14,14);
                        topRightHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        topRightHomeTeam.setPadding(14,14,14,14);
                        topRightAmount.setText("");
                        topRightWinnings.setText("");
                    }
                    else if(cache.topRight.equals("away")){
                        Bet newBet = new Bet(topRightAwayTeam.getText().toString(), Integer.parseInt(topRightAwayOdds.getText().toString()), topRightHomeTeam.getText().toString(),
                                Integer.parseInt(topRightHomeOdds.getText().toString()), Integer.parseInt(topRightAmount.getText().toString()),Integer.parseInt(topRightWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.topRight="submitted";

                        topRightAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        topRightAwayTeam.setPadding(14,14,14,14);
                        topRightHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        topRightHomeTeam.setPadding(14,14,14,14);
                        topRightAmount.setText("");
                        topRightWinnings.setText("");
                    }
                }

                if(cache.bottomLeft != null && !bottomLeftAmount.getText().toString().equals("")){
                    runningTotal += Integer.parseInt(bottomLeftAmount.getText().toString());
                    if(cache.bottomLeft.equals("home")){
                        Bet newBet = new Bet(bottomLeftHomeTeam.getText().toString(), Integer.parseInt(bottomLeftHomeOdds.getText().toString()), bottomLeftAwayTeam.getText().toString(),
                                Integer.parseInt(bottomLeftAwayOdds.getText().toString()), Integer.parseInt(bottomLeftAmount.getText().toString()),Integer.parseInt(bottomLeftWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.bottomLeft="submitted";

                        bottomLeftAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomLeftAwayTeam.setPadding(14,14,14,14);
                        bottomLeftHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomLeftHomeTeam.setPadding(14,14,14,14);
                        bottomLeftAmount.setText("");
                        bottomLeftWinnings.setText("");
                    }
                    else if(cache.bottomLeft.equals("away")){
                        Bet newBet = new Bet(bottomLeftAwayTeam.getText().toString(), Integer.parseInt(bottomLeftAwayOdds.getText().toString()), bottomLeftHomeTeam.getText().toString(),
                                Integer.parseInt(bottomLeftHomeOdds.getText().toString()), Integer.parseInt(bottomLeftAmount.getText().toString()),Integer.parseInt(bottomLeftWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.bottomLeft="submitted";

                        bottomLeftAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomLeftAwayTeam.setPadding(14,14,14,14);
                        bottomLeftHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomLeftHomeTeam.setPadding(14,14,14,14);
                        bottomLeftAmount.setText("");
                        bottomLeftWinnings.setText("");
                    }
                }

                if(cache.bottomRight != null && !bottomRightAmount.getText().toString().equals("")){
                    runningTotal += Integer.parseInt(bottomRightAmount.getText().toString());
                    if(cache.bottomRight.equals("home")){
                        Bet newBet = new Bet(bottomRightHomeTeam.getText().toString(), Integer.parseInt(bottomRightHomeOdds.getText().toString()), bottomRightAwayTeam.getText().toString(),
                                Integer.parseInt(bottomRightAwayOdds.getText().toString()), Integer.parseInt(bottomRightAmount.getText().toString()),Integer.parseInt(bottomRightWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.bottomRight="submitted";

                        bottomRightAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomRightAwayTeam.setPadding(14,14,14,14);
                        bottomRightHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomRightHomeTeam.setPadding(14,14,14,14);
                        bottomRightAmount.setText("");
                        bottomRightWinnings.setText("");
                    }
                    else if(cache.bottomRight.equals("away")){
                        Bet newBet = new Bet(bottomRightAwayTeam.getText().toString(), Integer.parseInt(bottomRightAwayOdds.getText().toString()), bottomRightHomeTeam.getText().toString(),
                                Integer.parseInt(bottomRightHomeOdds.getText().toString()), Integer.parseInt(bottomRightAmount.getText().toString()),Integer.parseInt(bottomRightWinnings.getText().toString()));
                        cache.betList.add(newBet);
                        cache.bottomRight="submitted";

                        bottomRightAwayTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomRightAwayTeam.setPadding(14,14,14,14);
                        bottomRightHomeTeam.setBackgroundResource(R.drawable.customborderlight);
                        bottomRightHomeTeam.setPadding(14,14,14,14);
                        bottomRightAmount.setText("");
                        bottomRightWinnings.setText("");
                    }
                }
                cache.BANK_ROLL_VALUE = cache.BANK_ROLL_VALUE - runningTotal;
                bankRoll.setText("Bank Total: $" + Integer.toString(cache.BANK_ROLL_VALUE));
            }

        });



    }


    public String calculateWinnings(int odds, int amountEntered){
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
        if(odds > 0){
            float winnings = (float) odds / 100 *amountEntered;
            winnings = winnings + amountEntered;
            return String.valueOf(df.format(winnings));
        }
        else{
            odds = Math.abs(odds);
            float winnings = 100 / (float) odds * amountEntered;
            winnings = winnings + amountEntered;
            return String.valueOf(df.format(winnings));
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public void createOddsPopup(){
        dialogBuilder = new AlertDialog.Builder(this.getContext());
        final View PopupView = getLayoutInflater().inflate(R.layout.popup,null);
        dialogBuilder.setView(PopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

}