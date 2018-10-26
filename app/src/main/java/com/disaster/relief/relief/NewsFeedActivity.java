package com.disaster.relief.relief;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Set;

public class NewsFeedActivity extends AppCompatActivity {

    private static final String TAG = "Json output";
    public TextView mTextView;
    private urlBook urlList = new urlBook();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        mTextView = (TextView) findViewById(R.id.txtResponse);
        for (int i=0;i<urlList.getSize();i++)
        {
            String url1 = urlList.getString(i);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url1, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());

                    try {
                        // Parsing json object response
                        // response will be a json object
                        String titl = response.getString("title");
                        //Toast.makeText(getApplicationContext(), titl, Toast.LENGTH_LONG).show();

                        JSONArray events = response.getJSONArray("events");
                        for (int i = 0; i < events.length(); i++) {

                            JSONObject list = (JSONObject) events.get(i);
                            String id = list.getString("id");
                            String title = list.getString("title");
                            // Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();

                            String description = list.getString("description");
                            //Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();

                            JSONArray geometries = list.getJSONArray("geometries");

                            JSONObject geometries_data = (JSONObject) geometries.get(0);

                            String date = geometries_data.getString("date");
                            //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();

                            //JSONArray coordinates = geometries_data.getJSONArray("coordinates");
                            //int first =  (int) coordinates.getInt(0);
                            //int second = (int) coordinates.getInt(1);

                            //JSONArray cd = (JSONArray) key.next();
                            // Toast.makeText(getApplicationContext(), second, Toast.LENGTH_LONG).show();
                            /*JSONArray coordinates2 = coordinates.getJSONArray("");
                            JSONObject coordinates3 = (JSONObject) coordinates2.get(0);
                            int latitude,longitude;
                            latitude = coordinates3.;
                            longitude=coordinates3.getInt(1);
                            Toast.makeText(getApplicationContext(), latitude, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), longitude, Toast.LENGTH_LONG).show();*/
                            String jsonResponse;

                            jsonResponse = "\n\n";
                            jsonResponse += title + "\n";
                            jsonResponse += description + "\n";
                            jsonResponse += "Date: " + date + "\n";
                            //jsonResponse += "Latitude: " + latitude + "\n";
                            //jsonResponse += "Longitude: " + longitude + "\n";
                            //jsonResponse += " " +  + "\n\n";
                            //Toast.makeText(getApplicationContext(), jsonResponse, Toast.LENGTH_LONG).show();
                            mTextView.setText(jsonResponse+mTextView.getText());
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                    // hide the progress dialog

                }
            });
            AppSingleton.getInstance(this).addToRequestQueue(jsonObjReq);

        }


    }

}
