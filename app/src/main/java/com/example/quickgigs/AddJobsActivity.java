package com.example.quickgigs;

import android.content.ContentValues;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.text.TextUtils;
import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import dao.UserAndJobDAOImpl;
import model.Jobs;
import model.User;
import com.example.quickgigs.Utilities.DBStrings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dao.UserAndJobDAOImpl;

public class AddJobsActivity extends AppCompatActivity {

    private EditText edtJobTitle;
    private EditText edtRatePerHour;
    private EditText edtContactNumber;
    private EditText edtArea;
    private Button btnPost;
    private UserAndJobDAOImpl userAndJobDAOImpl;

    private UserAndJobDAOImpl UserAndJobDAOImpl;

    private long userId;
    private String authenticatedUser;

    private Dialog dialog;

    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjob);

        edtJobTitle = findViewById(R.id.edtJobTitle);
        edtRatePerHour = findViewById(R.id.edtRatePerHour);
        edtContactNumber = findViewById(R.id.edtContactNumber);
        edtArea = findViewById(R.id.edtArea);
        btnPost = findViewById(R.id.btnPost);

        UserAndJobDAOImpl = new UserAndJobDAOImpl(this);
        authenticatedUser = getIntent().getStringExtra("authenticatedUser");
        userId = getIntent().getLongExtra("userId", -999);

        bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setSelectedItemId(R.id.nav_home);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_profile:
                        Intent goToProfileActivityIntent = new Intent(AddJobsActivity.this, ProfileActivity.class);
                        goToProfileActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToProfileActivityIntent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_explore:
                        Intent goToExploreActivityIntent = new Intent(AddJobsActivity.this, ExploreActivity.class);
                        goToExploreActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        moveToIntent(goToExploreActivityIntent);
                        return true;
                }
                return false;
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = edtJobTitle.getText().toString();
                String jobRatePerHour = "R" + edtRatePerHour.getText().toString() + " Per a hour";
                String jobContact = edtContactNumber.getText().toString();
                String jobArea = edtArea.getText().toString();

                Jobs jobs = new Jobs();
                jobs.setJobTitle(jobTitle);
                jobs.setRatePerHour(jobRatePerHour);
                jobs.setContactNum(jobContact);
                jobs.setAreaLocated(jobArea);

                UserAndJobDAOImpl userDao = new UserAndJobDAOImpl(AddJobsActivity.this);

                //User user = new User();

                if (userDao.addJob(jobs,authenticatedUser)) {
                    openHomeActivity();
                }

            }
        });
    }


    private void clearInput() {
        edtJobTitle.setText("");
        edtRatePerHour.setText("");
        edtContactNumber.setText("");
        edtArea.setText("");
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, ActivityHome.class);
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
        finish();

    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }
}