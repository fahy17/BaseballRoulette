package com.example.baseballroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class GamePage extends AppCompatActivity {

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
    @BindView(R.id.field)
    ImageView field;
    @BindView(R.id.baserunnerFirst)
    ImageView baserunnerFirst;
    @BindView(R.id.baserunnerSecond)
    ImageView baserunnerSecond;
    @BindView(R.id.baserunnerThird)
    ImageView baserunnerThird;


    private GameState currentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        currentState = GameState.getCurrentGameState();

        String lastHitType = getIntent().getStringExtra(swingPage.HIT_TYPE_EXTRA);
        if (lastHitType != null){
            currentState.getHitResult(lastHitType);
        }

        swingBtn = findViewById(R.id.swingBtn);

        swingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),swingPage.class);
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
    }

    private void updateField(){
        homeScoreTxt.setText(currentState.runs);
        outsTxt.setText(currentState.outs);
        List baserunnerList = Arrays.asList(currentState.baserunners);

        if(baserunnerList.contains(1)){
            baserunnerFirst.setVisibility(View.VISIBLE);
        } else{
            baserunnerFirst.setVisibility(View.INVISIBLE);
        }
        if(baserunnerList.contains(2)){
            baserunnerSecond.setVisibility(View.VISIBLE);
        } else{
            baserunnerSecond.setVisibility(View.INVISIBLE);
        }
        if(baserunnerList.contains(3)){
            baserunnerThird.setVisibility(View.VISIBLE);
        } else{
            baserunnerThird.setVisibility(View.INVISIBLE);
        }

    }
}