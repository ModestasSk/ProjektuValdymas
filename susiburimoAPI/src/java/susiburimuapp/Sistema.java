package susiburimuapp;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Sistema {
    SessionFactory sf = null;

    public Sistema() {
         sf = new Configuration().configure().buildSessionFactory();
    }
     public Vartotojas gautiVartotojaPagalPasta(String pastas) {
        Vartotojas stud = new Vartotojas();
        for (Vartotojas v : getVartotojai()) {
                if(v.getElpastas().equals(pastas))
                    return v;  
        }
        return null;
    }
     public Vartotojas gautiVartotoja(int kodas) {
        Vartotojas stud = new Vartotojas();
        for (Vartotojas v : getVartotojai()) {
                if(v.getKodas() == kodas)
                    return v;  
        }
        return null;
    }
     public Renginys gautiRengini(int kodas) {
        Renginys stud = new Renginys();
        for (Renginys r : getRenginiai()) {
                if(r.getKodas() == kodas)
                    return r;  
        }
        return null;
    }
    
    
     public ArrayList<Vartotojas> getVartotojai(){
        Session s = sf.openSession();
        ArrayList<Vartotojas> vartotojuSarasas = null;
        try {
            vartotojuSarasas = new ArrayList<Vartotojas>(s.createQuery("FROM Vartotojas").list());
        } catch (Exception e) {
        }
        s.close();
        return vartotojuSarasas;
     }
     
     public ArrayList<Renginys> getRenginiai(){
        Session s = sf.openSession();
        ArrayList<Renginys> renginiuSarasas = null;
        try {
            renginiuSarasas = new ArrayList<Renginys>(s.createQuery("FROM Renginys").list());
        } catch (Exception e) {
        }
        s.close();
        return renginiuSarasas;
     }
     
     public void pridetiVartotoja(Vartotojas v){
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(v);
            t.commit();
        } catch (Exception e) {
            System.out.println(e);
            t.rollback();
        }
        s.close();
     }
     public void pridetiRengini(Renginys r){
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(r);
            t.commit();
        } catch (Exception e) {
            System.out.println(e);
            t.rollback();
        }
        s.close();
     }
     
     public void priskirtiVartotojaPrieRenginio(Vartotojas vart, Renginys reng) {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            reng.getDalyviai().add(vart);
            s.merge(reng);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        }
        s.close();
    }
     
}
