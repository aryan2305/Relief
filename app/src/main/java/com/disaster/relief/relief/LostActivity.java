package com.disaster.relief.relief;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        EditText name = (EditText) findViewById(R.id.name_edit);
        EditText contact1 = (EditText) findViewById(R.id.contact1);
        EditText contact2 = (EditText) findViewById(R.id.contact2);
        EditText gender = (EditText) findViewById(R.id.gender);
        EditText age = (EditText) findViewById(R.id.age);
        EditText address1 = (EditText) findViewById(R.id.address1);
        EditText address2 = (EditText) findViewById(R.id.address2);
        Button submit = (Button) findViewById(R.id.submit);
        Button database = (Button) findViewById(R.id.check);



    }
}
