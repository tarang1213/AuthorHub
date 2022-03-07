package com.authorhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.authorhub.R;
import com.authorhub.utils.GifImageView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    int time=2000;
    GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gifImageView = findViewById(R.id.img_gif);
        gifImageView.setGifImageResource(R.drawable.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,NavHomeActivity.class);
                startActivity(i);
                finish();
            }
        },time);
    }
}