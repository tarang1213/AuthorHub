package com.authorhub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.authorhub.R;
import com.authorhub.activity.CatDisplayActivity;
import com.authorhub.adapters.CategoryAdapter;
import com.authorhub.adapters.SearchAdapter;
import com.authorhub.models.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    GridView gridView;
    private ArrayList<CategoryModel> categoryModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        categoryModelArrayList = new ArrayList<CategoryModel>();
        firebaseDatabase = FirebaseDatabase.getInstance("https://authorhub-750d5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Category");
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);
        gridView = rootview.findViewById(R.id.grid_view);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    CategoryModel categoryModel = dataSnapshot1.getValue(CategoryModel.class);
                    categoryModelArrayList.add(categoryModel);
                }


                SearchAdapter searchAdapter = new SearchAdapter(getActivity(), categoryModelArrayList);
                gridView.setAdapter(searchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Search");
    }
}