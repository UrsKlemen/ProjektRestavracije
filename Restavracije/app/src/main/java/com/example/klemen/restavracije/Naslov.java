package com.example.klemen.restavracije;

/**
 * Created by Klemen on 1. 03. 2016.
 */
public class Naslov {
    public String mesto;
    public String ulica;
    public Integer hisna_stev;
    public String posta;
    public Integer postna_stev;

    public Naslov(String mesto, String ulica, Integer hisna_stev, String posta, Integer postna_stev) {
        this.mesto = mesto;
        this.ulica = ulica;
        this.hisna_stev = hisna_stev;
        this.posta = posta;
        this.postna_stev = postna_stev;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;

    }

    public String getUlica() {
        return ulica;
    }

    public Integer getHisna_stev() {
        return hisna_stev;
    }

    public String getPosta() {
        return posta;
    }

    public Integer getPostna_stev() {
        return postna_stev;
    }

    @Override
    public String toString() {

        return ulica + " " + hisna_stev + ", " + mesto;
    }

}
