package com.example.registration_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register1.db";
    public static final String TABLE_NAME = "register";
    public static final String R_ID = "ID";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, firstName Text, lastName Text, Email Text, Phone Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password, String FirstName, String LastName, String Email, String Phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user);
        contentValues.put(PASSWORD, password);
        contentValues.put(FIRST_NAME, FirstName);
        contentValues.put(LAST_NAME, LastName);
        contentValues.put(EMAIL, Email);
        contentValues.put(PHONE, Phone);
        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return res;
    }

    public boolean checkLogin(String username, String password) {
        String[] columns = {R_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERNAME + "=?" + " and " + PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public Boolean CheckUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM register WHERE username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }
}