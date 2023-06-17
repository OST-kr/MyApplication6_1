package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomFoodItemAdapter extends ArrayAdapter<FoodItem> {
    private Context mContext;
    private int mResource;
    private List<FoodItem> mFoodItemList;

    public CustomFoodItemAdapter(Context context, int resource, List<FoodItem> foodItemList) {
        super(context, resource, foodItemList);
        mContext = context;
        mResource = resource;
        mFoodItemList = foodItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        TextView foodNameTextView = convertView.findViewById(R.id.food_name);
        TextView categoryTextView = convertView.findViewById(R.id.registration_date);
        TextView expiryTextView = convertView.findViewById(R.id.expiry_date);

        FoodItem foodItem = mFoodItemList.get(position);
        foodNameTextView.setText(foodItem.getName());
        categoryTextView.setText(foodItem.getCategory());
        expiryTextView.setText(foodItem.getExpiry());

        return convertView;
    }
}
