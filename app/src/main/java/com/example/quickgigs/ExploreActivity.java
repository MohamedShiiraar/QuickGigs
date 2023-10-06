package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExploreActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavView;
    private String authenticatedUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_explore);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        Intent goToProjectActivityIntent = new Intent(ExploreActivity.this, ActivityHome.class);
                        moveToIntent(goToProjectActivityIntent);
                        return true;
                    case R.id.nav_profile:
                        Intent goToProjectActivityIntent1 = new Intent(ExploreActivity.this, ProfileActivity.class);
                        moveToIntent(goToProjectActivityIntent1);
                        return true;
                    case R.id.nav_explore:
                        return true;
                }
                return false;
            }
        });
    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }
}
