package com.example.klemen.restavracije;

        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.LayerDrawable;
        import android.net.Uri;
        import android.support.v4.graphics.drawable.DrawableCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.text.method.LinkMovementMethod;
        import android.view.View;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.TextView;

        import com.google.zxing.integration.android.IntentIntegrator;
        import com.google.zxing.integration.android.IntentResult;

public class ActivityDodajRacun extends AppCompatActivity implements View.OnClickListener{

    public TextView t1, t2, t3, t4, t5, t6;
    public ImageView imv, imv1,imv2;
    Restavracija r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_racun);

        imv1 = (ImageView) findViewById(R.id.imv1);
        imv2 = (ImageView) findViewById(R.id.imv2);
        t1 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView4);
        t4 = (TextView) findViewById(R.id.textView5);
        t5 = (TextView) findViewById(R.id.textView6);
        t6 = (TextView) findViewById(R.id.textView7);
        TextView t2 = (TextView) findViewById(R.id.textView20);
        t2.setClickable(true);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        Button scan = (Button)findViewById(R.id.button3);
        scan.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button3) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getFormatName();
            t6.setText("CONTENT: " + scanContent);
        }
    }
}
