package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.healthapp.R;
import com.example.guest.healthapp.models.Food;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailFragment extends Fragment {
    @BindView(R.id.foodNameTextView) TextView mNameLabel;
    @BindView(R.id.foodImageView) ImageView mImageLabel;

    private Food mFood;

    public static FoodDetailFragment newInstance(Food food) {
        FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("food", Parcels.wrap(food));
        foodDetailFragment.setArguments(args);
        return foodDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFood = Parcels.unwrap(getArguments().getParcelable("food"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_food_detail_fragment, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mFood.getPhoto()).into(mImageLabel);

        mNameLabel.setText(mFood.getName());
        return view;
    }

}
