package com.authorhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.authorhub.R;
import com.authorhub.adapters.BookDetailsAdapter;
import com.authorhub.adapters.CategoryAdapter;
import com.authorhub.models.BookDetailsModel;
import com.authorhub.models.CategoryModel;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BookMoreDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView bookName,bookDesc,authorname;
    ImageView bookImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_more_details);

        firebaseDatabase = FirebaseDatabase.getInstance("https://authorhub-750d5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("BookDetails");

        bookImg=findViewById(R.id.img_book);
        bookName=findViewById(R.id.bookName);
        bookDesc=findViewById(R.id.bookDesc);
        authorname=findViewById(R.id.authorname);


        Intent i = getIntent();
        String strBookId = i.getStringExtra("KEY_ID");
        String strBookImg=i.getStringExtra("KEY_BOOKIMG");
        String strBookName = i.getStringExtra("KEY_NAME");
        String strBookDescription = i.getStringExtra("KEY_DESC");
        String  strauthorname =i.getStringExtra("KEY_AUTHOR");

        Glide.with(this).load(strBookImg).into(bookImg);
        bookName.setText("Book Name:\n"+strBookName);
        bookDesc.setText("Description:\n"+strBookDescription);
        //Glide.with(this).load(strauthorimage).into(authorimage);
        authorname.setText("Author Name:\n"+strauthorname);
    }
}


