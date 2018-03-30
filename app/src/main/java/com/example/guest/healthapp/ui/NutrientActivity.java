package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.healthapp.R;
import com.example.guest.healthapp.models.Nutrient;
import com.example.guest.healthapp.services.NutritionService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NutrientActivity extends AppCompatActivity {

    @BindView(R.id.foodNameTextView) TextView mFoodNameTextView;
    @BindView(R.id.caloriesTextView) TextView mCaloriesTextView;
    @BindView(R.id.sugarsTextView) TextView mSugarsTextView;
    public ArrayList<Nutrient> nutrients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String food = intent.getStringExtra("food");
        getDetails(food);

        mFoodNameTextView.setText(food);
    }

    private void getDetails(String food) {
        final NutritionService nutritionService = new NutritionService();
        nutritionService.findDetails(food, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                nutrients = nutritionService.processNutrientResults(response);
                if (nutrients.size() > 0) {
                    NutrientActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCaloriesTextView.setText(nutrients.get(0).getNfCalories());
                        }
                    });
                } else {
                    NutrientActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCaloriesTextView.setText("No results");
                            Toast.makeText(NutrientActivity.this, "0 RESULTS", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}
