package com.example.klemen.restavracije;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityRestavracije extends AppCompatActivity {

    public TextView t1, t2, t3, t4, t5, t6;
    public ImageView imv, imv1,imv2;
    public RatingBar rb;
    Restavracija r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_restavracije);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imv = (ImageView) findViewById(R.id.icon);
        imv1 = (ImageView) findViewById(R.id.imv1);
        imv2 = (ImageView) findViewById(R.id.imv2);
        t1 = (TextView) findViewById(R.id.textView2);
        t2 = (TextView) findViewById(R.id.textView3);
        t3 = (TextView) findViewById(R.id.textView4);
        t4 = (TextView) findViewById(R.id.textView5);
        t5 = (TextView) findViewById(R.id.textView6);
        rb = (RatingBar) findViewById(R.id.rating);
        t6 = (TextView) findViewById(R.id.texRat);
        Intent intent = getIntent();
        int message = intent.getIntExtra("intVariableName", 0);


        MyClass res = MyClass.getScenarij();
        r = res.getRestavracija(message);
        setTitle(r.getIme());
        t1.setText(r.getIme());
        t2.setText(r.getNaslov().getMesto());
        t3.setText(r.getNaslov().getUlica() + ", " + r.getNaslov().getHisna_stev());
        t5.setText(r.getNaslov().getPosta() + ", " + r.getNaslov().getPostna_stev());
        t6.setText(r.getOcena_restavracije().toString() + "/5");
        rb.setRating(r.getOcena_restavracije());
        Drawable p = rb.getProgressDrawable();
        DrawableCompat.setTint(p, Color.YELLOW);
        if(r.getGlukoza() && r.getLaktoza())
        {
            imv2.setImageResource(R.drawable.glutenfree);
            imv1.setImageResource(R.drawable.lactofree);
        }
        else if(r.getLaktoza())
            imv1.setImageResource(R.drawable.lactofree);
        else if(r.getGlukoza())
            imv1.setImageResource(R.drawable.glutenfree);
        else
            t4.append("Ni kategorij");
    }

    public void sendMessage(View view) {
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q="+r.getNaslov().toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
