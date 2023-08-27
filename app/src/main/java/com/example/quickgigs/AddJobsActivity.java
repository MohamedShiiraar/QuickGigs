package com.example.quickgigs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import dao.UserAndJobDAOImpl;
import model.User;
import com.example.quickgigs.Utilities.DBStrings;
import com.example.quickgigs.dao.UserAndJobsDAOImpl;

public class AddJobsActivity extends AppCompatActivity {

    private EditText edtJobTitle;
    private EditText edtRatePerHour;
    private EditText edtContactNumber;
    private EditText edtArea;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjob);

        edtJobTitle = findViewById(R.id.edtJobTitle);
        edtRatePerHour = findViewById(R.id.edtRatePerHour);
        edtContactNumber = findViewById(R.id.edtContactNumber);
        edtArea = findViewById(R.id.edtArea);
        btnPost = findViewById(R.id.btnPost);

        userAndJobDAOImpl = new UserAndJobsDAOImpl(this);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJob();
            }
        });
    }
}
