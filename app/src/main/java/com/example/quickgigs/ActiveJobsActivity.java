package com.example.quickgigs;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.example.quickgigs.Utilities.DBStrings;

public class ActiveJobsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activejobs);

        ArrayList<String> jobTitles = new ArrayList<>();

        SQLiteDatabase db = openOrCreateDatabase(DBStrings.DATABASE_NAME, MODE_PRIVATE, null);

        String query = "SELECT * FROM " + DBStrings.JOBS_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String jobTitle = cursor.getString(cursor.getColumnIndex(DBStrings.COLUMN_JOBS_TITLE));
                jobTitles.add(jobTitle);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        @SuppressLint("WrongViewCast") ListView listView = findViewById(R.id.textView14);  // Replace with your ListView's ID
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jobTitles);
        listView.setAdapter(adapter);
    }
}
