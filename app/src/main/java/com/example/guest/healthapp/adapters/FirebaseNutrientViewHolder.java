package com.example.guest.healthapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.guest.healthapp.R;
import com.example.guest.healthapp.models.Nutrient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseNutrientViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
//    FirebaseUser user;

    public FirebaseNutrientViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindNutrient(Nutrient nutrient) {
        TextView foodNameTextView = (TextView) mView.findViewById(R.id.foodNameTextView);
        foodNameTextView.setText(nutrient.getFoodName());
    }

//    public void onClick(View view) {
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("chats");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<String> chatIds = new ArrayList<String>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    chatIds.add(snapshot.getKey());
//
//                }
//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, ChatDetailActivity.class);
//                intent.putExtra("chatId", chatIds.get(itemPosition));
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("Chat on click", databaseError.toString());
//            }
//        });
//    }
}
