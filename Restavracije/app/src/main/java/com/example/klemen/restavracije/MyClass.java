package com.example.klemen.restavracije;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyClass {

    ArrayList<Restavracija> restavracija = new ArrayList<>();

    public void dodaj(Restavracija r){
        restavracija.add(r);
    }

    public int sizeRestavrecij(){
        return restavracija.size();
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "restavracija=" + restavracija +
                '}';
    }
    public Restavracija getRestavracija(int i){
        return restavracija.get(i);
    }

    public void sort() {
        Collections.sort(this.restavracija, Restavracija.StuNameComparator);
    }
}
