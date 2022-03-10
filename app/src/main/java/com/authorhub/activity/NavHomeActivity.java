package com.authorhub.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.authorhub.R;
import com.authorhub.fragments.AboutUsFragment;
import com.authorhub.fragments.ContactUsFragment;
import com.authorhub.fragments.HomeFragment;
import com.authorhub.fragments.HomeFragment1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class NavHomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);




        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        loadDashboard();


        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_AUTHOR", MODE_PRIVATE);
        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");


        View headerView = navigationView.getHeaderView(0);
        TextView tvEmail = headerView.findViewById(R.id.tv_email);
        tvEmail.setText(strEmail);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                fragmentManager = getSupportFragmentManager();
               fragmentTransaction = fragmentManager.beginTransaction();

                if (id == R.id.nav_home) {

                    fragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");
                } else if (id == R.id.nav_about_us) {

                    toolbar.setTitle("About Us");
                    fragment = new AboutUsFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                } else if (id == R.id.nav_contact_us) {

                    toolbar.setTitle("Contact Us");
                    fragment = new ContactUsFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                }else  if(id == R.id.nav_logout){

                    SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_GIFT",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("KEY_PREF_EMAIL");
                    editor.remove("KEY_PREF_Password");
                    editor.commit();
                    Intent i = new Intent(NavHomeActivity.this, LoginActivity.class);
                    startActivity(i);
                   // finish();
                }



                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    private void loadDashboard()
    {

        fragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
        toolbar.setTitle("Home");

    }
}