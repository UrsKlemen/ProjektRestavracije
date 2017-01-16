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
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

import org.w3c.dom.Text;

public class ActivityRestavracije extends AppCompatActivity{

    public TextView t1, t2, t3, t4, t5;
    public ImageView imv, imv1,imv2;
    public RatingBar rb;
    Restavracija r;

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
        rb = (RatingBar) findViewById(R.id.rating);
        TextView t2 = (TextView) findViewById(R.id.textView20);
        t2.setClickable(true);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        Intent intent = getIntent();
        int message = intent.getIntExtra("intVariableName", 0);
        WebView webview = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());

        MyClass res = MyClass.getScenarij();
        r = res.getRestavracija(message);
        setTitle(r.getIme());
        t1.setText(r.getIme());
        String text = "<a href='http://www.mojagostilna.com'>" + r.getIme() + "</a>";
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
    }
}
