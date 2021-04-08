package com.example.baseballroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostGame extends AppCompatActivity {

    @BindView(R.id.menuBtn)
    Button menuBtn;
    @BindView(R.id.playAgainBtn)
    Button playAgainBtn;
    @BindView(R.id.homeBALabel)
    TextView homeBALabel;
    @BindView(R.id.homeDoublesLabel)
    TextView homeDoublesLabel;
    @BindView(R.id.homeHomeRunsLabel)
    TextView homeHomeRunsLabel;
    @BindView(R.id.homeRunsLabel)
    TextView homeRunsLabel;
    @BindView(R.id.homeSInglesLabel)
    TextView homeSinglesLabel;
    @BindView(R.id.homeTriplesLabel)
    TextView homeTriplesLabel;
    @BindView(R.id.aBALabel)
    TextView aBALabel;
    @BindView(R.id.aDoublesLabel)
    TextView aDoublesLabel;
    @BindView(R.id.aSinglesLabel)
    TextView aSinglesLabel;
    @BindView(R.id.aTriplesLabel)
    TextView aTriplesLabel;
    @BindView(R.id.aHomeRunsLabel)
    TextView aHomeRunsLabel;
    @BindView(R.id.aRunsLabel)
    TextView aRunsLabel;

    public double homeBA;
    public double awayBA;
    public double homeABs;
    public double awayABs;
    public int homeHitsFinal;
    public int awayHitsFinal;
    public int homeRunsFinal;
    public int awayRunsFinal;
    public int[] homeHitTypesFinal;
    public int[] awayHitTypesFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);
        ButterKnife.bind(this);

        homeABs = (double) getIntent().getIntExtra("HOME_ABS", 0);
        awayABs = (double) getIntent().getIntExtra("AWAY_ABS", 0);
        homeHitsFinal = getIntent().getIntExtra("HOME_HITS", 0);
        awayHitsFinal = getIntent().getIntExtra("AWAY_HITS", 0);

        homeBA = ((double)homeHitsFinal)/homeABs;
        awayBA = ((double)awayHitsFinal)/awayABs;

        homeRunsFinal = getIntent().getIntExtra("HOME_RUNS", 0);
        awayRunsFinal = getIntent().getIntExtra("AWAY_RUNS", 0);
        homeHitTypesFinal = getIntent().getIntArrayExtra("HOME_HIT_TYPES");
        awayHitTypesFinal = getIntent().getIntArrayExtra("AWAY_HIT_TYPES");


        homeBALabel.setText(String.valueOf(0.350));
        aBALabel.setText(String.valueOf(0.275));
        homeRunsLabel.setText(String.valueOf(homeRunsFinal));
        aRunsLabel.setText(String.valueOf(awayRunsFinal));
        homeSinglesLabel.setText(String.valueOf(homeHitTypesFinal[0]));
        homeDoublesLabel.setText(String.valueOf(homeHitTypesFinal[1]));
        homeTriplesLabel.setText(String.valueOf(homeHitTypesFinal[2]));
        homeHomeRunsLabel.setText(String.valueOf(homeHitTypesFinal[3]));
        aSinglesLabel.setText(String.valueOf(awayHitTypesFinal[0]));
        aDoublesLabel.setText(String.valueOf(awayHitTypesFinal[1]));
        aTriplesLabel.setText(String.valueOf(awayHitTypesFinal[2]));
        aHomeRunsLabel.setText(String.valueOf(awayHitTypesFinal[3]));





    }

    @OnClick(R.id.menuBtn)
    public void openMenu(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.playAgainBtn)
    public void playAgain(){
        Intent intent = new Intent(getApplicationContext(),GamePage.class);
        startActivity(intent);
    }
}