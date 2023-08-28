package dao;

import static com.example.quickgigs.Utilities.DBStrings.COLUMN_JOBS_CONTACT;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_JOBS_ID;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_JOBS_RATE_PER_HOUR;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_JOBS_TITLE;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_EMAIL_ADDRESS;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_FIRST_NAME;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_ID;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_JOBS_FK;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_LAST_NAME;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_PASSWORD;
import static com.example.quickgigs.Utilities.DBStrings.COLUMN_USER_USERNAME;
import static com.example.quickgigs.Utilities.DBStrings.CREATE_JOBS_TABLE_QUERY;
import static com.example.quickgigs.Utilities.DBStrings.CREATE_USER_TABLE_QUERY;
import static com.example.quickgigs.Utilities.DBStrings.DATABASE_NAME;
import static com.example.quickgigs.Utilities.DBStrings.DATABASE_VERSION;
import static com.example.quickgigs.Utilities.DBStrings.DROP_SCHEDULE_TABLE_QUERY;
import static com.example.quickgigs.Utilities.DBStrings.DROP_USER_TABLE_QUERY;
import static com.example.quickgigs.Utilities.DBStrings.JOBS_TABLE;
import static com.example.quickgigs.Utilities.DBStrings.USER_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Jobs;
import model.User;

public class UserAndJobDAOImpl extends SQLiteOpenHelper {
    private final Context context;

public UserAndJobDAOImpl(@Nullable Context context) {
super(context,DATABASE_NAME,null,DATABASE_VERSION);
this.context=context;

}

    @Override
    public void onCreate(SQLiteDatabase db) {
    Log.i("Warn","Creating db");
    db.execSQL(CREATE_JOBS_TABLE_QUERY);
    db.execSQL(CREATE_USER_TABLE_QUERY);
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DROP_SCHEDULE_TABLE_QUERY);
        db.execSQL(DROP_USER_TABLE_QUERY);

