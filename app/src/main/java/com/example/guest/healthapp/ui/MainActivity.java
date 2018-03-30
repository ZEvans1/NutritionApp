package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedFoodReference;

    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.foodEditText) EditText mFoodEditText;
    @BindView(R.id.buttonFood) Button mButtonFood;
    @BindView(R.id.buttonNutrition) Button mButtonNutrition;
    @BindView(R.id.buttonAbout) Button mButtonAbout;
    @BindView(R.id.buttonSaved) Button mButtonSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedFoodReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_FOOD);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface caviarFont = Typeface.createFromAsset(getAssets(), "fonts/caviardreams.ttf");
        mAppNameTextView.setTypeface(caviarFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mButtonFood.setOnClickListener(this);
        mButtonAbout.setOnClickListener(this);
        mButtonNutrition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonFood) {
            String food = mFoodEditText.getText().toString();
            if (food.length() > 0) {
                saveFoodToFirebase(food);
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                intent.putExtra("food", food);
                addToSharedPreferences(food);
                startActivity(intent);
            } else {
                Toast.makeText(this, "EMPTY SEARCH - PLEASE ENTER TEXT", Toast.LENGTH_LONG).show();
            }
        }

        if (v == mButtonNutrition) {
            String food = mFoodEditText.getText().toString();
            if (food.length() > 0) {
                Intent intent = new Intent(MainActivity.this, NutrientActivity.class);
                intent.putExtra("food", food);
                startActivity(intent);
            } else {
                Toast.makeText(this, "EMPTY SEARCH - PLEASE ENTER TEXT", Toast.LENGTH_LONG).show();
            }
        }
        if (v == mButtonSaved) {
            Intent intent = new Intent(MainActivity.this, SavedActivity.class);
            startActivity(intent);
        }
        if (v == mButtonAbout) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    }

    public void saveFoodToFirebase(String food) {
        mSearchedFoodReference.push().setValue(food);
    }

    private void addToSharedPreferences(String food) {
//        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, food).apply();
    }
}
