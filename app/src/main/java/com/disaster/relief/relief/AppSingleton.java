package com.disaster.relief.relief;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

class AppSingleton {
    private static AppSingleton instance;
    private static Context mctx;
    private RequestQueue queue;
    public static AppSingleton getInstance(Context applicationContext)
    {
        mctx = applicationContext;
        if (instance== null) {
            synchronized(AppSingleton.class) {
                if (instance == null)
                    instance = new AppSingleton();
            }
        }
        // Return the instance
        return instance;
    }

    private AppSingleton()
    {
        // Constructor hidden because this is a singleton
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            queue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        getRequestQueue().add(req);

    }


}
