package com.example.quickgigs;

import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_EMAIL_ADDRESS;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_FIRST_NAME;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_ID;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_LAST_NAME;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_PASSWORD;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_USERNAME;
import static com.example.quickgigs.Utilities.DBStrings.USER_TABLE;

import android.content.ContentValues;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dao.UserAndJobDAOImpl;
import model.User;

public class ProfileActivity extends AppCompatActivity {
    private Button btnEditProfileDets;
    private Button btnActiveJobs;

    private BottomNavigationView bottomNavView;
    private String authenticatedUser;

    private UserAndJobDAOImpl userAndJobDAO;

    private long userId;
    private TextView edtNameAndSurname,edtEmailAdress,edtUsername;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfileDets=findViewById(R.id.btnEditProfDetails);
        btnActiveJobs=findViewById(R.id.BtnViewActive);
        userAndJobDAO = new UserAndJobDAOImpl(this);
        edtNameAndSurname = findViewById(R.id.edtNameAndSurname);
        edtUsername=findViewById(R.id.edtUsername2);
        edtEmailAdress = findViewById(R.id.edtEmailAdress);


        authenticatedUser = getIntent().getStringExtra("authenticatedUser");

        System.out.println("auth user: " + authenticatedUser);

        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_profile);

        loadUserData();

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        Intent goToProjectActivity = new Intent(ProfileActivity.this, ActivityHome.class);
                        goToProjectActivity.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToProjectActivity);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        return true;
                    case R.id.nav_explore:
                        Intent goToExploreActivityIntent = new Intent(ProfileActivity.this, ExploreActivity.class);
                        goToExploreActivityIntent.putExtra("authenticatedUser", authenticatedUser);
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
    private void loadUserData() {
        userId = userAndJobDAO.getCurrentUserId(authenticatedUser);
        System.out.println("USER ID: " + userId);
        User userDetails = userAndJobDAO.getUserDetails(authenticatedUser);
        edtNameAndSurname.setText(userDetails.getFirstName()+" "+userDetails.getSurname());
        edtEmailAdress.setText(userDetails.getEmailaddress());
        edtUsername.setText(userDetails.getUsername());
    }

    public void openUpdateDetails() {
        Intent intent = new Intent(this,UpdateDetailsActivity.class);
        intent.putExtra("authenticatedUser", authenticatedUser);
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
