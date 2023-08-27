package com.example.quickgigs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dao.UserAndJobDAOImpl;
import model.User;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin2;
    private EditText edtEmail, edtPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin2= findViewById(R.id.btnLogin2);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword1 = findViewById(R.id.edtPassword1);
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString();
                String password = edtPassword1.getText().toString();

                User user = new User();
                user.setEmailaddress(email);
                user.setPassword(password);

                UserAndJobDAOImpl userDao = new UserAndJobDAOImpl(LoginActivity.this);
                if (userDao.login(user)) {
                    openHome();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed. Invalid credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void openHome(){
        String emailaddress =edtEmail.getText().toString().trim();
        Intent intent=new Intent(this, ActivityHome.class);
        intent.putExtra("authenticatedUser", emailaddress);
        startActivity(intent);
    }

}
