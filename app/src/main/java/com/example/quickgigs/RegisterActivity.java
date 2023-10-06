package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import dao.UserAndJobDAOImpl;
import model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtEmail, edtFirstName, edtLastName, edtUsername, edtPassword;
    private Button btnRegister1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edtEmailAddress);
        edtFirstName = findViewById(R.id.edtName);
        edtLastName = findViewById(R.id.edtSurname);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister1 = findViewById(R.id.btnRegister2);

        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user-entered registration details
                String email = edtEmail.getText().toString();
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                // Create a User object with entered registration details
                User user = new User();
                user.setEmailaddress(email);
                user.setFirstName(firstName);
                user.setSurname(lastName);
                user.setUsername(username);
                user.setPassword(password);

                // Initialize UserAndJobDAOImpl instance
                UserAndJobDAOImpl userDao = new UserAndJobDAOImpl(RegisterActivity.this);

                // Register the user
                if (userDao.register(user)) {
                    // Registration successful, navigate to login activity
                    openLoginActivity();
                }
            }
        });
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finish this activity to prevent user from coming back using the back button
    }
    }
//}
