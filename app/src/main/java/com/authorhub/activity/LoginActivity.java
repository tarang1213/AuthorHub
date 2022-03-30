package com.authorhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.authorhub.R;
import com.authorhub.models.RegisterModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnLogin;
    TextView tvsignin;
    ImageView imgLogo;
    Button btnSignInAuthor,btnSignInUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://authorhub-750d5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Register");

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        imgLogo = findViewById(R.id.img_logo);
        tvsignin = findViewById(R.id.tv_signin);


        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View fpView = layoutInflater.inflate(R.layout.signinpag, null);
                btnSignInAuthor = fpView.findViewById(R.id.btn_signInAuthor);
                btnSignInUser = fpView.findViewById(R.id.btn_signInUser);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(fpView);
                alertDialog.show();


                btnSignInUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LoginActivity.this, SignUpUserActivity.class);
                        startActivity(i);
                    }
                });

                btnSignInAuthor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LoginActivity.this, SignUpAuthorActivity.class);
                        startActivity(i);
                    }
                });


            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();
                if (strEmail.equals("")) {
                    edtEmail.setError("Enter Email ID");
                 //  Toast.makeText(LoginActivity.this, " Valid Enter Email ID ", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    edtEmail.setError("Enter valid Email ID");
//                    Toast.makeText(MainActivity.this, "Enter valid Email ID ", Toast.LENGTH_SHORT).show();
                } else if (strPassword.equals("")) {
                    edtPassword.setError("Enter password");
//                    Toast.makeText(MainActivity.this, "Enter password ", Toast.LENGTH_SHORT).show();
                } else if (strPassword.length() < 8) {
                    edtPassword.setError("password must have 8 length");
//                    Toast.makeText(MainActivity.this, "Enter valid password ", Toast.LENGTH_SHORT).show();
                } else {
//                    imgLogo.setImageResource(R.drawable.icon_2);
                    //  databaseReference.setValue("Hello");


                    firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                String strUID = firebaseAuth.getUid();

                                databaseReference.child(strUID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        RegisterModel registerModel = dataSnapshot.getValue(RegisterModel.class);
                                        String loginEmail = registerModel.getUser_email();

                                        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_AUTHOR", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("KEY_PREF_EMAIL", strEmail);
                                        editor.putString("KEY_PREF_Password", strPassword);
                                        editor.commit();


                                        // Explicit Intent
                                        Intent i = new Intent(LoginActivity.this, NavHomeActivity.class);
                                        i.putExtra("KEY_EMAIL", strEmail);
                                        startActivity(i);
                                        finish();


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });
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