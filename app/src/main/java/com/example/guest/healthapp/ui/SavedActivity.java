package com.example.guest.healthapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedActivity extends AppCompatActivity {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private Query query = FirebaseDatabase
            .getInstance()
            .getReference("savedNutrients");

//    private Query userQuery = FirebaseDatabase
//            .getInstance()
//            .getReference("users")
//            .child(firebaseUser.getUid())
//            .child("photo");



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
//        Log.d("searched query", userQuery.toString());
        bindUserPicture();
        setupFirebaseAdapter();
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

    public void bindUserPicture() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users")
                .child(firebaseUser.getUid())
                .child("photo");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String photoString = dataSnapshot.getValue(String.class);
                Log.d("I am the photo!!!", photoString);
                try {
                    Bitmap imageBitmap = decodeFromFirebaseBase64(photoString);
                    mUserImageView.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });



    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
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

