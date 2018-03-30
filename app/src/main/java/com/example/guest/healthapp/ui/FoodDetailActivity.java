package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guest.healthapp.R;
import com.example.guest.healthapp.adapters.FoodPagerAdapter;
import com.example.guest.healthapp.models.Food;
import com.example.guest.healthapp.models.Nutrient;
import com.example.guest.healthapp.services.NutritionService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FoodDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private FoodPagerAdapter adapterViewPager;
    ArrayList<Food> mFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        mFoods = Parcels.unwrap(getIntent().getParcelableExtra("foods"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new FoodPagerAdapter(getSupportFragmentManager(), mFoods);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

//        for (int i = 0; i < mFoods.size(); i++) {
//            getDetails(mFoods.get(i).getName());
//        }
    }

//    private void getDetails(final String food) {
//        final NutritionService nutritionService = new NutritionService();
//        nutritionService.findDetails(food, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                nutrients = nutritionService.processNutrientResults(response);
//                Log.d("2nd call", "name " + food + " nutrients " + nutrients);
//            }
//        });
//    }
}
