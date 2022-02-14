package com.authorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    Button btnLogin;
    ImageView imgLogo;
    Button btnSignInAuthor,btnSignInUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edt_email);
        btnLogin = findViewById(R.id.btn_login);
        imgLogo  = findViewById(R.id.img_logo);
        btnSignInAuthor = findViewById(R.id.btn_signInAuthor);
        btnSignInUser = findViewById(R.id.btn_signInUser);

        btnSignInUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpUserActivity.class);
                startActivity(i);
            }
        });

        btnSignInAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpAuthorActivity.class);
                startActivity(i);
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString();
                if (strEmail.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter Email ID ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Email ID is  "+strEmail, Toast.LENGTH_SHORT).show();
//                    imgLogo.setImageResource(R.drawable.icon_2);

                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("KEY_EMAIL",strEmail);
                    startActivity(i);

                }
            }
        });
    }
}