package com.example.klemen.restavracije;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ActivityService extends AppCompatActivity {

    private static String METHOD_NAME = "";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/IService1/";
    private static String URL = "http://192.168.0.15/WebService";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        textView = (TextView) findViewById(R.id.textView3);

        final class Task extends AsyncTask<Void, Void, Void> {
            String parse;

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
                    parse = response.getProperty(0).toString();

                } catch (Exception e) {
                    Log.d("ERROR", e.getMessage());
                }
                return null;
            }

            protected void onPostExecute(Void unused) {
                try {
                    textView.setText(parse);
                } catch (Exception ex) {
                }
            }
        }
        new Task().execute();
    }
}