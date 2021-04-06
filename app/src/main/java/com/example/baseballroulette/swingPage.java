package com.example.baseballroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class swingPage extends AppCompatActivity {

    @BindView(R.id.startWheelBtn)
    Button startWheelBtn;
    @BindView(R.id.wheelResult)
    TextView wheelResult;
    @BindView(R.id.wheelImg)
    ImageView wheelImg;

    private static final String[] wheelSections = {"Out", "Single", "Single", "Out", "Home Run", "Out",
            "Double", "Out", "Double", "Single", "Out", "Triple"};


    private static final Random RAND_SPIN = new Random();
    private int degree = 0, degreeOld = 0;
    // There are 12 sections on the wheel, so divide 360/12
    // we divide by 2 to have a half sector
    private static final float HALF_SECTOR = 360f / 12f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swing_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startWheelBtn)
    public void spin(View v) {
        degreeOld = degree % 360;
        // we calculate random angle for rotation of our wheel and add 1080 so the wheel spins at least 3 times
        degree = RAND_SPIN.nextInt(360) + 1080;
        // rotation effect on the center of the wheel
        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // we empty the result text view when the animation start
                wheelResult.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // we display the correct sector pointed by the triangle at the end of the rotate animation
                wheelResult.setText(getSector(360 - (degree % 360)));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        // we start the animation
        wheelImg.startAnimation(rotateAnim);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(),GamePage.class);
        startActivity(intent);

    }

    private String getSector(int degrees) {
        int i = 0;
        String text = null;

        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = wheelSections[i];
            }

            i++;
            // now we can test our Android Roulette Game :)
            // That's all !
            // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
            // Subscribe and stay tuned !

        } while (text == null  &&  i < wheelSections.length);

        return text;
    }
}