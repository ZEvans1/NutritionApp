package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedFoodReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface caviarFont = Typeface.createFromAsset(getAssets(), "fonts/caviardreams.ttf");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };
        mAppNameTextView.setTypeface(caviarFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mButtonFood.setOnClickListener(this);
        mButtonAbout.setOnClickListener(this);
        mButtonNutrition.setOnClickListener(this);
        mButtonSaved.setOnClickListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