        onCreate(db);
    }

    //Register the user
    public boolean register(User user) {
        SQLiteDatabase quickgigsDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean valid = false;

        contentValues.put(COLUMN_USER_EMAIL_ADDRESS, user.getEmailaddress());
        contentValues.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
        contentValues.put(COLUMN_USER_LAST_NAME, user.getSurname());
        contentValues.put(COLUMN_USER_USERNAME,user.getUsername());
        contentValues.put(COLUMN_USER_PASSWORD, user.getPassword());

    //Check if email exists
        if(isEmailExists(user.getEmailaddress())) {
            Toast.makeText(context.getApplicationContext(), "User already exists with this Email Address.", Toast.LENGTH_SHORT).show();
        } else {
            quickgigsDB.insert(USER_TABLE, null, contentValues);
            valid = true;
            Toast.makeText(context.getApplicationContext(), "Your account was created successfully", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    //Email exists function
    private Boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_EMAIL_ADDRESS},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ?",
                new String[]{email},//Where clause
                null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;
        }
        closeCursor(cursor);
        return false;
    }

    //Login
    public boolean login(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean valid = false;

        String password = user.getPassword();
        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_ID,COLUMN_USER_EMAIL_ADDRESS, COLUMN_USER_FIRST_NAME, COLUMN_USER_LAST_NAME, COLUMN_USER_USERNAME, COLUMN_USER_PASSWORD},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ? ",
                new String[]{user.getEmailaddress()},//Where clause
                null, null, null);


        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            System.out.println("user does exist..");
            User user1 = new User(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

            if(user.getPassword().equals(user1.getPassword())) {
                valid = true;
            }
        }


        db.close();
        closeCursor(cursor);
        return valid;
    }

    public long getCurrentUserId(String emailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_ID},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ?",
                new String[]{String.valueOf(emailAddress)},//Where clause
                null, null, null);

        long userId = -999;

        while(cursor.moveToNext()) {
            userId = cursor.getLong(0);
        }

        closeCursor(cursor);
        return userId;

    }

    public User getUserDetails(String theEmailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.query(USER_TABLE,// Selecting Table
                new String[]{COLUMN_USER_ID,COLUMN_USER_EMAIL_ADDRESS, COLUMN_USER_FIRST_NAME, COLUMN_USER_LAST_NAME, COLUMN_USER_USERNAME, COLUMN_USER_PASSWORD},//Selecting columns want to query
                COLUMN_USER_EMAIL_ADDRESS + " = ? ",
                new String[]{String.valueOf(theEmailAddress)},//Where clause
                null, null, null);

        while(cursor.moveToNext()) {
            long userID = cursor.getLong(0);
            String emailAddress = cursor.getString(1);
            String firstName = cursor.getString(2);
            String lastName = cursor.getString(3);
            String username = cursor.getString(4);
            String password = cursor.getString(5);

            user = new User(userID,emailAddress, firstName, lastName, username, password);
        }


        closeCursor(cursor);
        return user;
    }
    //update user details
    public boolean updateUser(User user) {
        long result = 0;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_EMAIL_ADDRESS, user.getEmailaddress());
        contentValues.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
        contentValues.put(COLUMN_USER_LAST_NAME,user.getSurname());
        contentValues.put(COLUMN_USER_USERNAME,user.getUsername());
        contentValues.put(COLUMN_USER_PASSWORD,user.getPassword());

        Cursor cursor = DB.rawQuery("Select * from " + USER_TABLE + " where " + COLUMN_USER_ID  + " = ?", new String[]{String.valueOf(user.getId())});
        if (cursor.getCount() > 0) {
            result = DB.update(USER_TABLE, contentValues, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(user.getId())});

        }

        closeCursor(cursor);
        DB.close();

        return result == -1 ? false : true;
    }

    //Add job

    public boolean addJob(Jobs jobs, String emailAddress) {
        SQLiteDatabase dbRead = this.getReadableDatabase();
        SQLiteDatabase dbWrite = this.getWritableDatabase();
        long result = 0;
        long userId = -1;


        Cursor cursor = dbRead.rawQuery("SELECT " + COLUMN_USER_ID + " FROM " + USER_TABLE + " WHERE " + COLUMN_USER_EMAIL_ADDRESS + " = ?", new String[]{emailAddress});

        if(cursor.moveToNext()) {
            userId = cursor.getLong(0);
        }


        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_JOBS_TITLE, jobs.getJobTitle());
        contentValues.put(COLUMN_JOBS_RATE_PER_HOUR, jobs.getRatePerHour());
        contentValues.put(COLUMN_JOBS_CONTACT, String.valueOf(jobs.getContactNum()));
        contentValues.put(COLUMN_USER_JOBS_FK, String.valueOf(userId));

        result = dbWrite.insert(JOBS_TABLE, null, contentValues);

        closeCursor(cursor);
        dbRead.close();
        dbWrite.close();
        return result == -1 ? false : true;
    }
    //Delete a job
    public Boolean deleteJob (long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        Cursor cursor = db.rawQuery("SELECT * FROM " + JOBS_TABLE + " WHERE " + COLUMN_JOBS_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.getCount() > 0) {
            result = db.delete(JOBS_TABLE, COLUMN_JOBS_ID + " = ?", new String[]{String.valueOf(id)});
        }

        closeCursor(cursor);
        db.close();
        return result == -1 ? false : true;
    }

    //Get all jobs

    public List<Jobs> getUserJobs (long userId) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Jobs> JobsList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + JOBS_TABLE + " WHERE " + COLUMN_USER_JOBS_FK + " = ?", new String[]{String.valueOf(userId)});
        readDataFromCursor(JobsList, cursor);

        return JobsList;

    }
    //Get job by ID

    public Jobs getJobsById (long id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Jobs jobs = null;

        Cursor cursor = DB.rawQuery("Select * from " + JOBS_TABLE + " where " + COLUMN_JOBS_ID  + " = ?", new String[]{String.valueOf(id)});

        System.out.println("cursor: " + cursor);
        if(cursor.moveToNext()) {
            long jobsId = cursor.getLong(0);
            String jobtitle = cursor.getString(1);
            String rateperhour = cursor.getString(2);
            String contactNumber = cursor.getString(3);
            String areaLocated = cursor.getString(4);
            long userId = cursor.getInt(5);


            jobs = new Jobs(jobsId, jobtitle,rateperhour,contactNumber,areaLocated, userId);

        }

        closeCursor(cursor);
        return jobs;

    }

    private void readDataFromCursor(List<Jobs> jobsList, Cursor cursor) {
        int count = 0;
        while(cursor.moveToNext()) {
            long jobsId = cursor.getLong(0);
            String jobtitle = cursor.getString(1);
            String rateperhour = cursor.getString(2);
            String contactNumber = cursor.getString(3);
            String areaLocated = cursor.getString(4);
            long userId = cursor.getInt(5);

            System.out.println(cursor.getString(count));
            long theUserId = cursor.getLong(4);

            Jobs jobs = new Jobs(jobsId, jobtitle, rateperhour, contactNumber, areaLocated,theUserId);


            count++;
            jobsList.add(jobs);
        }

        closeCursor(cursor);

    }

    private void closeCursor(Cursor cursor) {
        if(cursor != null) {
            cursor.close();
        }
    }

}
