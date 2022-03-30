package com.authorhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.authorhub.R;
import com.authorhub.adapters.BookDetailsAdapter;
import com.authorhub.adapters.CategoryAdapter;
import com.authorhub.models.BookDetailsModel;
import com.authorhub.models.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookDisplayActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<BookDetailsModel> bookDetailsModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_display);
        listView = findViewById(R.id.list_view);
        Intent i=getIntent();
        String CatName=i.getStringExtra("KEY_CAT");

        bookDetailsModelArrayList = new ArrayList<BookDetailsModel>();
        firebaseDatabase = FirebaseDatabase.getInstance("https://authorhub-750d5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("BookDetails");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    BookDetailsModel bookDetailsModel = dataSnapshot1.getValue(BookDetailsModel.class);
                    if(bookDetailsModel.getBookCat().equals(CatName)){
                        bookDetailsModelArrayList.add(bookDetailsModel);
                    }

                }


                BookDetailsAdapter bookDetailsAdapter = new BookDetailsAdapter(BookDisplayActivity.this,bookDetailsModelArrayList);
                listView.setAdapter(bookDetailsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}

