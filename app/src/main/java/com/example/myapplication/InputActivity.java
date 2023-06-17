package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    Button okBtn;
    Button backBtn;
    ImageView imageView;
    TextView Ipt_foodname;
    TextView Ipt_foodext;
    foodlistDatabaseHelper dbfood;
    MyDatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        okBtn = findViewById(R.id.ok);
        backBtn = findViewById(R.id.back);
        imageView = findViewById(R.id.imageView);
        Ipt_foodname = findViewById(R.id.Ipt_foodname);
        Ipt_foodext = findViewById(R.id.Ipt_foodExt);
        dbfood = new foodlistDatabaseHelper(this);
        dbHelper = new MyDatabaseHelper(this);

        Bitmap image = getIntent().getParcelableExtra("image");
        if (image != null) {
            imageView.setImageBitmap(image);
        }

        // Text 받아와서 출력
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        Ipt_foodname.setText(text);

        // 소비기한 가져오기
        String foodExt = getFoodExtFromDatabase(text);
        Ipt_foodext.setText(foodExt);

        // 확인 버튼 누르면 text가 냉장고로 이동
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = Ipt_foodname.getText().toString().trim();
                String expiry = Ipt_foodext.getText().toString().trim();
                Intent intent = new Intent(InputActivity.this, MyRefrigerator.class);
                intent.putExtra("text", text);
                saveDataToDatabase(text, expiry);
                startActivity(intent);
                if (!text.isEmpty()) {
                    addFoodItemToDatabase(text);
                    openMyRefrigeratorActivity();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveDataToDatabase(String text, String expiry) {
        dbfood.addFoodItem(text, expiry);
        Intent intent = new Intent(InputActivity.this, MyRefrigerator.class);
        startActivity(intent);
    }

    private void openMyRefrigeratorActivity() {
        String foodName = Ipt_foodname.getText().toString().trim();
        Intent intent = new Intent(InputActivity.this, MyRefrigerator.class);
        intent.putExtra("foodName", foodName);
        startActivity(intent);
    }

    private String getFoodExtFromDatabase(String foodName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {MyDatabaseHelper.EXPIRY};
        String selection = MyDatabaseHelper.NAME + "=?";
        String[] selectionArgs = {foodName};

        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String foodExt = "";

        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(MyDatabaseHelper.EXPIRY);
            foodExt = cursor.getString(index);
        }

        cursor.close();
        db.close();

        return foodExt;
    }

    private void addFoodItemToDatabase(String foodName) {
        SQLiteDatabase db = dbfood.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(foodlistDatabaseHelper.COLUMN_FOOD_NAME, foodName);
        values.put(foodlistDatabaseHelper.COLUMN_EXPIRY_DATE, "5"); // 예시로 "5" 값 추가
        db.insert(foodlistDatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }
}