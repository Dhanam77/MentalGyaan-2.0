package com.example.mentalgyaan.Common;
import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingletonClass {
    private static MySingletonClass requestQueueSingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingletonClass(Context ctx){
        context = ctx;
        requestQueue = getRequestQueue();
    }

    public static synchronized  MySingletonClass getInstance(Context context){
        if (requestQueueSingleton == null){
            requestQueueSingleton = new MySingletonClass(context);
        }
        return requestQueueSingleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}