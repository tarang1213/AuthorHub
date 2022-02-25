package com.authorhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.authorhub.R;

public class MainActivity extends AppCompatActivity {

    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmail = findViewById(R.id.tv_email);
        Intent i=getIntent();
        String strEmail = i.getStringExtra("KEY_EMAIL");
        tvEmail.setText(strEmail);
    }
}