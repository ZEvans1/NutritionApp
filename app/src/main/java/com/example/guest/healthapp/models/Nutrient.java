package com.example.guest.healthapp.models;


import org.parceler.Parcel;

@Parcel
public class Nutrient {
    String nfCalories;
    String nfSugars;

    public Nutrient() {}

    public Nutrient(String nfCalories, String nfSugars) {
        this.nfCalories = nfCalories;
        this.nfSugars = nfSugars;
    }

    public String getNfCalories() {
        return nfCalories;
    }

    public String getNfSugars() {
        return nfSugars;
    }
}
