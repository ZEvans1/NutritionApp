package com.example.guest.healthapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.guest.healthapp.Constants;
import com.example.guest.healthapp.R;
import com.example.guest.healthapp.adapters.FirebaseNutrientViewHolder;
import com.example.guest.healthapp.adapters.FoodListAdapter;
import com.example.guest.healthapp.models.Nutrient;
import com.example.guest.healthapp.models.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedActivity extends AppCompatActivity {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private Query query = FirebaseDatabase
            .getInstance()
            .getReference("savedNutrients");

    private Query userQuery = FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(firebaseUser.getUid())
            .child("photo");
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userImageCode;
    private String userId;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.userImageView) ImageView mUserImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        ButterKnife.bind(this);
        Log.d("searched query", userQuery.toString());
        setupFirebaseAdapter();
//        userId = firebaseUser.getUid();
//        Log.d("Look at me!!!", userId);
//        Log.d("Here's the user query: ", userQuery.toString());
    }

    private void setupFirebaseAdapter() {
        FirebaseRecyclerOptions<Nutrient> options =
                new FirebaseRecyclerOptions.Builder<Nutrient>()
                .setQuery(query, Nutrient.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Nutrient, FirebaseNutrientViewHolder>(options) {
            @Override
            protected void onBindViewHolder(FirebaseNutrientViewHolder holder, int position, Nutrient model) {
                holder.bindNutrient(model);
            }

            @NonNull
            @Override
            public FirebaseNutrientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.nutrient_list_item, parent, false);

                return new FirebaseNutrientViewHolder(view);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAdapter.stopListening();
    }
}

