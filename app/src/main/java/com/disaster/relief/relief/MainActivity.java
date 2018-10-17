package com.example.sparsh.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import static android.os.Build.VERSION;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button SOS;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SOS = (Button)findViewById(R.id.sos);

        SOS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                PopupMenu emergency_popup = new PopupMenu(MainActivity.this, SOS);
                emergency_popup.getMenuInflater().inflate(R.menu.popup_emergency,emergency_popup.getMenu());
                emergency_popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String num = (String) item.getTitle();
                        if(num.equals("HELPLINE"))
                            callPhoneNumber("45791332123");
                        else if(num.equals("FIRE"))
                            callPhoneNumber("7896657602");
                        else if(num.equals("HOSPITAL"))
                            callPhoneNumber("123456798");
                        else if(num.equals("POLICE"))
                            callPhoneNumber(("9406964210"));

                        Toast.makeText(MainActivity.this,"You Clicked : " + num, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                emergency_popup.show();
            }
        });

    }



    public void callPhoneNumber(String num)
    {
        try
        {
            if(VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

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
