/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joca
 */
public class Osoba implements IDomenskiObjekat {

    private String osobaID;
    private String ime;
    private String prezime;

    public Osoba(String osobaID, String ime, String prezime) {
        this.osobaID = osobaID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Osoba() {
    }

    public String getOsobaID() {
        return osobaID;
    }

    public void setOsobaID(String osobaID) {
        this.osobaID = osobaID;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Osoba) {
            Osoba f = (Osoba) obj;
            if (osobaID.equals(f.getOsobaID())) {
                return true;
            }
        }
        return false;

    }

    @Override
    public String vratiNazivTabele() {
        return "osoba";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + osobaID + "','" + ime + "','" + prezime + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("osobaid");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");

                IDomenskiObjekat osoba = new Osoba(id, ime, prezime);
                objekti.add(osoba);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "osobaid ='" + osobaID + "', ime ='" + ime + "', prezime ='" + prezime + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "osobaid = '" + osobaID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        return null;
    }

}
