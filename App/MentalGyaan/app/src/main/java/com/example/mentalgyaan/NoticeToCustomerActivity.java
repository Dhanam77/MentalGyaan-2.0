package com.example.mentalgyaan;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NoticeToCustomerActivity extends AppCompatActivity {

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA6UnD1cw:APA91bH1POqT9zjustqr0HwWCt1p1rEtDZc_1uJHS9FZxmuUb902Cv3I0TjZZwsgmFe177u0spH5IYQy-JDchzyLdeQqmEkvqeDMbJ0bIHo_SpJR1dnXvg3q4zFmHCYDx5ZJHXFKn1jD";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    private Toolbar mToolbar;
    private DatabaseReference Ref;
    private String resultResponse;
    private RequestQueue requestQueue;
    ;

    //firebase objects

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC, message;


    EditText edtTitle;
    EditText edtMessage;
    Button edtImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_to_customer);

        FirebaseMessaging.getInstance().subscribeToTopic("offers");
        edtTitle = findViewById(R.id.noti_name);
        edtMessage = findViewById(R.id.noti_text);
        Button btnSend = findViewById(R.id.send_noti);

        requestQueue = Volley.newRequestQueue(this); // 'this' is the Context

        Ref = FirebaseDatabase.getInstance().getReference();


        SetupToolbar();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOPIC = "/topics/offers"; //topic must match with what the receiver subscribed to
                NOTIFICATION_TITLE = edtTitle.getText().toString();
                NOTIFICATION_MESSAGE = edtMessage.getText().toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDateFormat.format(new Date());

                Toast.makeText(NoticeToCustomerActivity.this, "Notification Sent!", Toast.LENGTH_SHORT).show();


                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", NOTIFICATION_TITLE);
                    notifcationBody.put("text", NOTIFICATION_MESSAGE);

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage());
                }
                sendNotification(notification);
            }
        });
    }

    private void SetupToolbar() {
        mToolbar = findViewById(R.id.noticebar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Send Notification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void sendNotification(JSONObject notification) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "ONRESPONSEEEEEEEEEEEEEE: " + response.toString());
                        edtTitle.setText("");
                        edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NoticeToCustomerActivity.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingletonClass.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}