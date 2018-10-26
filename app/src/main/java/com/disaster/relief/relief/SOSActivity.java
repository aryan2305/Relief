package com.disaster.relief.relief;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.disaster.relief.relief.MainActivity.num;

public class SOSActivity extends AppCompatActivity {
    private static final String TAG = "SOS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        Button fire_button,hospital_button,police_button,helpline_button,sos_message;
        fire_button = findViewById(R.id.fire);
        hospital_button = findViewById(R.id.hospital);
        police_button = findViewById(R.id.police);
        helpline_button = findViewById(R.id.helpline);
        sos_message = findViewById(R.id.distress);


        fire_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                callPhoneNumber("123456789");
            }
        });

        hospital_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                callPhoneNumber("123456789");
            }
        });

        police_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                callPhoneNumber("123456789");
            }
        });

        helpline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                callPhoneNumber("123456789");
            }
        });
    }

    public void callPhoneNumber(String num)
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(SOSActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + num));
                startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + num));
                startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber(num);
            }
            else
            {
                Log.e(TAG, "Permission not Granted");
            }
        }
    }
}
