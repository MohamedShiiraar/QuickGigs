package com.example.quickgigs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDetailsActivity extends AppCompatActivity {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public UpdateDetailsActivity(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;

        this.password = password;
    }





    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewClass{" + "firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + '}';
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails);
    }

}