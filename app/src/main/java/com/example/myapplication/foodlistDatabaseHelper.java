package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class foodlistDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "food_database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "foodList";
    public static final String COLUMN_FOOD_NAME = "음식이름";
    public static final String COLUMN_EXPIRY_DATE = "소비기한";

    public foodlistDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_FOOD_NAME + " TEXT,"
                + COLUMN_EXPIRY_DATE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFoodItem(String text, String expiry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD_NAME, text);
        values.put(COLUMN_EXPIRY_DATE, expiry);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}