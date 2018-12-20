/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susiburimuapp;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pc
 */
public class Vartotojas {
    @Expose
    private int kodas;
    @Expose
    private String elpastas;
    @Expose
    private String slaptazodis;
    @Expose
    private String vardas;
    @Expose
    private String pavarde;
    @Expose
    private int amzius;
    @Expose
    private String lytis; 
    @Expose
    private Set<Renginys> renginiai = new HashSet();

    public Vartotojas() {
    }

    public Set<Renginys> getRenginiai() {
        return renginiai;
    }

    public void setRenginiai(Set<Renginys> renginiai) {
        this.renginiai = renginiai;
    }

    public Vartotojas(int id, String elpastas, String slaptazodis, String vardas, String pavarde, int amzius, String lytis) {
        this.kodas = id;
        this.elpastas = elpastas;
        this.slaptazodis = slaptazodis;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.amzius = amzius;
        this.lytis = lytis;
    }

    public int getKodas() {
        return kodas;
    }

    public void setKodas(int kodas) {
        this.kodas = kodas;
    }


   

    public String getElpastas() {
        return elpastas;
    }

    public void setElpastas(String elpastas) {
        this.elpastas = elpastas;
    }

  
    public String getSlaptazodis() {
        return slaptazodis;
    }

    public void setSlaptazodis(String slaptazodis) {
        this.slaptazodis = slaptazodis;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public int getAmzius() {
        return amzius;
    }

    public void setAmzius(int amzius) {
        this.amzius = amzius;
    }

    public String getLytis() {
        return lytis;
    }

    public void setLytis(String lytis) {
        this.lytis = lytis;
    }

   

    

    @Override
    public String toString() {
        return vardas + " " + pavarde; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
