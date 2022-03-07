package com.authorhub.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.content.Context;

import com.authorhub.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpAuthorActivity extends AppCompatActivity {

    EditText edtName,edtEmail,edtNewPassword,edtConfirmPassword;
    TextView tvLogin;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    CircleImageView circleImageView;
    Button btnGallery,btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_author);
        edtName=findViewById(R.id.edt_name);
        edtEmail=findViewById(R.id.edt_email);
        edtNewPassword=findViewById(R.id.edt_newPassword);
        edtConfirmPassword=findViewById(R.id.edt_confirmPassword);
        btnRegister=findViewById(R.id.btn_register);
        tvLogin=findViewById(R.id.tv_login);
        circleImageView = findViewById(R.id.img_DP);


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View fpView = layoutInflater.inflate(R.layout.raw_dp, null);
                ImageView imgGallery = fpView.findViewById(R.id.img_gallery);
                ImageView imgCamera = fpView.findViewById(R.id.img_camera);
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpAuthorActivity.this);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(fpView);
                alertDialog.show();

                imgGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SignUpAuthorActivity.this, "Submit", Toast.LENGTH_SHORT).show();

                        if (alertDialog.isShowing()) {

                            alertDialog.dismiss();
                        }


                        Intent i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_PICK);
                        startActivityForResult(i, 11);
                    }
                });

                imgCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SignUpAuthorActivity.this, "Submit", Toast.LENGTH_SHORT).show();

                        if (alertDialog.isShowing()){
                            alertDialog.dismiss();
                        }

                        Intent i = new Intent();
                        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,12);
                    }
                });


            }
        });

       /* btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_PICK);
                startActivityForResult(i,11);

            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,12);
            }
        });*/


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName=edtName.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strNewPassword=edtNewPassword.getText().toString();
                String strConfirmPassword=edtConfirmPassword.getText().toString();
                if (strName.equals("")){
                    edtName.setError("Enter Name");
                }else if (strName.length()<2 || strName.length()>16){
                    edtName.setError("Enter valid Name length(2,16) ");
                }else if (strEmail.equals("")){
                    edtEmail.setError("Enter Email");
                }
                else if(!strEmail.matches(emailPattern)){
                    edtEmail.setError("Enter valid Email ID");
                }else if(strNewPassword.equals("")){
                    edtNewPassword.setError("Enter password");
                }
                else if(strNewPassword.length()<8){
                    edtNewPassword.setError("password must have 8 length");

                }else if(strConfirmPassword.equals("")){
                    edtConfirmPassword.setError("Enter password");
                }
                else if(strConfirmPassword.length()<8){
                    edtConfirmPassword.setError("password must have 8 length");

                }else if(!strNewPassword.equals(strConfirmPassword)){
                    edtConfirmPassword.setError("Confirm password does not match with new password");
                }else{
                    Intent i = new Intent(SignUpAuthorActivity.this,NavHomeActivity.class);
                    startActivity(i);
                }

            }
        });

     /*   tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpAuthorActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==11 && resultCode==RESULT_OK)
        {
            assert data != null;
            Uri uri = data.getData();
            circleImageView.setImageURI(uri);
        }
        if(requestCode==12 && resultCode==RESULT_OK)
        {
            assert data != null;
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            circleImageView.setImageBitmap(bitmap);
        }
    }
}