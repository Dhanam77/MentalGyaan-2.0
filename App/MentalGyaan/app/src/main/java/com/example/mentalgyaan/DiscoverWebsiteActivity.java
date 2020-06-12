package com.example.mentalgyaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DiscoverWebsiteActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebView;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String URL, name;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_website);


        Initialize();

        LoadURL();


        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript


        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }

    private void LoadURL() {
        Ref.child("DiscoverItems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.child("topicName").getValue().toString().equals(name)) ;
                    {
                        if (dataSnapshot1.hasChild("URL")) {
                            URL = dataSnapshot1.child("URL").getValue().toString();
                            break;
                        }

                        break;

                    }



                }
                mWebView.loadUrl(URL);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void Initialize() {

        name = getIntent().getStringExtra("name");
        SetupTOolbar();


        mWebView = (WebView) findViewById(R.id.company_webview);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        Ref = FirebaseDatabase.getInstance().getReference();


    }

    private void SetupTOolbar() {
        mToolbar = (Toolbar) findViewById(R.id.discover_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
