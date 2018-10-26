package com.disaster.relief.relief;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

//import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LostActivity extends AppCompatActivity {

    public static final String Firebase_Server_URL = "https://relief-1c0c6.firebaseio.com";
    //Firebase firebase;
    EditText name,contact1,contact2,gender,age,address1,address2;
    Button submit,database;
    //DatabaseReference databaseReference;
    String name_s,contact1_s,contact2_s,gender_s,age_s,address1_s,address2_s;

    public static final String Database_PATH = "Lost_Database";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        //Firebase.setAndroidContext(LostActivity.this);

        //firebase = new Firebase(Firebase_Server_URL);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        name = (EditText) findViewById(R.id.name_edit);
        contact1 = (EditText) findViewById(R.id.contact1);
        contact2 = (EditText) findViewById(R.id.contact2);
        gender = (EditText) findViewById(R.id.gender);
        age = (EditText) findViewById(R.id.age);
        address1 = (EditText) findViewById(R.id.address1);
        address2 = (EditText) findViewById(R.id.address2);
        submit = (Button) findViewById(R.id.submit);
        database = (Button) findViewById(R.id.check);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Details detail = new Details();
                GetDataFromEditText();
                detail.setName(name_s);
                detail.setContact1(contact1_s);
                detail.setContact2(contact2_s);
                detail.setAddress1(address1_s);
                detail.setAge(age_s);
                detail.setGender(gender_s);
                detail.setAddress2(address2_s);

                String recordId = databaseReference.push().getKey();

                databaseReference.child(recordId).setValue(detail);

                name.setText("");
                contact1.setText("");
                contact2.setText("");
                address1.setText("");
                address2.setText("");
                age.setText("");
                gender.setText("");

                Toast.makeText(LostActivity.this,"Data Insertion Successful",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void GetDataFromEditText() {
        name_s = name.getText().toString().trim();
        contact1_s = contact1.getText().toString().trim();
        contact2_s = contact2.getText().toString().trim();
        gender_s = gender.getText().toString().trim();
        age_s = age.getText().toString().trim();
        address1_s = address1.getText().toString().trim();
        address2_s = address2.getText().toString().trim();
    }

}
