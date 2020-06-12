package com.example.mentalgyaan;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlayingActivity extends AppCompatActivity implements View.OnClickListener{

    final static long INTERVAL = 1000;
    final static long TIMEOUT = 15000;
    int progressValue = 0;

    CountDownTimer mCountDown;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    int index=0,score=0,thisQuestion=0,totalQuestion,correctAnswer;
    FirebaseDatabase database;
    DatabaseReference question;
    ProgressBar progressBar;
    Button btnA,btnB,btnC,btnD;
    TextView textScore,textQuestionnum,question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        database = FirebaseDatabase.getInstance();
        question = database.getReference("QuizQuestions");
        textScore = (TextView) findViewById(R.id.textscore);
        textQuestionnum = (TextView) findViewById(R.id.texttotalQuestion);
        question_text = (TextView) findViewById(R.id.question_text);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        btnA=(Button)findViewById(R.id.BtnAnswerA);

        btnB=(Button)findViewById(R.id.BtnAnswerB);

        btnC=(Button)findViewById(R.id.BtnAnswerC);

        btnD=(Button)findViewById(R.id.BtnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();

        if(index<totalQuestion-1)
        {
            Button clickButton = (Button)v;
            if(clickButton.getText().equals(Common.questionList.get(index).getAnswerA()))
            {
                score += 0;
                //correctAnswer++;
                showQuestion(++index);
            }
            if(clickButton.getText().equals(Common.questionList.get(index).getAnswerB()))
            {
                score += 2;
                //correctAnswer++;
                showQuestion(++index);
            }
            if(clickButton.getText().equals(Common.questionList.get(index).getAnswerC()))
            {
                score += 5;
                //correctAnswer++;
                showQuestion(++index);
            }
            if(clickButton.getText().equals(Common.questionList.get(index).getAnswerD()))
            {
                score += 10;
                //correctAnswer++;
                showQuestion(++index);
            }

        }
        else
        {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle dataSent = new Bundle();
            dataSent.putInt("SCORE",score);
            databaseReference.child("Details").child("Score").setValue(score);
            dataSent.putInt("TOTAL",totalQuestion);
            dataSent.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSent);
            Toast.makeText(PlayingActivity.this,"Personalised Your feed",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        textScore.setText(String.format("%d",score));

    }

    private void showQuestion(int index) {
        if(index<totalQuestion)
        {
            thisQuestion++;
            textQuestionnum.setText(String.format("%d/%d",thisQuestion,(totalQuestion-1)));
            progressBar.setProgress(0);
            progressValue=0;

            question_text.setText(Common.questionList.get(index).getQuestion());

            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            mCountDown.start();
        }
        else
        {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle dataSent = new Bundle();
            dataSent.putInt("SCORE",score);
            databaseReference.child("Details").child("Score").setValue(score);
            dataSent.putInt("TOTAL",totalQuestion);
            dataSent.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSent);
            Toast.makeText(PlayingActivity.this,"Personalised Your feed",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        totalQuestion=Common.questionList.size();

        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;

            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };

        showQuestion(++index);
    }
}
