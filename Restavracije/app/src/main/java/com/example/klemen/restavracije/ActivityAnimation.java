package com.example.klemen.restavracije;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAnimation extends AppCompatActivity implements Animation.AnimationListener {

    Animation animacija;
    Animation animacija2;
    Animation animacija3;
    Animation animacija4;
    Animation animacija5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        final ImageView kroznik = (ImageView)findViewById(R.id.imageKroznik);
        final ImageView vilica = (ImageView)findViewById(R.id.imageVilica);
        final ImageView noz = (ImageView)findViewById(R.id.imageNoz);
        final ImageView steak = (ImageView)findViewById(R.id.imageView3);
        final ImageView mapa = (ImageView)findViewById(R.id.imageView2);
        final TextView napis1 = (TextView)findViewById(R.id.textView);
        final TextView napis2 = (TextView)findViewById(R.id.textView2);
        napis1.setVisibility(View.INVISIBLE);
        napis2.setVisibility(View.INVISIBLE);
        kroznik.setVisibility(View.INVISIBLE);
        vilica.setVisibility(View.INVISIBLE);
        noz.setVisibility(View.INVISIBLE);
        steak.setVisibility(View.INVISIBLE);
        kroznik.bringToFront();
        steak.bringToFront();
        noz.bringToFront();
        vilica.bringToFront();

        animacija = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_kroznik);
        animacija.setAnimationListener(this);
        animacija2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_text);
        animacija2.setAnimationListener(this);
        animacija3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_food);
        animacija3.setAnimationListener(this);
        animacija4 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_move);
        animacija4.setAnimationListener(this);
        animacija5 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_move1);
        animacija5.setAnimationListener(this);

        kroznik.setVisibility(View.VISIBLE);
        kroznik.startAnimation(animacija);
        vilica.setVisibility(View.VISIBLE);
        vilica.startAnimation(animacija);
        noz.setVisibility(View.VISIBLE);
        noz.startAnimation(animacija);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                steak.setVisibility(View.VISIBLE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        steak.setAnimation(animacija3);
                        steak.setVisibility(View.INVISIBLE);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                noz.setAnimation(animacija4);
                                vilica.setAnimation(animacija5);
                                mapa.setVisibility(View.INVISIBLE);
                                napis1.setVisibility(View.VISIBLE);
                                napis1.startAnimation(animacija2);
                                napis2.setVisibility(View.VISIBLE);
                                napis2.startAnimation(animacija2);
                            }
                        }, 4000);
                    }
                }, 500);

            }
        }, 1500);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == animacija5) {
            Toast.makeText(getApplicationContext(), "Naj vam tekne! :)",
            Toast.LENGTH_SHORT).show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ActivityAnimation.this, ListRestavracij.class);
                        startActivity(intent);
                    }
                }, 500);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
