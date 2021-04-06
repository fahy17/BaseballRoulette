package com.example.baseballroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;


public class GamePage extends AppCompatActivity {

    public int runs;
    public int[] baserunners = new int[3];
    public int outs;

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
    @BindView(R.id.field)
    ImageView field;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        swingBtn = findViewById(R.id.swingBtn);

        swingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),swingPage.class);
                startActivity(intent);
            }
        });
    }

    public void hitSingle(){
        boolean runnerAdded = false;
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]++;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            } else if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 1;
                runnerAdded = true;
            }
        }
    }

    public void hitDouble(){
        boolean runnerAdded = false;
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]+=2;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            } else if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 2;
                runnerAdded = true;
            }
        }
    }

    public void hitTriple(){
        boolean runnerAdded = false;
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]+=3;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            } else if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 3;
                runnerAdded = true;
            }
        }
    }

    public void hitHomeRun(){
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]+=4;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            }
            addRun();
        }
    }

    public void addRun() {
        runs++;
        homeScoreTxt.setText(runs);
    }

    public void addOut(){
        outs++;
        if (outs > 2){
            endGame();
        }
    }

    private void endGame() {
    }
}