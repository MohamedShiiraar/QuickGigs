package com.example.quickgigs;

import android.content.ContentValues;
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

public class AddJobsActivity extends AppCompatActivity {

    private EditText edtJobTitle;
    private EditText edtRatePerHour;
    private EditText edtContactNumber;
    private EditText edtArea;
    private Button btnPost;
    private UserAndJobDAOImpl userAndJobDAOImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjob);
    }
}
