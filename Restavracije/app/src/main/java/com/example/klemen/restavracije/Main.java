package com.example.klemen.restavracije;

/**
 * Created by Klemen on 1. 03. 2016.
 */
public class Main {
    public static void main(String [] args){
        Naslov naslov = new Naslov("Maribor", "Celjska cesta", 11, "Maribor", 3000);
        Restavracija res = new Restavracija(10, "DMG", naslov, 4,false,false,"04123443", "www.limbo.si", 0);
        Restavracija res1 = new Restavracija(11, "Dealt", naslov, 2,true,true,"04123443", "www.limbo.si", 0);
        MyClass polje = new MyClass();
        polje.dodaj(res);
        polje.dodaj(res1);
        System.out.println(polje);
    }
}
