package com.example.klemen.restavracije;

import android.app.Application;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Klemen on 24. 05. 2016.
 */
public class ApplicationMy extends Application {
    private MyClass all;
    private static final String FILE_NAME = "restavracije.json";
    private static final String DATA_MAP = "restavracijemapa";
    @Override
    public void onCreate() {
        super.onCreate();
        if (!load()) {
            all = MyClass.getScenarij();
            save();
        }
    }
    public MyClass getAll() {
        all.sort();
        return all;
    }

    public int size()
    {
        return all.sizeRestavrecij();
    }

    public void dodaj(Restavracija r1)
    {
        all.dodaj(r1);
    }

    public MyClass getByMesto(String mesto, String kategorija){
        MyClass ret = new MyClass();
        for (int i=0;i<all.sizeRestavrecij();i++) {
            if (mesto.equals(all.getRestavracija(i).getNaslov().getMesto().toString()))
                ret.dodaj(all.getRestavracija(i));
        }
        if(!kategorija.equals("vse"))
        {
            MyClass rest = new MyClass();
            for (int i = 0; i < ret.sizeRestavrecij(); i++) {
                if (kategorija.equals("lak") && ret.getRestavracija(i).getLaktoza())
                    rest.dodaj(ret.getRestavracija(i));
                else if (kategorija.equals("glu") && ret.getRestavracija(i).getGlukoza())
                    rest.dodaj(ret.getRestavracija(i));
            }
            rest.sort();
            return rest;
        }
        ret.sort();
        return ret;
    }
    private void setAll(MyClass all) {
        this.all = all;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean save() {

        return save(all,FILE_NAME);
    }
    public boolean load(){
        MyClass tmp = load(FILE_NAME);
        if (tmp!=null) all = tmp;
        else return false;
        return true;
    }

    private boolean save(MyClass a, String filename) {
        if (isExternalStorageWritable()) {
            File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                    + filename);
            try {
                long start = System.currentTimeMillis();
                System.out.println("Save "+file.getAbsolutePath()+" "+file.getName());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                PrintWriter pw = new PrintWriter(file);
                String sss=gson.toJson(a);
                System.out.println("Save time gson:"+(double)(System.currentTimeMillis()-start)/1000);
                pw.println(sss);
                pw.close();
                System.out.println("Save time s:"+(double)(System.currentTimeMillis()-start)/1000);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error save! (FileNotFoundException)");
            } catch (IOException e) {
                System.out.println("Error save! (IOException)");
            }
        } else{
            System.out.println(this.getClass().getCanonicalName()+" NOT Writable");
        }
        return false;
    }
    private MyClass load(String name) {
        if (isExternalStorageReadable()) {
            try {
                File file = new File(this.getExternalFilesDir(DATA_MAP),"" + name);
                System.out.println("Load "+file.getAbsolutePath()+" "+file.getName());
                FileInputStream fstream = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader( new InputStreamReader(in));
                StringBuffer sb = new StringBuffer();
                String strLine;
                while ((strLine = br.readLine()) != null) {sb.append(strLine).append('\n');}
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                MyClass a = gson.fromJson(sb.toString(), MyClass.class);
                if (a == null) { System.out.println("Error: fromJson Format error");
                } else { System.out.println(a.toString()); };
                return a;
            } catch (IOException e) {
                System.out.println("Error load "+e.toString());
            }}
        System.out.println("ExternalStorageAvailable is not avaliable");
        return null;
    }
}
