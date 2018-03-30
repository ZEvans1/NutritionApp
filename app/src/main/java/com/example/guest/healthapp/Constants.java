package com.example.guest.healthapp;


public class Constants {
    public static final String NUTRITION_ID = BuildConfig.NUTRITION_ID;
    public static final String NUTRITION_KEY = BuildConfig.NUTRITION_KEY;
    public static final String NUTRITION_BASE_URL = "https://trackapi.nutritionix.com/v2/search/instant";
    public static final String NUTRITION_FOOD_QUERY_PARAMETER = "query";

    public static final String NUTRITION_NATURAL_BASE_URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";

    public static final String PREFERENCES_FOOD_KEY = "food";
    public static final String FIREBASE_CHILD_SEARCHED_FOOD = "searchedFood";

    public static final String PREFERENCES_NUTRIENT_KEY = "nutrient";
    public static final String FIREBASE_CHILD_NUTRIENTS = "savedNutrients";

}
