package com.authorhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;

import com.authorhub.R;
import com.authorhub.fragments.SearchFragment;
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

        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_AUTHOR", MODE_PRIVATE);
        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              /*  Intent i = new Intent(SplashActivity.this,NavHomeActivity.class);
                startActivity(i);
                finish();*/
               if (strEmail.equals("")){
                    Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();

                }else {
                    Intent i = new Intent(SplashActivity.this, CatActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        },time);
    }
}