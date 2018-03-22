package com.example.guest.healthapp.services;

import android.util.Log;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.models.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NutritionService {

    private static final String TAG = "activity";

    public static void findFoods(String food, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NUTRITION_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.NUTRITION_FOOD_QUERY_PARAMETER, food);
        String url = urlBuilder.build().toString();
        Log.v(TAG, url);


        Request request = new Request.Builder()
                .url(url)
                .header("x-app-id", Constants.NUTRITION_ID)
                .header("x-app-key", Constants.NUTRITION_KEY)
                .header("x-remote-user-id", "0")
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
                String photo = foodJSON.getString("photo");
                String tagId = foodJSON.getString("tag_id");
                Food food = new Food(name, photo, tagId);
                foods.add(food);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return foods;
    }
}
