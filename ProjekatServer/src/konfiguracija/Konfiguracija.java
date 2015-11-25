/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Joca
 */
public class Konfiguracija {

    private Properties konfiguracija;
    static Konfiguracija instanca;
    FileOutputStream out;
    FileInputStream in;

    private Konfiguracija() {
        konfiguracija = new Properties();
    }

    public static Konfiguracija vratiInstancu() {
        if (instanca == null) {
            instanca = new Konfiguracija();
        }
        return instanca;
    }

    public void postaviPodesenostBaze(String b) {
        konfiguracija.setProperty("podesena_baza", b);
        sacuvaj();
    }

    public String vratiPodesenostBaze() {
        ucitaj();
        return konfiguracija.getProperty("podesena_baza");
    }

    public void postaviTipDB(String access) {
        konfiguracija.setProperty("trenutna", access);
        sacuvaj();
    }

    public String vratiDriver() {
        ucitaj();
        return konfiguracija.getProperty(konfiguracija.getProperty("trenutna") + "_driver");
    }

    public String vratiURL() {
        ucitaj();
        return konfiguracija.getProperty(konfiguracija.getProperty("trenutna") + "_url") + konfiguracija.getProperty("naziv");
    }

    public String vratiUsername() {
        ucitaj();
        return konfiguracija.getProperty(konfiguracija.getProperty("trenutna") + "_username");
    }

    public String vratiPassword() {
        ucitaj();
        return konfiguracija.getProperty(konfiguracija.getProperty("trenutna") + "_password");
    }

    private synchronized void sacuvaj() {
        try {
            out = new FileOutputStream("parametriBaze.properties");
            konfiguracija.store(out, null);
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void ucitaj() {
        try {
            in = new FileInputStream("parametriBaze.properties");
            konfiguracija.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void postaviNaziv(String naziv) {
        konfiguracija.setProperty("naziv", naziv);
        sacuvaj();
    }

    public void postaviUsername(String username) {
        konfiguracija.setProperty("username", username);
        sacuvaj();
    }

    public void postaviPassword(String password) {
        konfiguracija.setProperty("password", password);
        sacuvaj();
    }

}
