package com.example.guest.healthapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.healthapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SavedActivity extends AppCompatActivity {
    private Query query = FirebaseDatabase
            .getInstance()
            .getReference("savedNutrients");
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
    }
}
