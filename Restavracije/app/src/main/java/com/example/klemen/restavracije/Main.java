package com.example.klemen.restavracije;

/**
 * Created by Klemen on 1. 03. 2016.
 */
public class Main {
    public static void main(String [] args){
        Naslov naslov = new Naslov("Maribor", "Celjska cesta", 11, "Maribor", 3000);
        Restavracija res = new Restavracija(10, "DMG", naslov, 4,false,false);
        Restavracija res1 = new Restavracija(11, "Dealt", naslov, 2,true,true);
        MyClass polje = new MyClass();
        polje.dodaj(res);
        polje.dodaj(res1);
        System.out.println(polje);
    }
}
