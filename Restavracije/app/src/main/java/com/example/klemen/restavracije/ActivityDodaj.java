package com.example.klemen.restavracije;

import android.os.Bundle;
import android.print.PrintDocumentAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDodaj extends AppCompatActivity implements OnClickListener {
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    ApplicationMy all;
    DodajObiskane aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_dodaj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        all=(ApplicationMy)getApplication();

        scanBtn = (Button)findViewById(R.id.skeniraj);
        formatTxt = (TextView)findViewById(R.id.textView4);
        contentTxt = (TextView)findViewById(R.id.textView6);
        scanBtn.setOnClickListener(this);

    }
    public void onClick(View v) {
        if (v.getId() == R.id.skeniraj) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            if(scanContent.equals("9780123456786"))
            {
                formatTxt.setText("Restavracija Mak");
                contentTxt.setText("Osojnikova ulica 20, Maribor");
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void addButton(View v)
    {
        all.dodaj(new Restavracija(11, "Restavracija Mak",new Naslov("Maribor", "Osojnikova ulica", 20, "Maribor", 2000), 4,true,true));
        all.save();
        finish();
    }

}
