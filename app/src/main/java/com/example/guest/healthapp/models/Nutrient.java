package com.example.guest.healthapp.models;


import org.parceler.Parcel;

@Parcel
public class Nutrient {
    String foodName;
    String servingUnit;
    String nfCalories;
    String nfTotalFat;
    String nfSaturatedFat;
    String nfCholesterol;
    String nfSodium;
    String nfTotalCarbohydrate;
    String nfDietaryFiber;
    String nfProtein;
    String nfSugars;

    public Nutrient() {}

    public Nutrient(String foodName, String servingUnit, String nfCalories, String nfTotalFat, String nfSaturatedFat, String nfCholesterol, String nfSodium, String nfTotalCarbohydrate, String nfDietaryFiber, String nfProtein, String nfSugars) {
        this.foodName = foodName;
        this.servingUnit = servingUnit;
        this.nfCalories = nfCalories;
        this.nfTotalFat = nfTotalFat;
        this.nfSaturatedFat = nfSaturatedFat;
        this.nfCholesterol = nfCholesterol;
        this.nfSodium = nfSodium;
        this.nfTotalCarbohydrate = nfTotalCarbohydrate;
        this.nfDietaryFiber = nfDietaryFiber;
        this.nfProtein = nfProtein;
        this.nfSugars = nfSugars;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public String getNfCalories() {
        return nfCalories;
    }

    public void setNfCalories(String nfCalories) {
        this.nfCalories = nfCalories;
    }

    public String getNfTotalFat() {
        return nfTotalFat;
    }

    public void setNfTotalFat(String nfTotalFat) {
        this.nfTotalFat = nfTotalFat;
    }

    public String getNfSaturatedFat() {
        return nfSaturatedFat;
    }

    public void setNfSaturatedFat(String nfSaturatedFat) {
        this.nfSaturatedFat = nfSaturatedFat;
    }

    public String getNfCholesterol() {
        return nfCholesterol;
    }

    public void setNfCholesterol(String nfCholesterol) {
        this.nfCholesterol = nfCholesterol;
    }

    public String getNfSodium() {
        return nfSodium;
    }

    public void setNfSodium(String nfSodium) {
        this.nfSodium = nfSodium;
    }

    public String getNfTotalCarbohydrate() {
        return nfTotalCarbohydrate;
    }

    public void setNfTotalCarbohydrate(String nfTotalCarbohydrate) {
        this.nfTotalCarbohydrate = nfTotalCarbohydrate;
    }

    public String getNfDietaryFiber() {
        return nfDietaryFiber;
    }

    public void setNfDietaryFiber(String nfDietaryFiber) {
        this.nfDietaryFiber = nfDietaryFiber;
    }

    public String getNfProtein() {
        return nfProtein;
    }

    public void setNfProtein(String nfProtein) {
        this.nfProtein = nfProtein;
    }

    public String getNfSugars() {
        return nfSugars;
    }

    public void setNfSugars(String nfSugars) {
        this.nfSugars = nfSugars;
    }
}
