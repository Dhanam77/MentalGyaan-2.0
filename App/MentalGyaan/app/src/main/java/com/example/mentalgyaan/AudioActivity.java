package com.example.mentalgyaan;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class AudioActivity extends AppCompatActivity {


    MediaPlayer mplayer;

    public void PlayAudioFunction(View view) {


        mplayer.start();
    }

    public void PauseAudioFunction(View view) {

        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        mplayer = MediaPlayer.create(this, R.raw.stressrelief);

    }
}