package com.authorhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.authorhub.R;
import com.authorhub.fragments.ContactUsFragment;
import com.authorhub.fragments.HomeFragment;
import com.authorhub.fragments.HomeFragment1;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        bottomNavigationView = findViewById(R.id.bottom_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.home){

                    fragment = new HomeFragment1();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");

                } else if (id == R.id.profile){

                    toolbar.setTitle("Profile");
                    fragment = new ContactUsFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();

                }

                return true;

            }
        });
    }
}