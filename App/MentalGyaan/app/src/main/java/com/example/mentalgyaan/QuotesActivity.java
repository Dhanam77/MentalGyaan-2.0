package com.example.mentalgyaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
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

public class QuotesActivity extends AppCompatActivity {


    private DatabaseReference Ref;
    private TextView quoteText;
    private String image, name;
    private ImageView quoteImage;

    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        Init();


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateData = formatter.format(date);


        Ref.child("Quotes").child(dateData).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    image = dataSnapshot.child("Image").getValue().toString();
                    name = dataSnapshot.child("Text").getValue().toString();

                    Glide.with(QuotesActivity.this)
                            .load(image)
                            .into(quoteImage);

                    quoteText.setText(name);
                    loadingBar.setVisibility(View.INVISIBLE);

                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void Init() {
        Ref = FirebaseDatabase.getInstance().getReference();
        quoteImage = (ImageView) findViewById(R.id.quote_image);
        quoteText = (TextView) findViewById(R.id.quote_text);
        loadingBar = (ProgressBar) findViewById(R.id.load_quotes);


    }
}
