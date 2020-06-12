package com.example.mentalgyaan;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
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

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {


    private View myView;
    private Context mContext;
    private ArrayList<Discovers> foodItemsArrayListFull;

    private DatabaseReference Ref;
    String URL;

    private ArrayList<Discovers> foodItemsArrayList;


    public DiscoverAdapter(Context mContext, ArrayList<Discovers> foodItemsArrayList) {
        this.mContext = mContext;
        this.foodItemsArrayList = foodItemsArrayList;
    }

    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_layout, parent, false);
        return new DiscoverAdapter.ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiscoverAdapter.ViewHolder holder, int position) {


        final Discovers items = foodItemsArrayList.get(position);
        holder.name.setText(items.getTopicname());
        holder.text.setText(items.getTopicdes());

        Glide.with(mContext)
                .load(items.getTopicimage())
                .into(holder.image);





        holder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DiscoverWebsiteActivity.class);
                i.putExtra("name",items.getTopicname());
                mContext.startActivity(i);
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
        private RelativeLayout Layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) myView.findViewById(R.id.topic_image);
            name = (TextView) myView.findViewById(R.id.topic_name);
            text = (TextView) myView.findViewById(R.id.topic_desc);
            Layout = (RelativeLayout)myView.findViewById(R.id.disclayout);

            Ref = FirebaseDatabase.getInstance().getReference();

        }
    }
}


