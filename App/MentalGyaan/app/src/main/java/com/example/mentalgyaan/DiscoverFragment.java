package com.example.mentalgyaan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {


    private View mView;
    private RecyclerView discoverRecycler;
    private DatabaseReference Ref;
    private ArrayList<Discovers> discoversArrayList = new ArrayList<>();




    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_discover, container, false);
        Init();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        discoverRecycler.setLayoutManager(layoutManager);

        Ref.child("DiscoverItems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                discoversArrayList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    discoversArrayList.add(new Discovers(dataSnapshot1.child("topicName").getValue().toString(),
                            dataSnapshot1.child("topicText").getValue().toString(),
                            dataSnapshot1.child("topicImage").getValue().toString()));
                }

                DiscoverAdapter adapter = new DiscoverAdapter(getContext(), discoversArrayList);
                discoverRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return mView;
    }

    private void Init() {

        Ref = FirebaseDatabase.getInstance().getReference();
        discoverRecycler = (RecyclerView)mView.findViewById(R.id.discover_recycler);
    }

}
