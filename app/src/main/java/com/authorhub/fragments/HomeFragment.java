package com.authorhub.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.authorhub.R;
import com.authorhub.fragments.ContactUsFragment;
import com.authorhub.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    Fragment fragment = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home,null);
        bottomNavigationView = view.findViewById(R.id.bottom_view);
        toolbar = getActivity().findViewById(R.id.toolbar);
        loadDashboard();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                if (id == R.id.home){


                    fragment = new HomeFragment1();
                    fragmentTransaction.replace(R.id.frame1,fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");

                } else if (id == R.id.search){

                    toolbar.setTitle("Search");
                    fragment = new SearchFragment();
                    fragmentTransaction.replace(R.id.frame1,fragment);
                    fragmentTransaction.commit();

                }else if (id == R.id.profile){
                    getActivity().setTitle("profile");

                    toolbar.setTitle("Profile");
                    fragment = new ContactUsFragment();
                    fragmentTransaction.replace(R.id.frame1,fragment);
                    fragmentTransaction.commit();

                }
                return true;

            }
        });
        return view;



    }

    private void loadDashboard() {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragment = new HomeFragment1();
        fragmentTransaction.replace(R.id.frame1, fragment);
        toolbar.setTitle("Home");
        fragmentTransaction.commit();


    }

}