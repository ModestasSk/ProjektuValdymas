/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susiburimuapp;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class Renginys {
    @Expose
    private int kodas;
    @Expose
    private double kaina;
    @Expose
    private String pavadinimas;
    @Expose
    private String aprasymas;
    @Expose
    private String data;
    @Expose
    private String statusas;
    private Set<Vartotojas> dalyviai = new HashSet();

    public Renginys() {
    }

    public int getKodas() {
        return kodas;
    }

    public void setKodas(int kodas) {
        this.kodas = kodas;
    }

    public double getKaina() {
        return kaina;
    }

    public void setKaina(double kaina) {
        this.kaina = kaina;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatusas() {
        return statusas;
    }

    public void setStatusas(String statusas) {
        this.statusas = statusas;
    }

    public Set<Vartotojas> getDalyviai() {
        return dalyviai;
    }

    public void setDalyviai(Set<Vartotojas> dalyviai) {
        this.dalyviai = dalyviai;
    }

    @Override
    public String toString() {
        return pavadinimas + " " + dalyviai;//To change body of generated methods, choose Tools | Templates.
    }
    
    
}
