package com.example.mentalgyaan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {

    private View mView;
    private DatabaseReference Ref;
    private EditText diaryText;
    private TextView diaryDate;
    private Button submitButton;
    private String randomID;
    Random random = new Random();


    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_diary, container, false);
        randomID = String.valueOf(random.nextInt(100));
        Init();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        final String dateData = formatter.format(date);

       diaryDate.setText(dateData);

        Ref.child("Diaries").child(dateData).child("data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String s = dataSnapshot.getValue().toString();
                    if (s != null) {
                        diaryText.setText(s);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = diaryText.getText().toString();

                if (text != null) {
                    Ref.child("Diaries").child(dateData).child("data").setValue(text);
                    Toast.makeText(getContext(), "Diary Updated", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TherapyFragment()).commit();

                }
            }
        });

        // Creating adapter for spinner



        return mView;
    }

    private void Init() {
        diaryText = (EditText) mView.findViewById(R.id.diary_text);
        diaryDate = (TextView) mView.findViewById(R.id.diary_date);
        submitButton = (Button) mView.findViewById(R.id.submit_button);
        Ref = FirebaseDatabase.getInstance().getReference();
    }

}
