package com.example.mentalgyaan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 3000;
    private CircleImageView splashLogo;
    private TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Initialize();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SliderActivity.class));
                finish();
            }
        },SPLASH_TIMEOUT);
    }

    private void Initialize() {
        splashLogo = (CircleImageView) findViewById(R.id.splash_logo);
     //   splashText = (TextView) findViewById(R.id.splash_text);
    }


}
