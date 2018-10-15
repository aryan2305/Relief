package com.disaster.relief.relief;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText loginId;
    private EditText password;
    private Button loginButton;
    private Button Signupbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginId = findViewById(R.id.LoginIdEditText);
        password = findViewById(R.id.PasswordEditText);
        loginButton = findViewById(R.id.Loginbutton);
        Signupbutton = findViewById(R.id.SignUpButton);

        String email = loginId.getText().toString();
        String pass = password.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        Signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        loginButton.setEnabled(false);
    }

    private void login() {
        Log.d(TAG,"Login");

        if(!validate()){
            onLoginFailed();
            return;
        }
    }

    private void onLoginFailed() {

    }

    private boolean validate() {
        return false;
    }
}
