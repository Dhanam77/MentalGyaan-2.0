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

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ViewHolder> {


    private View myView;
    private Context mContext;


    private ArrayList<Helps> foodItemsArrayList;


    public HelpAdapter(Context mContext, ArrayList<Helps> foodItemsArrayList) {
        this.mContext = mContext;
        this.foodItemsArrayList = foodItemsArrayList;
    }

    @NonNull
    @Override
    public HelpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_layout, parent, false);
        return new HelpAdapter.ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HelpAdapter.ViewHolder holder, int position) {

        final Helps items = foodItemsArrayList.get(position);
        holder.name.setText(items.getName());
        holder.address.setText(items.getLocation());
        holder.contact.setText(items.getContact());



        holder.helpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, HelpWebsiteActivity.class).putExtra("name", items.getName()));
            }
        });



    }

    @Override
    public int getItemCount() {
        return foodItemsArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView address, name, contact;
        private CardView helpLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            helpLayout = (CardView) myView.findViewById(R.id.help_layout);
            name = (TextView) myView.findViewById(R.id.name);
            address = (TextView) myView.findViewById(R.id.address1);
            contact = (TextView) myView.findViewById(R.id.contact1);


        }
    }
}


