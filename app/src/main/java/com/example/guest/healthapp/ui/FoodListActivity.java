package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.R;
import com.example.guest.healthapp.adapters.FoodListAdapter;
import com.example.guest.healthapp.models.Food;
import com.example.guest.healthapp.services.NutritionService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String mRecentFood;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private FoodListAdapter mAdapter;
    public static final String TAG = FoodListActivity.class.getSimpleName();
    public ArrayList<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String food = intent.getStringExtra("food");
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getFoods(food);

        mRecentFood = mSharedPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);
        Log.d("Shared Pref Food", mRecentFood);

    }

    private void getFoods(String food) {
        final NutritionService nutritionService = new NutritionService();
        nutritionService.findFoods(food, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                foods = nutritionService.processResults(response);

                if (foods.size() > 0) {
                    FoodListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new FoodListAdapter(getApplicationContext(), foods);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FoodListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    });
                } else {
                    FoodListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new FoodListAdapter(getApplicationContext(), foods);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FoodListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                            Toast.makeText(FoodListActivity.this, "0 RESULTS - TRY ANOTHER TERM", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
