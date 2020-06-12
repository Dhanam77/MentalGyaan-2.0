package com.example.mentalgyaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class TherapyActivity extends AppCompatActivity {


    private RelativeLayout breatheLayout;
    private CircleImageView breatheimage;
    private Button startButton;
    private TextView breatheinText, breateOutText, timer;
    private int inCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);


        Init();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new CountDownTimer(10000, 1000) {
                    public void onTick(long millisec) {
                        timer.setText(String.valueOf(inCount));
                        inCount--;
                    }

                    public void onFinish() {
                        breateOutText.setVisibility(View.VISIBLE);
                        breatheinText.setVisibility(View.INVISIBLE);

                    }
                }.start();
            }
        });


    }

    private void Init() {

        timer = (TextView)findViewById(R.id.timer);
        breateOutText = (TextView) findViewById(R.id.breate_out);
        breatheinText = (TextView) findViewById(R.id.breathe_in);
        breatheimage = (CircleImageView) findViewById(R.id.breathe_image);
        startButton = (Button) findViewById(R.id.start_breathe);

    }
}
