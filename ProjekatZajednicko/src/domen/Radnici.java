/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joca
 */
public class Radnici implements IDomenskiObjekat {

    private String radniciID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String korisnickaSifra;
    private String telefon;

    public Radnici(String radniciID, String ime, String prezime, String korisnickoIme, String korisnickaSifra, String telefon) {
        this.radniciID = radniciID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
        this.telefon = telefon;
    }

    public Radnici() {
    }

    public String getRadniciID() {
        return radniciID;
    }

    public void setRadniciID(String radniciID) {
        this.radniciID = radniciID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return radniciID + " " + ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Radnici) {
            Radnici f = (Radnici) obj;
            if (korisnickoIme.equals(f.getKorisnickoIme()) && korisnickaSifra.equals(f.getKorisnickaSifra())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "radnici";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + radniciID + "','" + ime + "','" + prezime + "','" + korisnickoIme + "','"
                + korisnickaSifra + "','" + telefon + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("radniciid");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String telefon = rs.getString("telefon");
                String korisnickoime = rs.getString("korisnickoime");
                String sifra = rs.getString("korisnickasifra");

                IDomenskiObjekat radnici = new Radnici(id, ime, prezime, korisnickoime, sifra, telefon);
                objekti.add(radnici);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "radniciid = '" + radniciID + "', ime = '" + ime + "', prezime = '" + prezime
                + "', korisnickoime = '" + korisnickoIme + "', korisnickasifra = '"
                + korisnickaSifra + "', telefon = '" + telefon + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "radniciid = '" + radniciID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Radnici r = null;
        if (rs.next()) {
            r = new Radnici();
            r.setRadniciID(rs.getString("radniciid"));
            r.setIme(rs.getString("ime"));
            r.setPrezime(rs.getString("prezime"));
            r.setTelefon(rs.getString("telefon"));
            r.setKorisnickoIme(rs.getString("korisnickoime"));
            r.setKorisnickaSifra(rs.getString("korisnickasifra"));
        }
        rs.close();
        return r;

    }

}
