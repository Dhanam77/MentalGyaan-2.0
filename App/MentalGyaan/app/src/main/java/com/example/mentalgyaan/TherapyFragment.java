package com.example.mentalgyaan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TherapyFragment extends Fragment {

    private View mView;
    private DatabaseReference Ref;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private ArrayList<Therapies> therapiesArrayList = new ArrayList<>();
    private RecyclerView therapyRecycler;
    private ProgressBar loadingBar;
    private int m;
    String score;

    public TherapyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_therapy, container, false);


        Init();
        Ref.child("Details").child("Score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                score = dataSnapshot.getValue().toString();
                m=Integer.valueOf(score);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SetupTherapies();

        return mView;

    }

    private void SetupTherapies()  {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        therapyRecycler.setLayoutManager(layoutManager);

        if (m > 60) {
            Ref.child("Therapies").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    therapiesArrayList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        therapiesArrayList.add(new Therapies(dataSnapshot1.child("Image").getValue().toString(),
                                dataSnapshot1.child("Text").getValue().toString(),
                                dataSnapshot1.child("Name").getValue().toString()));
                    }
                    TherapyAdapter adapter = new TherapyAdapter(getContext(), therapiesArrayList);
                    therapyRecycler.setAdapter(adapter);
                    loadingBar.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        } else if (m > 20 && m <= 60) {
            Ref.child("Therapies2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    therapiesArrayList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        therapiesArrayList.add(new Therapies(dataSnapshot1.child("Image").getValue().toString(),
                                dataSnapshot1.child("Text").getValue().toString(),
                                dataSnapshot1.child("Name").getValue().toString()));
                    }
                    TherapyAdapter adapter = new TherapyAdapter(getContext(), therapiesArrayList);
                    therapyRecycler.setAdapter(adapter);
                    loadingBar.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        } else {
            Ref.child("Therapies1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    therapiesArrayList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        therapiesArrayList.add(new Therapies(dataSnapshot1.child("Image").getValue().toString(),
                                dataSnapshot1.child("Text").getValue().toString(),
                                dataSnapshot1.child("Name").getValue().toString()));
                    }
                    TherapyAdapter adapter = new TherapyAdapter(getContext(), therapiesArrayList);
                    therapyRecycler.setAdapter(adapter);
                    loadingBar.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }


    }

    private void Init() {

        therapyRecycler = (RecyclerView)mView.findViewById(R.id.therapy_sesions);
        Ref = FirebaseDatabase.getInstance().getReference();

        loadingBar = (ProgressBar)mView.findViewById(R.id.load_therapies);
    }



}
