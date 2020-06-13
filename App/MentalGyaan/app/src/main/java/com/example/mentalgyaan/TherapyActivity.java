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
     int inCount=5;
    CountDownTimer mCount,mCount1;

    public int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);


       // Init();
        timer = (TextView)findViewById(R.id.timer);
        breatheinText = (TextView) findViewById(R.id.breathe_in);
        breatheimage = (CircleImageView) findViewById(R.id.breathe_image);
        startButton = (Button) findViewById(R.id.start_breathe);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breatheinText.setText("Breathe in for");
                inCount=5;
                timer.setText(String.valueOf(inCount));
              new CountDownTimer(5000, 1000) {
                    public void onTick(long millisec) {
                        --inCount;
                        timer.setText(String.valueOf(inCount));


                    }
                    public void onFinish() {
                        //breateOutText.setVisibility(View.VISIBLE);
                        breatheinText.setText("Breathe out for");
                        inCount=6;
                        new CountDownTimer(6000, 1000) {
                            public void onTick(long millisec) {
                                --inCount;
                                timer.setText(String.valueOf(inCount));


                            }
                            public void onFinish() {
                                //breateOutText.setVisibility(View.VISIBLE);
                                //breatheinText.setVisibility(View.INVISIBLE);

                            }
                        }.start();

                    }
                }.start();

            }
        });


    }

    private void Init() {

        timer = (TextView)findViewById(R.id.timer);
        breatheinText = (TextView) findViewById(R.id.breathe_in);
        breatheimage = (CircleImageView) findViewById(R.id.breathe_image);
        startButton = (Button) findViewById(R.id.start_breathe);

    }
}
