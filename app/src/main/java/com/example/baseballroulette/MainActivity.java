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
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.titleTxt)
    TextView titleTxt;


    //I received the background image of the baseball in a field from, https://unsplash.com/wallpapers/sports/baseball
    //I do not intend to distribute or profit from this app
    //I received significant help from Dr. Stonedahl in the debugging process and some elements of refractoring my code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startBtn = findViewById(R.id.startBtn);
        titleTxt = findViewById(R.id.titleTxt);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GamePage.class);
                startActivity(intent);
            }
        });
    }
}