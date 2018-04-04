package com.example.guest.healthapp.services;

import android.util.Log;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.models.Food;
import com.example.guest.healthapp.models.Nutrient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NutritionService {


    public static void findFoods(String food, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NUTRITION_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.NUTRITION_FOOD_QUERY_PARAMETER, food);
        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .header("x-app-id", Constants.NUTRITION_ID)
                .header("x-app-key", Constants.NUTRITION_KEY)
                .header("x-remote-user-id", "0")
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void findDetails(String foodRequestName, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NUTRITION_NATURAL_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();

        RequestBody formBody = new FormBody.Builder()
                .add("query", foodRequestName)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("x-app-id", Constants.NUTRITION_ID)
                .header("x-app-key", Constants.NUTRITION_KEY)
                .header("x-remote-user-id", "0")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Food> processResults(Response response) {
        ArrayList<Food> foods = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject nutritionJSON = new JSONObject(jsonData);
            JSONArray commonJSON = nutritionJSON.getJSONArray("common");
            for (int i = 0; i < commonJSON.length(); i++) {
                JSONObject foodJSON = commonJSON.getJSONObject(i);

                String name = foodJSON.getString("food_name");

                String photo = foodJSON.getJSONObject("photo").getString("thumb");

                String tagId = foodJSON.getString("tag_id");

                Food food = new Food(name, photo, tagId);
                foods.add(food);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public ArrayList<Nutrient> processNutrientResults(Response response) {
        ArrayList<Nutrient> nutrients = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject nutritionJSON = new JSONObject(jsonData);
            JSONArray foodsJSON = nutritionJSON.getJSONArray("foods");
            for (int i = 0; i < foodsJSON.length(); i++) {
                JSONObject foodJSON = foodsJSON.getJSONObject(i);

                String foodName = foodJSON.getString("food_name");

                String servingUnit = foodJSON.getString("serving_unit");

                String nfCalories = foodJSON.getString("nf_calories");

                String nfTotalFat = foodJSON.getString("nf_total_fat");

                String nfSaturatedFat = foodJSON.getString("nf_saturated_fat");

                String nfCholesterol = foodJSON.getString("nf_cholesterol");

                String nfSodium = foodJSON.getString("nf_sodium");

                String nfTotalCarbohydrate = foodJSON.getString("nf_total_carbohydrate");

                String nfDietaryFiber = foodJSON.getString("nf_dietary_fiber");

                String nfProtein = foodJSON.getString("nf_protein");

                String nfSugars = foodJSON.getString("nf_sugars");

                Nutrient nutrient = new Nutrient(foodName, servingUnit, nfCalories, nfTotalFat, nfSaturatedFat, nfCholesterol, nfSodium, nfTotalCarbohydrate, nfDietaryFiber, nfProtein, nfSugars);
                nutrients.add(nutrient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nutrients;

    }
}

