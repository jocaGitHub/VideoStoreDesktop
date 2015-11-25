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
 *
 * @author Joca
 */
public class KonfiguracijaKlijenta {

    private Properties konfiguracija;
    static KonfiguracijaKlijenta instanca;
    FileInputStream in;
    FileOutputStream out;

    private KonfiguracijaKlijenta() {
        konfiguracija = new Properties();
    }

    public static KonfiguracijaKlijenta vratiInstancu() {
        if (instanca == null) {
            instanca = new KonfiguracijaKlijenta();
        }
        return instanca;
    }

    public String vratiIPAdresu() {
        ucitaj();
        return konfiguracija.getProperty("ipadresa");
    }

    public String vratiPort() {
        ucitaj();
        return konfiguracija.getProperty("port");
    }

    public void postaviIPAdresu(String adresa) {
        konfiguracija.setProperty("ipadresa", adresa);
        sacuvaj();
    }

    public void postaviPort(String port) {
        konfiguracija.setProperty("port", port);
        sacuvaj();
    }

    public String vratiPodesenServer() {
        ucitaj();
        return konfiguracija.getProperty("podesen_server");
    }

    public void postaviPodesenServer(String bool) {
        konfiguracija.setProperty("podesen_server", bool);
        sacuvaj();
    }

    private void sacuvaj() {
        try {
            out = new FileOutputStream("podesavanjeServera.properties");
            konfiguracija.store(out, null);
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void ucitaj() {
        try {
            in = new FileInputStream("podesavanjeServera.properties");
            konfiguracija.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
