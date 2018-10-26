package com.disaster.relief.relief;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeeLostActivity extends AppCompatActivity {

    List <Details> myDataset;
    private static final String TAG = SeeLostActivity.class.getSimpleName();
    private RecyclerView seeRV;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_lost);

        seeRV = (RecyclerView) findViewById(R.id.see);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        myDataset = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myDataset.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Details detail = snapshot.getValue(Details.class);
                    Log.d(TAG,String.valueOf(detail.name));
                    //Toast.makeText(getApplicationContext(),String.valueOf(detail.name),Toast.LENGTH_LONG).show();
                    myDataset.add(detail);
                }
                //Toast.makeText(getApplicationContext(),String.valueOf(myDataset.size()),Toast.LENGTH_LONG).show();
                setRvadapter(myDataset);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }

        });



    }

    public void setRvadapter(List<Details> lst) {

        seeRV.setLayoutManager(new LinearLayoutManager(this));
        seeRvAdapter myAdapter = new seeRvAdapter(this,lst);
        seeRV.setAdapter(myAdapter);
    }
}
