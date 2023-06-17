package com.example.myapplication;

public class FoodItem {
    private String name;
    private String category;
    private String expiry;

    public FoodItem(String name, String category, String expiry) {
        this.name = name;
        this.category = category;
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getExpiry() {
        return expiry;
    }
}
