package com.example.guest.healthapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.R;
import com.example.guest.healthapp.models.Nutrient;
import com.example.guest.healthapp.services.NutritionService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NutrientActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @BindView(R.id.nutrientImageView) ImageView mNutrientImageView;
    @BindView(R.id.foodNameTextView) TextView mFoodNameTextView;
    @BindView(R.id.caloriesTextView) TextView mCaloriesTextView;
    @BindView(R.id.sugarsTextView) TextView mSugarsTextView;
    @BindView(R.id.buttonSaveNutrient) Button mButtonSaveNutrient;
    @BindView(R.id.servingTextView) TextView mServingTextView;
    @BindView(R.id.sodiumTextView) TextView mSodiumTextView;
    @BindView(R.id.fatTextView) TextView mFatTextView;
    @BindView(R.id.satfatTextView) TextView mSatFatTextView;
    @BindView(R.id.cholesterolTextView) TextView mCholesterolTextView;
    @BindView(R.id.carbsTextView) TextView mCarbsTextView;
    @BindView(R.id.proteinTextView) TextView mProteinTextView;
    public ArrayList<Nutrient> nutrients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String food = intent.getStringExtra("food");
        getDetails(food);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mButtonSaveNutrient.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonSaveNutrient) {
            DatabaseReference nutrientRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_NUTRIENTS);
            nutrientRef.push().setValue(nutrients.get(0));
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();

        }
    }

//    private void addToSharedPreferences(String )

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
                            if (!nutrients.get(0).getThumb().equals("null")) {

                                Picasso.get().load(nutrients.get(0).getThumb()).into(mNutrientImageView);
                            } else {
                                mNutrientImageView.setImageResource(R.drawable.nullimg);
                            }

                            mFoodNameTextView.setText(nutrients.get(0).getFoodName());

                            String servingText = "Serving size:" + " " + nutrients.get(0).getServingUnit();
                            mServingTextView.setText(servingText);

                            String caloriesText = getString(R.string.caloriesString) + " " + nutrients.get(0).getNfCalories();
                            mCaloriesTextView.setText(caloriesText);

                            String totalFatText = "Total Fat:" + " " + nutrients.get(0).getNfTotalFat();
                            mFatTextView.setText(totalFatText);

                            String satFatText = "Sat. Fat:" + " " + nutrients.get(0).getNfSaturatedFat();
                            mSatFatTextView.setText(satFatText);

                            String cholesterolText = "Cholesterol:" + " " + nutrients.get(0).getNfCholesterol();
                            mCholesterolTextView.setText(cholesterolText);

                            String sodiumText = "Sodium:" + " " + nutrients.get(0).getNfSodium();
                            mSodiumTextView.setText(sodiumText);

                            String carbsText = "Total carbs:" + " " + nutrients.get(0).getNfTotalCarbohydrate();
                            mCarbsTextView.setText(carbsText);

                            String fiberText = "Fiber:" + " " + nutrients.get(0).getNfDietaryFiber();


                            String proteinText = "Protein:" + " " + nutrients.get(0).getNfProtein();
                            mProteinTextView.setText(proteinText);

                            String sugarsText = getString(R.string.sugarsString) + " " + nutrients.get(0).getNfSugars();
                            mSugarsTextView.setText(sugarsText);
                        }
                    });
                } else {
                    NutrientActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCaloriesTextView.setText("No results");
                            mSugarsTextView.setText("No results");
                            Toast.makeText(NutrientActivity.this, "0 RESULTS", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}
