package com.example.guest.healthapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.healthapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.aboutTextView) TextView mAboutTextView;
    @BindView(R.id.websiteTextView) TextView mWebsiteTextView;
    @BindView(R.id.apiImageView) ImageView mApiImageView;

    private String websiteURL = "https://www.nutritionix.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        mApiImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mApiImageView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(websiteURL));
            startActivity(webIntent);
        }
    }
}
