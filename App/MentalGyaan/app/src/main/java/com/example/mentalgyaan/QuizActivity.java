package com.example.mentalgyaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mentalgyaan.Common.Common;
import com.example.mentalgyaan.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    Button btnPlay;
    FirebaseDatabase database;
    DatabaseReference questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        database=FirebaseDatabase.getInstance();

        btnPlay = (Button)findViewById(R.id.btnplay);
        questions = database.getReference("QuizQuestions");
        loadQuestion(Common.CategoryId);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this,PlayingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void loadQuestion(String categoryId)
    {
        if(Common.questionList.size()>0)
        {
            Common.questionList.clear();
        }
        questions.orderByChild("CatergoryId").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Question ques = postsnapshot.getValue(Question.class);
                    Common.questionList.add(ques);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Collections.shuffle(Common.questionList);

    }
}
