package com.example.mentalgyaan;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TherapyAdapter extends RecyclerView.Adapter<TherapyAdapter.ViewHolder> {


    private View myView;
    private Context mContext;
    private ArrayList<Therapies> foodItemsArrayListFull;


    private ArrayList<Therapies> foodItemsArrayList;


    public TherapyAdapter(Context mContext, ArrayList<Therapies> foodItemsArrayList) {
        this.mContext = mContext;
        this.foodItemsArrayList = foodItemsArrayList;
    }

    @NonNull
    @Override
    public TherapyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.therapy_layout, parent, false);
        return new TherapyAdapter.ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TherapyAdapter.ViewHolder holder, int position) {

        final Therapies items = foodItemsArrayList.get(position);
        holder.name.setText(items.getName());
        holder.text.setText(items.getText());

        Glide.with(mContext)
                .load(items.getImage())
                .into(holder.image);


        holder.therapyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(items.getText().equalsIgnoreCase("breathing exercise"))
                {
                    mContext.startActivity(new Intent(mContext, TherapyActivity.class));

                }

                if(items.getText().equalsIgnoreCase("guided discovery"))
                {
                    mContext.startActivity(new Intent(mContext, QuestionnaireActivity.class));

                }

                if(items.getText().equalsIgnoreCase("peaceful meditation"))
                {
                    mContext.startActivity(new Intent(mContext, AudioActivity.class));

                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return foodItemsArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text, name;
        private CardView therapyLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            therapyLayout = (CardView) myView.findViewById(R.id.therapy_layout);
            image = (ImageView) myView.findViewById(R.id.therapy_image);
            name = (TextView) myView.findViewById(R.id.therapy_name);
            text = (TextView) myView.findViewById(R.id.therapy_text);


        }
    }
}


