package com.example.popescu.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

public class DetailsActivity extends AppCompatActivity {
    private static String LOG_TAG = "DetailView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
        FragmentDetailsActivity detailFragment = (FragmentDetailsActivity) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        detailFragment.movie = movie;

        Log.v(LOG_TAG, "Activity on create finished");
    }
}
