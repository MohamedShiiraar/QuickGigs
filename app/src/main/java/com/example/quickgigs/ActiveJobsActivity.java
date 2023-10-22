package com.example.quickgigs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.quickgigs.Utilities.DBStrings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dao.UserAndJobDAOImpl;
import model.Jobs;
import model.User;

public class ActiveJobsActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavView;
    private String authenticatedUser;

    private TextView edtNameActive;

    private UserAndJobDAOImpl userAndJobDAO;

    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activejobs);

        edtNameActive = findViewById(R.id.nameActive);

        authenticatedUser = getIntent().getStringExtra("authenticatedUser");
        System.out.println("auth user: " + authenticatedUser);
        userAndJobDAO = new UserAndJobDAOImpl(this);

        List<Jobs> jobList2 = userAndJobDAO.getJobsById(userAndJobDAO.getCurrentUserId(authenticatedUser));

        User userDetails = userAndJobDAO.getUserDetails(authenticatedUser);
        if (userDetails!=null) {
            loadUserData();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerviewActive);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new JobListAdapter(getApplicationContext(),jobList2));

        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_profile);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        Intent goToProjectActivity = new Intent(ActiveJobsActivity.this, ActivityHome.class);
                        goToProjectActivity.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToProjectActivity);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        return true;
                    case R.id.nav_explore:
                        Intent goToExploreActivityIntent = new Intent(ActiveJobsActivity.this, ExploreActivity.class);
                        goToExploreActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        moveToIntent(goToExploreActivityIntent);
                        return true;
                }
                return false;
            }
        });

        /*ArrayList<String> jobTitles = new ArrayList<>();

        SQLiteDatabase db = openOrCreateDatabase(DBStrings.DATABASE_NAME, MODE_PRIVATE, null);

        String query = "SELECT * FROM " + DBStrings.JOBS_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String jobTitle = cursor.getString(cursor.getColumnIndex(DBStrings.COLUMN_JOBS_TITLE));
                jobTitles.add(jobTitle);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();*/
    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }

    private void loadUserData() {
        userId = userAndJobDAO.getCurrentUserId(authenticatedUser);
        System.out.println("USER ID: " + userId);
        User userDetails = userAndJobDAO.getUserDetails(authenticatedUser);
        edtNameActive.setText(userDetails.getFirstName()+" "+userDetails.getSurname());;
    }
}
