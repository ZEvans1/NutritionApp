package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.healthapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.foodEditText) EditText mFoodEditText;
    @BindView(R.id.buttonFood) Button mButtonFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface caviarFont = Typeface.createFromAsset(getAssets(), "fonts/caviardreams.ttf");
        mAppNameTextView.setTypeface(caviarFont);

        mButtonFood.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonFood) {
            String food = mFoodEditText.getText().toString();
            if (food.length() > 0) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                intent.putExtra("food", food);
                startActivity(intent);
            } else {
                Toast.makeText(this, "EMPTY SEARCH - PLEASE ENTER TEXT", Toast.LENGTH_LONG).show();
            }
        }
    }
}
