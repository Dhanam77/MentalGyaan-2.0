package com.example.mentalgyaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

public class ActivitiesActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        SetupToolbar();
    }

    private void SetupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.act_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Activities to Elevate Mood");
    }
}
