package com.authorhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.authorhub.models.AuthorDetailsModel;
import com.authorhub.models.BookDetailsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import com.authorhub.R;


public class AuthorDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtBookName, edtAuthorName, edtAuthorImage;
    Button btnAdd;
    // CircleImageView circleImageView;

    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_details);

        // Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://authorhub-750d5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("AuthorDetails");

        firebaseStorage = FirebaseStorage.getInstance();

        edtBookName = findViewById(R.id.edt_bookname);
        edtAuthorImage = findViewById(R.id.edt_authorimage);
        edtAuthorName = findViewById(R.id.edt_authorname);


        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strBookName = edtBookName.getText().toString();
                String strAuthorImage = edtAuthorImage.getText().toString();
                String strAuthorName = edtAuthorName.getText().toString();
                String strid = databaseReference.push().getKey();
                AuthorDetailsModel authorDetailsModel = new AuthorDetailsModel();
                authorDetailsModel.setSid(strid);
                authorDetailsModel.setBookName(strBookName);
                authorDetailsModel.setAuthorImage(strAuthorImage);
                authorDetailsModel.setAuthorName(strAuthorName);
                databaseReference.child(strid).setValue(authorDetailsModel);
                edtAuthorImage.setText("");
                edtBookName.setText("");
                edtAuthorName.setText("");
            }
        });


    }
}

