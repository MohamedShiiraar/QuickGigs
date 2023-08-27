package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import dao.UserAndJobDAOImpl;
import model.Jobs;

public class ExploreActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavView;
    private String authenticatedUser;
    private UserAndJobDAOImpl userAndJobDAO;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        userAndJobDAO = new UserAndJobDAOImpl(this);

        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_explore);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        authenticatedUser = getIntent().getStringExtra("authenticatedUser");

        long userId = 1;
        List<Jobs> jobList = userAndJobDAO.getUserJobs(userId);

        JobListAdapter adapter = new JobListAdapter(jobList);
        recyclerView.setAdapter(adapter);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        Intent goToHomeActivityIntent = new Intent(ExploreActivity.this, ActivityHome.class);
                        goToHomeActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToHomeActivityIntent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        Intent goToProfileActivityIntent = new Intent(ExploreActivity.this, ProfileActivity.class);
                        goToProfileActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToProfileActivityIntent);
                        overridePendingTransition(0,0);
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
