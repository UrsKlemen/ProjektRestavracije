package com.example.klemen.restavracije;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Text;

public class ActivityRestavracije extends AppCompatActivity{

    public TextView t1, t3, t4, t5;
    public ImageView imv, imv1,imv2;
    public RatingBar rb;
    Restavracija r;
    private static String METHOD_NAME = "";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/IService1/";
    private static String URL = "http://192.168.0.15/WebService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_restavracije);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imv = (ImageView) findViewById(R.id.imageView2);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/pages/Vivaldi/238275042895811?fref=ts");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        imv1 = (ImageView) findViewById(R.id.imv1);
        imv2 = (ImageView) findViewById(R.id.imv2);
        t1 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView4);
        t4 = (TextView) findViewById(R.id.textView5);
        t5 = (TextView) findViewById(R.id.textView6);
        rb = (RatingBar) findViewById(R.id.rating);
        final TextView t2 = (TextView) findViewById(R.id.textView20);
        t2.setClickable(true);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        Intent intent = getIntent();
        final int message = intent.getIntExtra("intVariableName", 0);
        final WebView webview = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());

        final MyClass res = new MyClass();

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
                        boolean l,g;
                        if(another[8].equals("True"))
                            l = true;
                        else
                            l = false;
                        if(another[9].equals("True"))
                            g = true;
                        else
                            g = false;
                        res.dodaj(new Restavracija(Integer.parseInt(another[0]), another[1], new Naslov(another[2], another[3], Integer.parseInt(another[4]) ,another[5] , Integer.parseInt(another[6])), Integer.parseInt(another[7]), l, g, another[10], another[11], 0));
                    }
                    r = res.getRestavracija(message);
                    setTitle(r.getIme());
                    t1.setText(r.getIme());
                    t5.setText(r.getTelefonska());
                    String text = "<a href='http://"+ r.getSpletna() +"'>" + r.getIme() + "</a>";
                    t2.setText(Html.fromHtml(text));
                    webview.loadUrl("https://www.google.si/maps/place/"+ r.getNaslov().getUlica() + " " + r.getNaslov().getHisna_stev() + ", " + r.getNaslov().getMesto());
                    t3.setText(r.getNaslov().getUlica() + ", " + r.getNaslov().getHisna_stev());
                    t4.setText(r.getNaslov().getPostna_stev() + " " + r.getNaslov().getPosta());
                    rb.setRating(r.getOcena_restavracije());
                    LayerDrawable stars = (LayerDrawable) rb.getProgressDrawable();
                    stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                    if(r.getGlukoza() && r.getLaktoza())
                    {
                        imv2.setImageResource(R.drawable.glutenfree);
                        imv1.setImageResource(R.drawable.lactofree);
                    }
                    else if(r.getLaktoza())
                        imv1.setImageResource(R.drawable.lactofree);
                    else if(r.getGlukoza())
                        imv1.setImageResource(R.drawable.glutenfree);
                } catch (Exception ex) {
                }
            }
        }
        new Task().execute();
    }
}
