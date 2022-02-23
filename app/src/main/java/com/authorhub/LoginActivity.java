package com.authorhub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnLogin;
    ImageView imgLogo;
    Button btnSignInAuthor,btnSignInUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword=findViewById(R.id.edt_password);
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
                String strPassword=edtPassword.getText().toString();
                if (strEmail.equals("")){
                    edtEmail.setError("Enter Email ID");
//                    Toast.makeText(MainActivity.this, "Enter Email ID ", Toast.LENGTH_SHORT).show();
                }else if(!strEmail.matches(emailPattern)){
                    edtEmail.setError("Enter valid Email ID");
//                    Toast.makeText(MainActivity.this, "Enter valid Email ID ", Toast.LENGTH_SHORT).show();
                }else if(strPassword.equals("")){
                    edtPassword.setError("Enter password");
//                    Toast.makeText(MainActivity.this, "Enter password ", Toast.LENGTH_SHORT).show();
                }
                else if(strPassword.length()<8){
                    edtPassword.setError("Enter valid password");
//                    Toast.makeText(MainActivity.this, "Enter valid password ", Toast.LENGTH_SHORT).show();
                }
                else {
//                    imgLogo.setImageResource(R.drawable.icon_2);

                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("KEY_EMAIL",strEmail);
                    startActivity(i);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Author Hub");
        builder.setIcon(R.drawable.ic_home);
        builder.setMessage("Are you sure you want to Exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}