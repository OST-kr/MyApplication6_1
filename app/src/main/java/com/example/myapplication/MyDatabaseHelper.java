package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "food_expiry.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "food";
    public static final String NAME = "이름";
    public static final String CATEGORY = "분류";
    public static final String EXPIRY = "소비기한";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                NAME + " TEXT, " +
                CATEGORY + " TEXT, " +
                EXPIRY + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스 업그레이드 시 필요한 작업 수행
    }

    public void addFood(String name, String category, String expiry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(CATEGORY, category);
        values.put(EXPIRY, expiry);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

}