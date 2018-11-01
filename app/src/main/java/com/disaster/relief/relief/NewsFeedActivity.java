package com.disaster.relief.relief;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
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
    public double[] latitude;
    public double[] longitude;
    public int index;
    private RecyclerView Myrv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button mapbtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        Myrv = (RecyclerView) findViewById(R.id.rv);
        mapbtn = (Button) findViewById(R.id.mapButton);

        index =0;
        latitude=new double[1000000];
        longitude=new double[1000000];
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Extracting Data...");
        showDialog();

        for (int i=1;i<urlList.getSize();i++)
        {
            String url1 = urlList.getString(i);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url1, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                    hideDialog();

                    try {
                        // Parsing json object response
                        // response will be a json object
                        JSONArray events = response.getJSONArray("events");
                        for (int i = 0; i < events.length(); i++) {

                            JSONObject list = (JSONObject) events.get(i);
                            DisasterNews disaster = new DisasterNews();

                            String title = list.getString("title");
                            Log.d(TAG,title);

                            String description = list.getString("description");
                            Log.d(TAG,description);

                            JSONArray categories = list.getJSONArray("categories");
                            JSONObject categories_data = (JSONObject) categories.get(0);
                            String type = categories_data.getString("title");


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
                                Log.d(TAG,String.valueOf(index));
                                latitude[index]=lat;
                                longitude[index++]=longi;







                            disaster.setName(title +"\n"+ type);
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

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsFeedActivity.this,MapsActivity.class);
                intent.putExtra("lat",latitude);
                intent.putExtra("long",longitude);
                intent.putExtra("size",index-1);
                startActivity(intent);
            }
        });


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

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
