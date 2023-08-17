package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHome extends AppCompatActivity {
    private Button btnCreateJobList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnCreateJobList = findViewById(R.id.btnCreateJob);

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
}
