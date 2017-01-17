package com.example.klemen.restavracije;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ListRestavracij extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    ApplicationMy app;
    String message;
    String kategorija;
    String city;

    private static String METHOD_NAME = "";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/IService1/";
    private static String URL = "http://192.168.0.15/WebService";
    MyClass restavracije;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_list_restavracij);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        LocationManager locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria criteria=new Criteria();
        Location location=locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,true));
        Geocoder gcd=new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses=gcd.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if(addresses.size()>0)
            {
                city = addresses.get(0).getLocality().toString();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }*/

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if(message==null)
            message = city;
        kategorija = intent.getStringExtra(MainActivity.MESSAGE_KATEGORIJA);
        if(kategorija==null)
            kategorija = "vse";

        restavracije = new MyClass();

        final class Task extends AsyncTask<Void, Void, Void> {
            String parse[];

            protected void onPreExecute() {

            }

            protected Void doInBackground(Void... unused) {

                try {
                    METHOD_NAME = "GetByMesto";
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    request.addProperty("m", "Maribor");
                    envelope.setOutputSoapObject(request);

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    androidHttpTransport.call(SOAP_ACTION + "GetByMesto", envelope);


                    SoapObject response = (SoapObject) envelope.bodyIn;
                    String vmesna = response.getProperty(0).toString();
                    parse = vmesna.split(":");

                } catch (Exception e) {
                    Log.d("ERROR", e.getMessage());
                }
                return null;
            }

            protected void onPostExecute(Void unused) {
                try {
                    for(int i=0;i<parse.length;i++)
                    {
                        String[] another = parse[i].split(",");
                        boolean l, g;
                        if (another[8].equals("True"))
                            l = true;
                        else
                            l = false;
                        if (another[9].equals("True"))
                            g = true;
                        else
                            g = false;
                        restavracije.dodaj(new Restavracija(Integer.parseInt(another[0]), another[1], new Naslov(another[2], another[3], Integer.parseInt(another[4]), another[5], Integer.parseInt(another[6])), Integer.parseInt(another[7]), l, g, another[10], another[11], 0));
                        app = (ApplicationMy) getApplication();
                        mLayoutManager = new LinearLayoutManager(ListRestavracij.this);
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mAdapter = new AdapterRestavracij(restavracije, ListRestavracij.this);
                        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                } catch (Exception ex) {
                }
            }
        }
        new Task().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.pif:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.dodaj:
                Intent intent1 = new Intent(this, ActivityDodaj.class);
                startActivity(intent1);
                break;
            case R.id.obiskane:
                Intent intent2 = new Intent(this, ActivityObskane.class);
                startActivity(intent2);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
