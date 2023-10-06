package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private Button btnEditProfileDets;
    private Button btnActiveJobs;

    private BottomNavigationView bottomNavView;
    private String authenticatedUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfileDets=findViewById(R.id.btnEditProfDetails);
        btnActiveJobs=findViewById(R.id.BtnViewActive);

        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_profile);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        Intent goToProjectActivityIntent = new Intent(ProfileActivity.this, ActivityHome.class);
                        moveToIntent(goToProjectActivityIntent);
                        return true;
                    case R.id.nav_profile:
                        return true;
                    case R.id.nav_explore:
                        Intent goToExploreActivityIntent = new Intent(ProfileActivity.this, ExploreActivity.class);
                        moveToIntent(goToExploreActivityIntent);
                        return true;
                }
                return false;
            }
        });

        btnEditProfileDets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateDetails();

            }
        });

        btnActiveJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActiveJobs();

            }
        });

    }

    public void openUpdateDetails() {
        Intent intent = new Intent(this,UpdateDetailsActivity.class);
        startActivity(intent);
    }

    public void openActiveJobs() {
        Intent intent = new Intent(this, ActiveJobsActivity.class);
        startActivity(intent);
    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }
}
