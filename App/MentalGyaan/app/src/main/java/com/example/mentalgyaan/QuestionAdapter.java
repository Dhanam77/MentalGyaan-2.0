package com.example.mentalgyaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.database.DatabaseReference;


public class QuestionAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    EditText ans;
    private DatabaseReference Ref;
    public QuestionAdapter(Context context){
        this.context=context;
    }



    public String[] slideHeadings ={
            "How often do you feel depressed?",
            "Have you had any thoughts of suicide",
            "Do you prefer to stay home or go out?"
    };



    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.question_layout, container, false);

        TextView slideHeading = (TextView) view.findViewById(R.id.question_text);


        slideHeading.setText(slideHeadings[position]);


        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
