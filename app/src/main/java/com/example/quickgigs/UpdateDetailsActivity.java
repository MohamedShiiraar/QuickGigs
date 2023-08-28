package com.example.quickgigs;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.quickgigs.Utilities.DBStrings;  // Import your DBStrings?????

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickgigs.Utilities.DBStrings;

public class UpdateDetailsActivity extends AppCompatActivity {

    private EditText edtEditEmail, edtEditPassword, edtEditName, edtEditSurname;
    private Button edtUpdateDets;

    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails);

        edtEditEmail = findViewById(R.id.edtEditEmail);
        edtEditPassword = findViewById(R.id.edtEditPassword);
        edtEditName = findViewById(R.id.edtEditName);
        edtEditSurname = findViewById(R.id.edtEditSurname);
        edtUpdateDets = findViewById(R.id.edtUpdateDets);

        sqLiteDatabase = openOrCreateDatabase(DBStrings.DATABASE_NAME, MODE_PRIVATE, null);

        loadUserDetails();

        edtUpdateDets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails(v);
            }
        });
    }

    @SuppressLint("Range")
    private void loadUserDetails() {
        int userId = 1; // NOTE: You should get this value dynamically
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DBStrings.USER_TABLE + " WHERE " + DBStrings.COLUMN_USER_ID + " = " + userId, null);

        if (cursor.moveToFirst()) {
            edtEditEmail.setText(cursor.getString(cursor.getColumnIndex(DBStrings.COLUMN_USER_EMAIL_ADDRESS)));
            edtEditPassword.setText(cursor.getString(cursor.getColumnIndex(DBStrings.COLUMN_USER_PASSWORD)));
            edtEditName.setText(cursor.getString(cursor.getColumnIndex(DBStrings.COLUMN_USER_FIRST_NAME)));
            edtEditSurname.setText(cursor.getString(cursor.getColumnIndex(DBStrings.COLUMN_USER_LAST_NAME)));
        }
    }

    public void updateDetails(View view) {
        String updatedEmail = edtEditEmail.getText().toString();
        String updatedPassword = edtEditPassword.getText().toString();
        String updatedName = edtEditName.getText().toString();
        String updatedSurname = edtEditSurname.getText().toString();

        int userId = 1; // NOTE: You should get this value dynamically

        sqLiteDatabase.execSQL("UPDATE " + DBStrings.USER_TABLE + " SET " +
                DBStrings.COLUMN_USER_EMAIL_ADDRESS + " = '" + updatedEmail + "', " +
                DBStrings.COLUMN_USER_PASSWORD + " = '" + updatedPassword + "', " +
                DBStrings.COLUMN_USER_FIRST_NAME + " = '" + updatedName + "', " +
                DBStrings.COLUMN_USER_LAST_NAME + " = '" + updatedSurname +
                "' WHERE " + DBStrings.COLUMN_USER_ID + " = " + userId);

        Toast.makeText(this, "Details Updated!", Toast.LENGTH_SHORT).show();
    }
}
