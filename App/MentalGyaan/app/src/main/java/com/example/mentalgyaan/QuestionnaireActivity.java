package com.example.mentalgyaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class QuestionnaireActivity extends AppCompatActivity {


    private ViewPager mSlideViewPager;
    private QuestionAdapter questionAdapter;
    private Button buttonNext, enterButton;
    private int count = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference Ref;
    private EditText answer;
    private String randomID;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);


        answer = (EditText)findViewById(R.id.question_ans);
        mSlideViewPager = (ViewPager) findViewById(R.id.slide_viewpager1);
        buttonNext = (Button) findViewById(R.id.next_button);
        enterButton = (Button) findViewById(R.id.enter);
        QuestionAdapter questionAdapter = new QuestionAdapter(this);

        mSlideViewPager.setAdapter(questionAdapter);


        mSlideViewPager.addOnPageChangeListener(viewListener);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionnaireActivity.this, MainActivity.class));

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (count != 3) {
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }


            }
        });


    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            mCurrentPage = position;


            if(position == 2)
            {

                enterButton.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.GONE);

            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}

