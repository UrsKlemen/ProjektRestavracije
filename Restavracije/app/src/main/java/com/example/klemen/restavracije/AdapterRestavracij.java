package com.example.klemen.restavracije;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Klemen on 24. 05. 2016.
 */
public class AdapterRestavracij extends RecyclerView.Adapter<AdapterRestavracij.ViewHolder>{
    public static final String PARAMETER_POSITION_1 = "POSITION_RESTAVRACIJ";
    private MyClass mDataset;
    Activity ac;
    //Obiskane obiskane;

    public class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public LinearLayout linearl;
        public ImageView imv1, imv2, ocena;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            linearl = (LinearLayout) v.findViewById(R.id.linearl);
            imv1 = (ImageView) v.findViewById(R.id.imv1);
            imv2 = (ImageView) v.findViewById(R.id.imv2);
            ocena = (ImageView) v.findViewById(R.id.ocena);
        }
    }

    public AdapterRestavracij(MyClass myDataSet, Activity ac){
        this.ac=ac;
        mDataset = myDataSet;
    }

    @Override
    public AdapterRestavracij.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Restavracija trenutni = mDataset.getRestavracija(position);
        holder.txtHeader.setText(trenutni.getIme());
        final int id = trenutni.getId();
        android.view.ViewGroup.LayoutParams layoutParams = holder.ocena.getLayoutParams();
        switch (trenutni.getOcena_restavracije().toString())
        {
            case "5":
                layoutParams.width = 70;
                layoutParams.height = 70;
                holder.ocena.setLayoutParams(layoutParams);
                break;
            case "4":
                layoutParams.width = 60;
                layoutParams.height = 60;
                holder.ocena.setLayoutParams(layoutParams);
                break;
            case "3":
                layoutParams.width = 50;
                layoutParams.height = 50;
                holder.ocena.setLayoutParams(layoutParams);
                break;
            case "2":
                layoutParams.width = 40;
                layoutParams.height = 40;
                holder.ocena.setLayoutParams(layoutParams);
                break;
        }
        holder.txtFooter.setText(trenutni.getNaslov().toString());
        if(trenutni.getGlukoza() && trenutni.getLaktoza())
        {
            holder.imv2.setImageResource(R.drawable.glutenfree);
            holder.imv1.setImageResource(R.drawable.lactofree);
        }
        else if(trenutni.getLaktoza())
            holder.imv1.setImageResource(R.drawable.lactofree);
        else if(trenutni.getGlukoza())
            holder.imv1.setImageResource(R.drawable.glutenfree);
        holder.linearl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dva = new Intent(ac, ActivityRestavracije.class);
                dva.putExtra("intVariableName",id);
                ac.startActivity(dva);
            }
        });
        holder.linearl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ac);
                builder.setMessage("Želite restavracijo dodati med obiskane restavracije?");
                builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*obiskane.dodaj(trenutni);
                        obiskane.save();*/
                        Toast toast = Toast.makeText(ac, "Restavracija je bila dodana med obiskane.",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER| Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
                builder.setNegativeButton("Ne", null);
                builder.create().show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return (null != mDataset ? mDataset.sizeRestavrecij() : 0);
    }
}
