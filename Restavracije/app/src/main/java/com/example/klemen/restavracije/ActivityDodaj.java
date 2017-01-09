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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class ActivityDodaj extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_dodaj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner spinnerOcena = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_num, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOcena.setAdapter(adapter);

        final Spinner spinnerHrana = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.array_hrana, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHrana.setAdapter(adapter1);

        final Spinner spinnerPosebni = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.array_pos, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosebni.setAdapter(adapter2);

        final Spinner spinnerCena = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.array_cena, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCena.setAdapter(adapter3);

        Button gumb = (Button)findViewById(R.id.button2);
        final TextView izpis = (TextView)findViewById(R.id.textView13);
        final EditText lokacija = (EditText)findViewById(R.id.editText3);

        final String str = "%Klemen Ursic\n" +
                "\n" +
                "@RELATION restavrcije\n" +
                "\n" +
                "@ATTRIBUTE ocena {1,2,3,4,5}\n" +
                "@ATTRIBUTE lokacija {Maribor, Celje, NovoMesto, Ljubljana, Koper, Portoroz}\n" +
                "@ATTRIBUTE hrana {zar, testenine, morskaHrana, pizze}\n" +
                "@ATTRIBUTE posebniIzdelki {brez, brezLaktozni, brezGlutenski, brezLaktozni/Glutenski}\n" +
                "@ATTRIBUTE cenovniRang {poceni, srednje, drago}\n" +
                "@ATTRIBUTE razred {slabo, dobro, odlicno}\n" +
                "\n" +
                "@DATA\n" +
                "4, Celje, zar, brez, srednje, dobro\n" +
                "2, Celje, testenine, brezLaktozni, srednje, slabo\n" +
                "5, Maribor, pizze, brez, drago, odlicno\n" +
                "3, Maribor, testenine, brezLaktozni/Glutenski, poceni, dobro\n" +
                "4, NovoMesto, morskaHrana, brez, srednje, dobro\n" +
                "5, Ljubljana, zar, brezLaktozni, srednje, odlicno\n" +
                "3, Koper, morskaHrana, brezLaktozni/Glutenski, drago, dobro\n" +
                "5, Portoroz, morskaHrana, brez, srednje, odlicno\n" +
                "1, Celje, pizze, brezGlutenski, poceni, slabo\n" +
                "3, Ljubljana, zar, brezLaktozni, srednje, dobro\n" +
                "4, Koper, pizze, brez, poceni, odlicno\n" +
                "2, Maribor, testenine, brez, srednje, slabo\n" +
                "3, Portoroz, pizze, brezLaktozni, drago, slabo\n" +
                "5, Ljubljana, morskaHrana, brezLaktozni/Glutenski, srednje, odlicno\n" +
                "4, Koper, pizze, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "2, Portoroz, zar, brezGlutenski, srednje, slabo\n" +
                "3, Celje, pizze, brezLaktozni, drago, dobro\n" +
                "5, Maribor, morskaHrana, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "4, NovoMesto, zar, brez, srednje, odlicno\n" +
                "2, NovoMesto, testenine, brezLaktozni, drago, slabo\n" +
                "3, Maribor, pizze, brez, srednje, dobro\n" +
                "4, Celje, zar, brez, srednje, dobro\n" +
                "2, Celje, testenine, brezLaktozni, srednje, slabo\n" +
                "5, Maribor, pizze, brez, drago, odlicno\n" +
                "3, Maribor, testenine, brezLaktozni/Glutenski, poceni, dobro\n" +
                "4, NovoMesto, morskaHrana, brez, srednje, dobro\n" +
                "5, Ljubljana, zar, brezLaktozni, srednje, odlicno\n" +
                "3, Koper, morskaHrana, brezLaktozni/Glutenski, drago, dobro\n" +
                "5, Portoroz, morskaHrana, brez, srednje, odlicno\n" +
                "1, Celje, pizze, brezGlutenski, poceni, slabo\n" +
                "3, Ljubljana, zar, brezLaktozni, srednje, dobro\n" +
                "4, Koper, pizze, brez, poceni, odlicno\n" +
                "2, Maribor, testenine, brez, srednje, slabo\n" +
                "3, Portoroz, pizze, brezLaktozni, drago, slabo\n" +
                "5, Ljubljana, morskaHrana, brezLaktozni/Glutenski, srednje, odlicno\n" +
                "4, Koper, pizze, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "2, Portoroz, zar, brezGlutenski, srednje, slabo\n" +
                "3, Celje, pizze, brezLaktozni, drago, dobro\n" +
                "5, Maribor, morskaHrana, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "4, NovoMesto, zar, brez, srednje, odlicno\n" +
                "2, NovoMesto, testenine, brezLaktozni, drago, slabo\n" +
                "3, Maribor, pizze, brez, srednje, dobro\n" +
                "4, Celje, zar, brez, srednje, dobro\n" +
                "2, Celje, testenine, brezLaktozni, srednje, slabo\n" +
                "5, Maribor, pizze, brez, drago, odlicno\n" +
                "3, Maribor, testenine, brezLaktozni/Glutenski, poceni, dobro\n" +
                "4, NovoMesto, morskaHrana, brez, srednje, dobro\n" +
                "5, Ljubljana, zar, brezLaktozni, srednje, odlicno\n" +
                "3, Koper, morskaHrana, brezLaktozni/Glutenski, drago, dobro\n" +
                "3, Ljubljana, zar, brezLaktozni, srednje, dobro\n" +
                "4, Koper, pizze, brez, poceni, odlicno\n" +
                "2, Maribor, testenine, brez, srednje, slabo\n" +
                "3, Portoroz, pizze, brezLaktozni, drago, slabo\n" +
                "4, Koper, pizze, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "2, Portoroz, zar, brezGlutenski, srednje, slabo\n" +
                "3, Celje, pizze, brezLaktozni, drago, dobro\n" +
                "5, Koper, pizze, brezLaktozni, drago, dobro\n" +
                "4, NovoMesto, zar, brez, srednje, odlicno\n" +
                "2, NovoMesto, testenine, brezLaktozni, drago, slabo\n" +
                "3, Maribor, pizze, brez, srednje, dobro\n" +
                "4, Celje, zar, brez, srednje, dobro\n" +
                "2, Celje, testenine, brezLaktozni, srednje, slabo\n" +
                "5, Maribor, pizze, brez, drago, odlicno\n" +
                "3, Maribor, testenine, brezLaktozni/Glutenski, poceni, dobro\n" +
                "4, NovoMesto, morskaHrana, brez, srednje, dobro\n" +
                "5, Ljubljana, zar, brezLaktozni, srednje, odlicno\n" +
                "3, Koper, morskaHrana, brezLaktozni/Glutenski, drago, dobro\n" +
                "2, Portoroz, morskaHrana, brezGlutenski, drago, slabo\n" +
                "1, Celje, pizze, brezGlutenski, poceni, slabo\n" +
                "3, Ljubljana, zar, brezLaktozni, srednje, dobro\n" +
                "4, Koper, pizze, brez, poceni, odlicno\n" +
                "2, Maribor, testenine, brez, srednje, slabo\n" +
                "5, Ljubljana, morskaHrana, brezLaktozni/Glutenski, srednje, odlicno\n" +
                "4, Koper, pizze, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "2, Portoroz, zar, brezGlutenski, srednje, slabo\n" +
                "3, Celje, pizze, brezLaktozni, drago, dobro\n" +
                "5, Maribor, morskaHrana, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "4, NovoMesto, zar, brez, srednje, odlicno\n" +
                "2, NovoMesto, testenine, brezLaktozni, drago, slabo\n" +
                "3, Maribor, pizze, brez, srednje, dobro\n" +
                "4, Celje, pizze, brezGlutenski, poceni, odlicno\n" +
                "2, Celje, testenine, brezLaktozni, srednje, slabo\n" +
                "5, Maribor, pizze, brez, drago, odlicno\n" +
                "3, Maribor, testenine, brezLaktozni/Glutenski, poceni, dobro\n" +
                "4, NovoMesto, morskaHrana, brez, srednje, dobro\n" +
                "5, Ljubljana, zar, brezLaktozni, srednje, odlicno\n" +
                "3, Koper, morskaHrana, brezLaktozni/Glutenski, drago, dobro\n" +
                "5, Portoroz, morskaHrana, brez, srednje, odlicno\n" +
                "1, Celje, pizze, brezGlutenski, poceni, slabo\n" +
                "3, Ljubljana, zar, brezLaktozni, srednje, dobro\n" +
                "4, Koper, pizze, brez, poceni, odlicno\n" +
                "3, Portoroz, pizze, brezLaktozni, drago, slabo\n" +
                "5, Ljubljana, morskaHrana, brezLaktozni/Glutenski, srednje, odlicno\n" +
                "4, Koper, pizze, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "2, Portoroz, zar, brezGlutenski, srednje, slabo\n" +
                "3, Celje, pizze, brezLaktozni, drago, dobro\n" +
                "5, Maribor, morskaHrana, brezLaktozni/Glutenski, poceni, odlicno\n" +
                "4, NovoMesto, zar, brez, srednje, odlicno\n" +
                "2, NovoMesto, testenine, brezLaktozni, drago, slabo\n" +
                "3, Maribor, pizze, brez, srednje, dobro";

        gumb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String sestavljen = "%Klemen Ursic\n" +
                        "\n" +
                        "@RELATION restavrcije\n" +
                        "\n" +
                        "@ATTRIBUTE ocena {1,2,3,4,5}\n" +
                        "@ATTRIBUTE lokacija {Maribor, Celje, NovoMesto, Ljubljana, Koper, Portoroz}\n" +
                        "@ATTRIBUTE hrana {zar, testenine, morskaHrana, pizze}\n" +
                        "@ATTRIBUTE posebniIzdelki {brez, brezLaktozni, brezGlutenski, brezLaktozni/Glutenski}\n" +
                        "@ATTRIBUTE cenovniRang {poceni, srednje, drago}\n" +
                        "@ATTRIBUTE razred {slabo, dobro, odlicno}\n" +
                        "\n" +
                        "@DATA\n" +
                        spinnerOcena.getSelectedItem().toString() + ", " + lokacija.getText().toString() + ", " + spinnerHrana.getSelectedItem().toString() + ", " + spinnerPosebni.getSelectedItem().toString() + ", " + spinnerCena.getSelectedItem().toString() + ", ?";

                ArffLoader ar1 = new ArffLoader();
                InputStream input1=new ByteArrayInputStream(sestavljen.getBytes());
                ArffLoader ar = new ArffLoader();
                InputStream input=new ByteArrayInputStream(str.getBytes());
                try {
                    ar.setSource(input);
                    Instances data = ar.getDataSet();
                    if (data.classIndex() == -1)
                        data.setClassIndex(data.numAttributes() -1);

                    NaiveBayes nB = new NaiveBayes();
                    nB.buildClassifier(data);
                    Evaluation eval = new Evaluation(data);
                    eval.crossValidateModel(nB, data, 10, new Random(1));


                    ar1.setSource(input1);
                    Instances data1=ar1.getDataSet();
                    data1.setClassIndex(data1.numAttributes()-1);
                    nB.buildClassifier(data);

                    for(int i=0;i<data1.numInstances();i++){
                        double index = nB.classifyInstance(data1.instance(i));
                        String className = data1.attribute(data1.numAttributes() - 1).value((int)index);

                        izpis.setText("Razred instance: "+ className);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void onClick(View v) {
    }
}
