package com.example.quickgigs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.quickgigs.Utilities.DBStrings;  // Import your DBStrings?????

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickgigs.Utilities.DBStrings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dao.UserAndJobDAOImpl;
import model.User;

public class UpdateDetailsActivity extends AppCompatActivity {

    private EditText edtEditEmail, edtEditPassword, edtEditName, edtEditSurname;
    private Button edtUpdateDets;
    private String authenticatedUser;

    private UserAndJobDAOImpl userAndJobDAO;
    private BottomNavigationView bottomNavView;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails);
        userAndJobDAO = new UserAndJobDAOImpl(this);

        edtEditEmail = findViewById(R.id.edtEditEmail);
        edtEditPassword = findViewById(R.id.edtEditPassword);
        edtEditName = findViewById(R.id.edtEditName);
        edtEditSurname = findViewById(R.id.edtEditSurname);
        edtUpdateDets = findViewById(R.id.edtUpdateDets);


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
                        Intent goToProjectActivity = new Intent(UpdateDetailsActivity.this, ActivityHome.class);
                        goToProjectActivity.putExtra("authenticatedUser", authenticatedUser);
                        startActivity(goToProjectActivity);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        return true;
                    case R.id.nav_explore:
                        Intent goToExploreActivityIntent = new Intent(UpdateDetailsActivity.this, ExploreActivity.class);
                        goToExploreActivityIntent.putExtra("authenticatedUser", authenticatedUser);
                        moveToIntent(goToExploreActivityIntent);
                        return true;
                }
                return false;
            }
        });


        edtUpdateDets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDetails();
            }
        });
    }

    private void loadUserData() {
        userId = userAndJobDAO.getCurrentUserId(authenticatedUser);
        System.out.println("USER ID: " + userId);
        User userDetails = userAndJobDAO.getUserDetails(authenticatedUser);
        edtEditName.setText(userDetails.getFirstName());
        edtEditSurname.setText(userDetails.getSurname());
        edtEditEmail.setText(userDetails.getEmailaddress());
        edtEditPassword.setText(userDetails.getPassword());
    }

    private void editDetails() {
        String name = edtEditName.getText().toString().trim();
        String surname = edtEditSurname.getText().toString().trim();
        String email = edtEditEmail.getText().toString().trim();
        String password = edtEditPassword.getText().toString().trim();


            User updateDetails = new User(userId, email, name, surname,password);
        if (userAndJobDAO.updateUser(updateDetails)) {
            openProfile();
        } else {
            Toast.makeText(UpdateDetailsActivity.this, "Failed to update details", Toast.LENGTH_SHORT).show();
        }
        }

    public void openProfile(){
        Intent intent=new Intent(this, ProfileActivity.class);
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }
    }
