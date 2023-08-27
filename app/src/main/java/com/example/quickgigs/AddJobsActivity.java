package com.example.quickgigs;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
import dao.UserAndJobDAOImpl;

public class AddJobsActivity extends AppCompatActivity {

    private EditText edtJobTitle;
    private EditText edtRatePerHour;
    private EditText edtContactNumber;
    private EditText edtArea;
    private Button btnPost;

    private UserAndJobDAOImpl UserAndJobDAOImpl;

    private long userId;
    private String authenticatedUser;

    private Dialog dialog;

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

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = edtJobTitle.getText().toString().trim();
                String jobRatePerHour = edtRatePerHour.getText().toString().trim();
                String jobContact = edtContactNumber.getText().toString().trim();
                String jobArea = edtArea.getText().toString().trim();
                Jobs jobs = new Jobs();
                jobs.setJobTitle(jobTitle);
                jobs.setRatePerHour(jobRatePerHour);
                jobs.setContactNum(jobContact);
                jobs.setAreaLocated(jobArea);

                UserAndJobDAOImpl userDao = new UserAndJobDAOImpl(AddJobsActivity.this);
                User user = new User();
                if (userDao.addJob(jobs,user.getEmailaddress())) {
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
        startActivity(intent);
        finish();

    }
}