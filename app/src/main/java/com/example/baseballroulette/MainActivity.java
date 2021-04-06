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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.settingsBtn)
    Button settingsBtn;
    @BindView(R.id.scoresBtn)
    Button scoresBtn;
    @BindView(R.id.titleTxt)
    TextView titleTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        scoresBtn = findViewById(R.id.scoresBtn);
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