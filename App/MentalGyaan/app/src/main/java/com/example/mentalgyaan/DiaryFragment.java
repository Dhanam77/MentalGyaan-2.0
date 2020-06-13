package com.example.mentalgyaan;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mentalgyaan.API.ApiCLient;
import com.example.mentalgyaan.API.ApiInterface;
import com.example.mentalgyaan.Model.Sentiment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {

    private View mView;
    private DatabaseReference Ref;
    private EditText diaryText;
    private TextView diaryDate;
    private Button submitButton;
    private ApiInterface apiInterface;
    private String randomID;
    Random random = new Random();
    private final String BASE_URL= "http://10.0.2.2:5000/";
    final private String contentType = "application/json";
    RequestQueue requestQueue;

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

                    apiInterface= ApiCLient.getApiClient().create(ApiInterface.class);
                    Call<Sentiment> call = apiInterface.getSentiment(new Sentiment(text));
                    call.enqueue(new Callback<Sentiment>() {
                        @Override
                        public void onResponse(@NonNull Call<Sentiment> call, @NonNull Response<Sentiment> response) {
                            Sentiment sentiment = response.body();
                            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                            alertDialog.setTitle("Your Sentiment");
                            if(sentiment.getSentiment().equals("sad vibes")){
                                alertDialog.setMessage("You are having sad vibes! Please head over to Moodify tab to improve your mood or visit the Personalized Therapies Section");

                            }
                            else{
                                alertDialog.setMessage("You are having good vibes! Good to see that bruh! Rate this app NOWWW");

                            }
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }

                        @Override
                        public void onFailure(Call<Sentiment> call, Throwable t) {
                            Toast.makeText(getActivity(), "Opps " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // Creating adapter for spinner



        return mView;
    }
    // Post Request For JSONObject
    private void Init() {
        diaryText = (EditText) mView.findViewById(R.id.diary_text);
        diaryDate = (TextView) mView.findViewById(R.id.diary_date);
        submitButton = (Button) mView.findViewById(R.id.submit_button);
        Ref = FirebaseDatabase.getInstance().getReference();
    }

}
