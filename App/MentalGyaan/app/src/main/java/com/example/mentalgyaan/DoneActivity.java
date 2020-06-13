package com.example.mentalgyaan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoneActivity extends AppCompatActivity {

    Button btntryagain;
    TextView textresultscore,getTextresultquestion;

    FirebaseDatabase database;
    DatabaseReference question_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        final AlertDialog builder = new AlertDialog.Builder(this).create();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                builder.setMessage("Personalizing your feed");
                builder.setCancelable(false);
            }
        },3000);



        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("QuizQuestion_score");

        textresultscore = (TextView) findViewById(R.id.txttotalscore);
        getTextresultquestion = (TextView) findViewById(R.id.texttotalQuestion);

        btntryagain = (Button) findViewById(R.id.buttontryagain);

        btntryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneActivity.this,QuizActivity.class);
                startActivity(intent);
                finish();

            }
        });
        Bundle extra = getIntent().getExtras();
        if(extra!=null)
        {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");



        }
    }
}
