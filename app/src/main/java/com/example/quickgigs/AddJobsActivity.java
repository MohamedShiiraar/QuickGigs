package com.example.quickgigs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddJobsActivity extends AppCompatActivity {

    private EditText edtJobTitle;
    private EditText edtRatePerhour;
    private EditText edtContactNumber;
    private EditText edtArea;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjob);

        edtJobTitle = findViewById(R.id.edtJobTitle);
        //edtRatePerHour=findViewById(R.id.edtRatePerHour);

    }





    public abstract class JobDatabase {
        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/your_database_name";
        private static final String USERNAME = "your_username";
        private static final String PASSWORD = "your_password";

        public void addJobToDatabase(String jobTitle, String company, String location, String salary) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");


                Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);


                String insertQuery = "INSERT INTO jobs (job_title, company, location, salary) VALUES (?, ?, ?, ?";


                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, jobTitle);
                preparedStatement.setString(2, company);
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, salary);


                preparedStatement.executeUpdate();


                preparedStatement.close();
                connection.close();

                System.out.println("Job added to the database successfully.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.err.println("Error adding job to the database: " + e.getMessage());
            }
        }


            String jobTitle = "Software Engineer";
            String company = "ABC Inc.";
            String location = "New York";
            String salary = "$100,000";

             public void AddJobsActivity(jobTitle, company, location, salary);
        }
    }


