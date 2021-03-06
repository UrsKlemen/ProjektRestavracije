package com.example.klemen.restavracije;

import java.util.Comparator;

/**
 * Created by Klemen on 1. 03. 2016.
 */
public class Restavracija {
    public Integer id;
    public String ime;
    public Naslov naslov;
    public Integer ocena_restavracije;
    public Boolean laktoza, glukoza;
    public String telefonska;
    public String spletna;
    public Integer stevec;

    public Restavracija(Integer id, String ime, Naslov naslov, Integer ocena_restavracije, Boolean laktoza, Boolean glukoza, String telefonska, String spletna, Integer stevec) {
        this.id = id;
        this.ime = ime;
        this.naslov = naslov;
        this.ocena_restavracije = ocena_restavracije;
        this.laktoza = laktoza;
        this.glukoza = glukoza;
        this.telefonska = telefonska;
        this.spletna = spletna;
        this.stevec = stevec;
    }

    @Override
    public String toString() {
        return "Restavracija{" +
                "ime='" + ime + '\'' +
                ", naslov=" + naslov +
                ", ocena_restavracije=" + ocena_restavracije +
                "}\n";
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setNaslov(Naslov naslov) {
        this.naslov = naslov;
    }

    public void setOcena_restavracije(Integer ocena_restavracije) {
        this.ocena_restavracije = ocena_restavracije;
    }
    public void setStevec()
    {
        this.stevec++;
    }
    public Integer getStevec()
    {
        return stevec;
    }

    public Integer getId()
    {return id;}

    public String getIme() {
        return ime;

    }

    public Naslov getNaslov() {
        return naslov;
    }

    public Integer getOcena_restavracije() {
        return ocena_restavracije;
    }

    public Boolean getLaktoza() {
        return  laktoza;
    }
    public Boolean getGlukoza(){
        return  glukoza;
    }

    public String getTelefonska() {
        return telefonska;
    }

    public String getSpletna() {
        return spletna;
    }

    public static Comparator<Restavracija> StuNameComparator = new Comparator<Restavracija>() {

        public int compare(Restavracija r1, Restavracija r2) {
            return r2.getOcena_restavracije().compareTo(r1.getOcena_restavracije());
        }};
}
