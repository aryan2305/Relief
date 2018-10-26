package com.disaster.relief.relief;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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

import java.util.ArrayList;
import java.util.List;

public class NewsFeedActivity extends AppCompatActivity {

    private static final String TAG = "Json output";
    public TextView mTextView;
    private urlBook urlList = new urlBook();
    private List<DisasterNews> listDisaster = new ArrayList<>();
    private RecyclerView Myrv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        Myrv = (RecyclerView) findViewById(R.id.rv);
        //layoutManager = new LinearLayoutManager(this);
       // Myrv.setLayoutManager(layoutManager);


        //mTextView = (TextView) findViewById(R.id.txtResponse);
        for (int i=1;i<urlList.getSize();i++)
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
                            DisasterNews disaster = new DisasterNews();

                            String id = list.getString("id");
                            String title = list.getString("title");
                            Log.d(TAG,title);

                            String description = list.getString("description");
                            Log.d(TAG,description);

                            JSONArray geometries = list.getJSONArray("geometries");
                            JSONObject geometries_data = (JSONObject) geometries.get(0);
                            String date = geometries_data.getString("date");
                            Log.d(TAG,date);
                            //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();

                            JSONArray coordinates = geometries_data.getJSONArray("coordinates");
                            double lat,longi;
                            lat = (double) coordinates.get(0);
                            longi = (double) coordinates.get(1);
                            Log.d(TAG,String.valueOf(lat));
                            Log.d(TAG,String.valueOf(longi));

                            /*String jsonResponse;

                            jsonResponse = "\n\n";
                            jsonResponse += title + "\n";
                            jsonResponse += description + "\n";
                            jsonResponse += "Date: " + date + "\n";*/
                            //jsonResponse += "Latitude: " + latitude + "\n";
                            //jsonResponse += "Longitude: " + longitude + "\n";
                            //jsonResponse += " " +  + "\n\n";
                            //Toast.makeText(getApplicationContext(), jsonResponse, Toast.LENGTH_LONG).show();
                            //mTextView.setText(jsonResponse+mTextView.getText());
                            disaster.setName(title);
                            disaster.setDescription(description);
                            disaster.setDate(date);
                            disaster.setLatitude(lat);
                            disaster.setLongitude(longi);

                            listDisaster.add(disaster);
                            Log.d(TAG,listDisaster.toString());
                            //Toast.makeText(NewsFeedActivity.this,"Size of Liste "+String.valueOf(listDisaster.size()),Toast.LENGTH_SHORT).show();
                             }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                    Toast.makeText(NewsFeedActivity.this,"Size of Liste "+String.valueOf(listDisaster.size()),Toast.LENGTH_SHORT).show();
                    Toast.makeText(NewsFeedActivity.this,listDisaster.get(0).toString(),Toast.LENGTH_SHORT).show();
                    setRvadapter(listDisaster);

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

    public void setRvadapter(List<DisasterNews> lst) {
        RvAdapter myAdapter = new RvAdapter(this,lst) ;
        Myrv.setLayoutManager(new LinearLayoutManager(this));
        Myrv.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
