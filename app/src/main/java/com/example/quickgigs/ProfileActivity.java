package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private Button btnEditProfileDets;
    private Button btnActiveJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfileDets=findViewById(R.id.btnEditProfDetails);
        btnActiveJobs=findViewById(R.id.BtnViewActive);

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
}
