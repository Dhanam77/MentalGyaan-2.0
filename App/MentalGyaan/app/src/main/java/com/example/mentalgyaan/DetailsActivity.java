package com.example.mentalgyaan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;
    private RadioButton radiobutton;
    private Button buttonAdd;
    private EditText Age;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    private String Fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        radioGroup1=(RadioGroup)findViewById(R.id.gender);
        radioGroup2=(RadioGroup)findViewById(R.id.working);
        radioGroup3=(RadioGroup)findViewById(R.id.treatment);
        radioGroup4=(RadioGroup)findViewById(R.id.work);


        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        Age = (EditText)findViewById(R.id.editAge);

        radioGroup1.setOnCheckedChangeListener(this);
        radioGroup2.setOnCheckedChangeListener(this);
        radioGroup3.setOnCheckedChangeListener(this);
        radioGroup4.setOnCheckedChangeListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==buttonAdd)
                {
                    AddD();
                }
            }
        });


    }
    private void AddD() {
        Fa = Age.getText().toString().trim();
        String A = Fa;
        databaseReference.child("Details").child("Age").setValue(A);
        startActivity(new Intent(this, QuizActivity.class));
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.male:
                databaseReference.child("Details").child("Gender").setValue("Male");
                break;
            case R.id.female:
                databaseReference.child("Details").child("Gender").setValue("Female");
                break;
            case R.id.yes:
                databaseReference.child("Details").child("Working").setValue("Yes");
                break;
            case R.id.no:
                databaseReference.child("Details").child("Working").setValue("No");
                break;
            case R.id.yes1:
                databaseReference.child("Details").child("Treatment").setValue("Yes");
                break;
            case R.id.no1:
                databaseReference.child("Details").child("Treatment").setValue("No");
                break;
            case R.id.sometimes:
                databaseReference.child("Details").child("Work").setValue("sometimes");
                break;
            case R.id.never:
                databaseReference.child("Details").child("Work").setValue("never");
                break;
            case R.id.often:
                databaseReference.child("Details").child("Work").setValue("often");
                break;
            case R.id.rarely:
                databaseReference.child("Details").child("Work").setValue("rarely");
                break;


        }

    }
}
