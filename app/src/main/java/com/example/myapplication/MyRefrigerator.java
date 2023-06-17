package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRefrigerator extends AppCompatActivity {

    private ListView listView;
    private FoodAdapter adapter;
    private ArrayList<FoodItem> items;
    ImageView camera, alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // 아이템 리스트 초기화
        items = new ArrayList<>();
        items.add(new FoodItem("김치찌개", "2023-06-08", "2023-06-12"));

        // 어댑터 초기화
        adapter = new FoodAdapter(this, items);
        listView.setAdapter(adapter);
        // 카메라 버튼 클릭 시
        camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CameraActivity로 이동
                Intent intent = new Intent(MyRefrigerator.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        // 알림 버튼 클릭 시
        alert = findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alertpage로 이동
                Intent intent = new Intent(MyRefrigerator.this, alertpage.class);
                startActivity(intent);
            }
        });
    }

    // 음식 아이템 클래스
    private class FoodItem {
        private String name;
        private String registrationDate;
        private String expiryDate;

        public FoodItem(String name, String registrationDate, String expiryDate) {
            this.name = name;
            this.registrationDate = registrationDate;
            this.expiryDate = expiryDate;
        }

        public String getName() {
            return name;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public String getExpiryDate() {
            return expiryDate;
        }
    }

    // 어댑터 클래스
    private class FoodAdapter extends ArrayAdapter<FoodItem> {
        private Context context;
        private ArrayList<FoodItem> items;

        public FoodAdapter(Context context, ArrayList<FoodItem> items) {
            super(context, 0, items);
            this.context = context;
            this.items = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item_food, parent, false);
            }

            // 아이템에서 데이터 가져오기
            FoodItem item = items.get(position);

            // 뷰 찾기
            CheckBox checkBox = view.findViewById(R.id.checkbox);
            TextView foodNameTextView = view.findViewById(R.id.food_name);
            TextView registrationDateTextView = view.findViewById(R.id.registration_date);
            TextView expiryDateTextView = view.findViewById(R.id.expiry_date);
            Button editButton = view.findViewById(R.id.edit_button);

            // 데이터 설정
            foodNameTextView.setText(item.getName());
            registrationDateTextView.setText(item.getRegistrationDate());
            expiryDateTextView.setText(item.getExpiryDate());

            // 체크박스 리스너 설정 등 필요한 작업 수행

            return view;
        }

    }
}