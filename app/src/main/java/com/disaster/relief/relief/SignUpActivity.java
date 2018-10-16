package com.disaster.relief.relief;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private static final String URL_FOR_REGISTRATION = "https://XXX.XXX.X.XX/android_login_example/register.php";
    private Vibrator vib;
    ProgressDialog progressDialog;
    Animation animShake;
    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputDOB,signupInputCity, signupInputState,signupInputCountry;
    private TextInputLayout signupInputLayoutName, signupInputLayoutEmail, signupInputLayoutPassword,signupInputLayoutDOB,signupInputLayoutCity, signupInputLayoutState,signupInputLayoutCountry;
    private Button btnSignUp;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupInputLayoutName = (TextInputLayout) findViewById(R.id.signup_input_layout_name);
        signupInputLayoutEmail = (TextInputLayout) findViewById(R.id.signup_input_layout_email);
        signupInputLayoutPassword = (TextInputLayout) findViewById(R.id.signup_input_layout_password);
        signupInputLayoutDOB = (TextInputLayout) findViewById(R.id.signup_input_layout_dob);
        signupInputLayoutCity = (TextInputLayout) findViewById(R.id.signup_input_layout_city);
        signupInputLayoutState = (TextInputLayout) findViewById(R.id.signup_input_layout_state);
        signupInputLayoutCountry = (TextInputLayout) findViewById(R.id.signup_input_layout_country);

        signupInputName = (EditText) findViewById(R.id.signup_input_name);
        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputPassword = (EditText) findViewById(R.id.signup_input_password);
        signupInputDOB = (EditText) findViewById(R.id.signup_input_dob);
        signupInputCity = (EditText) findViewById(R.id.signup_input_city);
        signupInputState = (EditText) findViewById(R.id.signup_input_state);
        signupInputCountry = (EditText) findViewById(R.id.signup_input_country);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private void submitForm() {

        if (!checkName()) {
            signupInputName.setAnimation(animShake);
            signupInputName.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkEmail()) {
            signupInputEmail.setAnimation(animShake);
            signupInputEmail.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkPassword()) {
            signupInputPassword.setAnimation(animShake);
            signupInputPassword.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkDOB()) {
            signupInputDOB.setAnimation(animShake);
            signupInputDOB.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkCity()) {
            signupInputCity.setAnimation(animShake);
            signupInputCity.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkState()) {
            signupInputState.setAnimation(animShake);
            signupInputState.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkCountry()) {
            signupInputCountry.setAnimation(animShake);
            signupInputCountry.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        signupInputLayoutName.setErrorEnabled(false);
        signupInputLayoutEmail.setErrorEnabled(false);
        signupInputLayoutPassword.setErrorEnabled(false);
        signupInputLayoutDOB.setErrorEnabled(false);
        signupInputLayoutCity.setErrorEnabled(false);
        signupInputLayoutState.setErrorEnabled(false);
        signupInputLayoutCountry.setErrorEnabled(false);

        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        String gender;
        if(selectedId == R.id.female_radio_btn)
            gender = "Female";
        else
            gender = "Male";

        Intent intent = new Intent(
                SignUpActivity.this,
                LoginActivity.class);
        startActivity(intent);
        /*registerUser(signupInputName.getText().toString(),
                signupInputEmail.getText().toString(),
                signupInputPassword.getText().toString(),
                gender,
                signupInputDOB.getText().toString(),
                signupInputCity.getText().toString(),
                signupInputState.getText().toString(),
                signupInputCountry.getText().toString());*/
        Toast.makeText(getApplicationContext(), "You are successfully Registered !!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkCountry() {
        if (signupInputCountry.getText().toString().trim().isEmpty()) {

            signupInputLayoutCountry.setErrorEnabled(true);
            signupInputLayoutCountry.setError(getString(R.string.err_msg_country));
            signupInputCountry.setError(getString(R.string.err_msg_required));
            return false;
        }
        signupInputLayoutCountry.setErrorEnabled(false);
        return true;
    }

    private boolean checkState() {
        if (signupInputState.getText().toString().trim().isEmpty()) {

            signupInputLayoutState.setErrorEnabled(true);
            signupInputLayoutState.setError(getString(R.string.err_msg_state));
            signupInputState.setError(getString(R.string.err_msg_required));
            return false;
        }
        signupInputLayoutState.setErrorEnabled(false);
        return true;
    }

    private boolean checkCity() {
        if (signupInputCity.getText().toString().trim().isEmpty()) {

            signupInputLayoutCity.setErrorEnabled(true);
            signupInputLayoutCity.setError(getString(R.string.err_msg_city));
            signupInputCity.setError(getString(R.string.err_msg_required));
            return false;
        }
        signupInputLayoutCity.setErrorEnabled(false);
        return true;
    }

    private boolean checkName() {
        if (signupInputName.getText().toString().trim().isEmpty()) {

            signupInputLayoutName.setErrorEnabled(true);
            signupInputLayoutName.setError(getString(R.string.err_msg_name));
            signupInputName.setError(getString(R.string.err_msg_required));
            return false;
        }
        signupInputLayoutName.setErrorEnabled(false);
        return true;
    }

    private boolean checkEmail() {
        String email = signupInputEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {

            signupInputLayoutEmail.setErrorEnabled(true);
            signupInputLayoutEmail.setError(getString(R.string.err_msg_email));
            signupInputEmail.setError(getString(R.string.err_msg_required));
            requestFocus(signupInputEmail);
            return false;
        }
        signupInputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword() {
        if (signupInputPassword.getText().toString().trim().isEmpty()) {

            signupInputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(signupInputPassword);
            return false;
        }
        signupInputLayoutPassword.setErrorEnabled(false);
        return true;
    }

    private boolean checkDOB() {

        try {
            boolean isDateValid = false;
            String[] s = signupInputDOB.getText().toString().split("/");
            int date = Integer.parseInt(s[0]);
            int month = Integer.parseInt(s[1]);

            if (date < 32 && month < 13)
                isDateValid = true;

            if (signupInputDOB.getText().toString().trim().isEmpty() && isDateValid) {

                signupInputLayoutDOB.setError(getString(R.string.err_msg_dob));
                requestFocus(signupInputDOB);
                signupInputDOB.setError(getString(R.string.err_msg_required));

                return false;
            }
        }catch(Exception ex){
            signupInputLayoutDOB.setError(getString(R.string.err_msg_dob));
            requestFocus(signupInputDOB);
            return false;
        }

        signupInputDOB.setError(null);
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private void registerUser(final String name,  final String email, final String password,
                              final String gender, final String dob, final String city, final String state, final  String country) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";

        progressDialog.setMessage("Adding you ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
               hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        Toast.makeText(getApplicationContext(), "Hi " + user +", You are successfully Added!", Toast.LENGTH_SHORT).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                SignUpActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("gender", gender);
                params.put("dob", dob);
                params.put("city",city);
                params.put("state",state);
                params.put("country",country);
                return params;
            }
        };
        // Adding request to request queue
       // AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
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
