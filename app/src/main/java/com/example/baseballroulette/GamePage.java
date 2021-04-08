package com.example.baseballroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GamePage extends AppCompatActivity {



    //I received help from Dr. Stonedahl in this section in moving around methods and values to better
    //fit the flow of my app as well as improve functionality.

    //I received the baseball field image here, https://creazilla.com/nodes/76536-baseball-diamond-clipart for recreational use.
    //I received the baseball player running image here, http://www.clipartpanda.com/clipart_images/a-player-running-bases-43840251 for recreational use.

    @BindView(R.id.swingBtn)
    Button swingBtn;
    @BindView(R.id.homeLabel)
    TextView homeLabel;
    @BindView(R.id.homeScoreTxt)
    TextView homeScoreTxt;
    @BindView(R.id.awayLabel)
    TextView awayLabel;
    @BindView(R.id.awayScoreTxt)
    TextView awayScoreTxt;
    @BindView(R.id.outsTxt)
    TextView outsTxt;
    @BindView(R.id.inningLabel)
    TextView inningLabel;
    @BindView(R.id.inningNumTxt)
    TextView inningNumTxt;
    @BindView(R.id.homeBattingLabel)
    TextView homeBattingLabel;
    @BindView(R.id.awayBattingLabel)
    TextView awayBattingLabel;
    @BindView(R.id.hitTypeTxt)
    TextView hitTypeTxt;
    @BindView(R.id.field)
    ImageView field;
    @BindView(R.id.baserunnerFirst)
    ImageView baserunnerFirst;
    @BindView(R.id.baserunnerSecond)
    ImageView baserunnerSecond;
    @BindView(R.id.baserunnerThird)
    ImageView baserunnerThird;


    private GameState currentState;
    public static final int MAX_INNINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        ButterKnife.bind(this);


        currentState = GameState.getCurrentGameState();
        updateField();

        String lastHitType = getIntent().getStringExtra(SwingPage.HIT_TYPE_EXTRA);
        if (lastHitType != null){
            Log.d("hitType", lastHitType);
            currentState.processHitType(lastHitType);
            updateField();
            if(currentState.getInning()>MAX_INNINGS){

                endGame();
            }
            Log.d("triedHitandUpdate", "hit type was not null");
        }

        swingBtn = findViewById(R.id.swingBtn);

        swingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SwingPage.class);
                startActivity(intent);
            }
        });
    }



    /*public void addRun() {
        runs++;
        homeScoreTxt.setText(runs);
    }

    public void addOut(){
        outs++;
        if (outs > 2){
            endGame();
        }
    }*/

    private void endGame() {
        Log.d("END", "tried to open post game");
        Intent intent = new Intent(getApplicationContext(),PostGame.class);

        intent.putExtra("HOME_HITS", currentState.getHomeHits());
        intent.putExtra("AWAY_HITS", currentState.getAwayHits());
        intent.putExtra("HOME_ABS", currentState.getHomeABs());
        intent.putExtra("AWAY_ABS", currentState.getAwayABs());
        intent.putExtra("HOME_HIT_TYPES", currentState.getHomeHitTypes());
        intent.putExtra("AWAY_HIT_TYPES", currentState.getAwayHitTypes());
        intent.putExtra("HOME_RUNS", currentState.getHomeNumRuns());
        intent.putExtra("AWAY_RUNS", currentState.getAwayNumRuns());
        startActivity(intent);
    }

    private void updateField(){


        Log.d("UpdateField", "Update Field is running");


        homeScoreTxt.setText(String.valueOf(currentState.getHomeNumRuns()));
        awayScoreTxt.setText(String.valueOf(currentState.getAwayNumRuns()));
        inningNumTxt.setText(String.valueOf(currentState.getInning()));
        outsTxt.setText(String.valueOf(currentState.getOuts()));
        hitTypeTxt.setText(currentState.getLastHitType());

        if(currentState.isHomeBatting()){
            homeBattingLabel.setVisibility(View.VISIBLE);
            awayBattingLabel.setVisibility(View.INVISIBLE);
        } else {
            homeBattingLabel.setVisibility(View.INVISIBLE);
            awayBattingLabel.setVisibility(View.VISIBLE);
        }

        Log.d("out number", String.valueOf(currentState.getOuts()));
        Log.d("home runs number", String.valueOf(currentState.getHomeNumRuns()));
        Log.d("away runs number", String.valueOf(currentState.getAwayNumRuns()));


        if(currentState.isRunnerOnBase(1)){
            baserunnerFirst.setVisibility(View.VISIBLE);
            Log.d("Single", "Runner on First");
        } else{
            baserunnerFirst.setVisibility(View.INVISIBLE);
        }
        if(currentState.isRunnerOnBase(2)){
            baserunnerSecond.setVisibility(View.VISIBLE);
            Log.d("Double", "Runner on second");
        } else{
            baserunnerSecond.setVisibility(View.INVISIBLE);
        }
        if(currentState.isRunnerOnBase(3)){
            baserunnerThird.setVisibility(View.VISIBLE);
            Log.d("Triple", "runner on third");
        } else{
            baserunnerThird.setVisibility(View.INVISIBLE);
        }

    }
}