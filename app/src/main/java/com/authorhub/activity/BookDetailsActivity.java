package com.authorhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.authorhub.models.BookDetailsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import com.authorhub.R;


public class BookDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtbookCat,edtBookName, edtBookDes, edtAuthorName, edtBookImage;
    Button btnAdd;
    // CircleImageView circleImageView;

    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);


        // Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://authorhub-750d5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("BookDetails");

        firebaseStorage = FirebaseStorage.getInstance();

        edtbookCat=findViewById(R.id.edt_bookCat);
        edtBookName = findViewById(R.id.edt_bookname);
        edtBookImage = findViewById(R.id.edt_bookimage);
        edtAuthorName = findViewById(R.id.edt_authorname);
        edtBookDes = findViewById(R.id.edt_bookdescription);

        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strBookName = edtBookName.getText().toString();
                String strBookImage = edtBookImage.getText().toString();
                String strAuthorName = edtAuthorName.getText().toString();
                String strBookDes = edtBookDes.getText().toString();
                String strbookCat = edtbookCat.getText().toString();
                String strid = databaseReference.push().getKey();
                BookDetailsModel bookDetailsModel = new BookDetailsModel();

                bookDetailsModel.setSid(strid);
                bookDetailsModel.setBookName(strBookName);
                bookDetailsModel.setBookImage(strBookImage);
                bookDetailsModel.setBookDesc(strBookDes);
                bookDetailsModel.setAuthorName(strAuthorName);
                bookDetailsModel.setBookCat(strbookCat);
                databaseReference.child(strid).setValue(bookDetailsModel);
                edtbookCat.setText("");
                edtBookName.setText("");
                edtBookDes.setText("");
                edtBookImage.setText("");
                edtAuthorName.setText("");
            }
        });


    }
}