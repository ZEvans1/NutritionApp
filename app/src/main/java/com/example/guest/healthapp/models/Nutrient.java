package com.example.guest.healthapp.models;


import org.parceler.Parcel;

@Parcel
public class Nutrient {
    String foodName;
    String nfCalories;
    String nfSugars;

    public Nutrient() {}

    public Nutrient(String foodName, String nfCalories, String nfSugars) {
        this.foodName = foodName;
        this.nfCalories = nfCalories;
        this.nfSugars = nfSugars;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getNfCalories() {
        return nfCalories;
    }

    public String getNfSugars() {
        return nfSugars;
    }
}
