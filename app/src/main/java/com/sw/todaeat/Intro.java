package com.sw.todaeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Intro extends AppCompatActivity {

    ImageView appName, splashImg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-1800).setDuration(1000).setStartDelay(4000);

        appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Intro.this, MainActivity.class));
                finish();
            }
        }, 5900);

    }

}
