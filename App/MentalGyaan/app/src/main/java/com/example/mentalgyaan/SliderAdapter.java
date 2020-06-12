package com.example.mentalgyaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slideImages = {

            R.drawable.is1,
            R.drawable.is2,
            R.drawable.is3

    };

    public String[] slideHeadings ={
            "Get Confident More Than Ever",
            "Get Benefits of CBT Techniques",
            "Get Positive Quotes Everyday"
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
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.iv_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.iv_text);

        slideImageView.setImageResource(slideImages[position]);
        slideHeading.setText(slideHeadings[position]);


        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);  //todo: RelativeLayout??
    }
}
