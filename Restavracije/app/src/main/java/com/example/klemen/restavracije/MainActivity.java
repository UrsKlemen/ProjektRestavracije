package com.example.klemen.restavracije;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    public final static String MESSAGE_KATEGORIJA = "com.mycompany.myfirstapp.KATEGORIJA";
    EditText locText;
    public String kategorija = "vse";
    String cityname;
    ApplicationMy all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        all=(ApplicationMy)getApplication();
        locText = (EditText) findViewById(R.id.edText);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
        {
            return;
        }

        LocationManager locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria criteria=new Criteria();
        locationManager.getBestProvider(criteria, true);
        Location location=locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,true));
        Geocoder gcd=new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses=gcd.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if(addresses.size()>0)
            {
                cityname = addresses.get(0).getLocality().toString();
                locText.setText(cityname);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ListRestavracij.class);
        EditText editText = (EditText) findViewById(R.id.edText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(MESSAGE_KATEGORIJA,kategorija);
        startActivity(intent);
    }

    public void openLocation(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    locText.setEnabled(false);
                    break;
            case R.id.radioButton2:
                if (checked)
                    locText.setEnabled(true);
                    break;
        }
    }

    public void radioKategorij(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton3:
                if (checked)
                    kategorija = "vse";
                break;
            case R.id.radioButton4:
                if (checked)
                    kategorija = "lak";
                break;
            case R.id.radioButton5:
                if (checked)
                    kategorija = "glu";
                break;
        }
    }
}
