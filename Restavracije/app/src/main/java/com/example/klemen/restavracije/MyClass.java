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

    public static MyClass getScenarij(){
        MyClass rest = new MyClass();
        rest.dodaj(new Restavracija(0,"Šmerc Ala Akbar",new Naslov("Celje", "Mariborska cesta", 12, "Celje", 3000), 3,true,true));
        rest.dodaj(new Restavracija(1, "Limbo",new Naslov("Celje", "Ljubecna", 31, "Celje", 3000),5,true,false));
        rest.dodaj(new Restavracija(2, "Vivaldi",new Naslov("Maribor", "Severdi komodor", 8, "Maribor", 2000),4,true,true));
        rest.dodaj(new Restavracija(3, "Pepito",new Naslov("Ljubljana", "Ulica za bregom", 12, "Ljubljana", 1000), 4,true,true));
        rest.dodaj(new Restavracija(4, "McDonalds",new Naslov("Novo Mesto", "Ulica Borisa Kriznica", 9, "Novo Mesto", 8600), 5,false,true));
        rest.dodaj(new Restavracija(5, "Pr Hostar",new Naslov("Ptuj", "Ulica se sam nevem kje", 69, "Ptuj", 2250), 2, true,true));
        rest.dodaj(new Restavracija(6, "Staro Sidro",new Naslov("Maribor", "Ulica bogu za hrbtom", 16, "Maribor", 2000), 3,true,false));
        rest.dodaj(new Restavracija(7, "Merkur restaurat",new Naslov("Vojnik", "Celjska cesta", 12, "Vojnik", 3212), 3,true,false));
        rest.dodaj(new Restavracija(8, "Belo nebo",new Naslov("Maribor", "Ulica kriznik", 16, "Maribor", 2000), 3,true,false));
        rest.dodaj(new Restavracija(9, "Delfincek",new Naslov("Maribor", "Ulica Marjana Plešastega", 20, "Maribor", 2000), 5,true,false));
        rest.dodaj(new Restavracija(10, "Novi okusi",new Naslov("Maribor", "Ulica rešpet", 1, "Maribor", 2000), 4,true,false));

        return rest;
    }

    public static MyClass getScenarijObiskanih(){
        MyClass rest = new MyClass();
        rest.dodaj(new Restavracija(1, "Limbo",new Naslov("Celje", "Ljubecna", 31, "Celje", 3000),5,true,false));
        rest.dodaj(new Restavracija(2, "Vivaldi",new Naslov("Maribor", "Severdi komodor", 8, "Maribor", 2000),4,true,true));
        rest.dodaj(new Restavracija(3, "Pepito",new Naslov("Ljubljana", "Ulica za bregom", 12, "Ljubljana", 1000), 4,true,true));
        return rest;
    }

    public Restavracija getRestavracija(int i){
        return restavracija.get(i);
    }

    public void sort() {
        Collections.sort(this.restavracija, Restavracija.StuNameComparator);
    }
}
