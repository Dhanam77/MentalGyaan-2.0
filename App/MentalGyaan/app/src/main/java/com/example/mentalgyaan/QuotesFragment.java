package com.example.mentalgyaan;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuotesFragment extends Fragment {

    private View mView;
    private DatabaseReference Ref;
    private TextView quoteText;
    private String image, name;
    private ImageView quoteImage;
    private CardView quoteView, actView;

    private ProgressBar loadingBar;

    public QuotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_quotes, container, false);

        Init();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateData = formatter.format(date);

        Log.e("date", " " + dateData);

        quoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuotesActivity.class);
                startActivity(intent);
            }
        });
        actView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivitiesActivity
                        .class);
                startActivity(intent);
            }
        });


        return mView;
    }

    private void Init() {
        Ref = FirebaseDatabase.getInstance().getReference();



        quoteView = (CardView)mView.findViewById(R.id.quote_view);
        actView = (CardView)mView.findViewById(R.id.act_view);
    }

}
