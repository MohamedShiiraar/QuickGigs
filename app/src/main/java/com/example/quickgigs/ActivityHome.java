package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityHome extends AppCompatActivity {
    private Button btnCreateJobList;
    private BottomNavigationView bottomNavView;
    private String authenticatedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnCreateJobList = findViewById(R.id.btnCreateJob);
        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_home);

        authenticatedUser = getIntent().getStringExtra("authenticatedUser");

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_profile:
                        Intent goToProfileActivityIntent = new Intent(ActivityHome.this, ProfileActivity.class);
                        goToProfileActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToProfileActivityIntent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_explore:
                        Intent goToExploreActivityIntent = new Intent(ActivityHome.this, ExploreActivity.class);
                        goToExploreActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        moveToIntent(goToExploreActivityIntent);
                        return true;
                }
                return false;
            }
        });

        btnCreateJobList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddJob();

            }
        });
    }
    public void openAddJob() {
        Intent intent = new Intent(this,AddJobsActivity.class);
        startActivity(intent);
    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }
}
