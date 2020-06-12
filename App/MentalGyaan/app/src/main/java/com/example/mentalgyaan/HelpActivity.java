package com.example.mentalgyaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {


    private RecyclerView helpRecycler;
    private Toolbar mToolbar;
    private DatabaseReference Ref;
    private ArrayList<Helps> helpsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Init();

        SetupRecycler();
    }

    private void SetupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
        helpRecycler.setLayoutManager(layoutManager);
        Ref.child("Help").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                helpsArrayList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    String location = dataSnapshot1.child("location").getValue().toString();
                    String name = dataSnapshot1.child("Name").getValue().toString();
                    String contact = dataSnapshot1.child("Contact").getValue().toString();
                    helpsArrayList.add(new Helps(location,contact, name));
                }
                HelpAdapter adapter = new HelpAdapter(HelpActivity.this, helpsArrayList);
                helpRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }


    private void SetupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.help_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Need Help?");


    }

    private void Init() {
        helpRecycler = (RecyclerView)findViewById(R.id.help_recycler);
        Ref = FirebaseDatabase.getInstance().getReference();
        SetupToolbar();
    }
}
