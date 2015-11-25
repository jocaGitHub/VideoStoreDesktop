/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joca
 */
public class Clan implements IDomenskiObjekat {

    private String clanID;
    private String ime;
    private String prezime;
    private String jmbg;
    private String telefon;
    private String UlicaIBroj;
    private Mesto mesto;
    private Date datumOsnivanja;

    public String getClanID() {
        return clanID;
    }

    public void setClanID(String clanID) {
        this.clanID = clanID;
    }

    public Clan(String clanID, String ime, String prezime, String jmbg, String telefon, String UlicaIBroj, Mesto mesto, Date datumOsnivanja) {
        this.clanID = clanID;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.telefon = telefon;
        this.UlicaIBroj = UlicaIBroj;
        this.mesto = mesto;
        this.datumOsnivanja = datumOsnivanja;
    }

    public Clan() {
        this.mesto = new Mesto();
        this.datumOsnivanja = new Date();
    }

    @Override
    public String toString() {
//        return ime + prezime + clanID + mesto.getMestoID();
        return ime + " "+prezime + " "+clanID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Clan) {
            Clan c = (Clan) obj;
            if (c.getClanID().equals(clanID)) {
                return true;
            }
        }
        return false;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public String getUlicaIBroj() {
        return UlicaIBroj;
    }

    public void setUlicaIBroj(String UlicaIBroj) {
        this.UlicaIBroj = UlicaIBroj;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    @Override
    public String vratiNazivTabele() {
        return "clan";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        String sDatum = new SimpleDateFormat("yyyy-MM-dd").format(datumOsnivanja);
        return "'" + ime + "','" + prezime + "','" + jmbg + "','" + telefon + "','" + UlicaIBroj + "','"
                + sDatum + "','" + clanID + "','" + mesto.getMestoID() + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {

        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("clanid");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String jmbg = rs.getString("jmbg");
                String telefon = rs.getString("telefon");
                String ulicaibroj = rs.getString("ulicaibroj");
                Date datum = rs.getDate("datumosnivanja");
                Mesto m = new Mesto();
                m.setMestoID(rs.getString("mestoid"));
                IDomenskiObjekat clan = new Clan(id, ime, prezime, jmbg, telefon, ulicaibroj, m, datum);
                objekti.add(clan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        String sDatum = new SimpleDateFormat("yyyy-MM-dd").format(datumOsnivanja);
        return "ime = '" + ime + "', prezime ='" + prezime + "', jmbg ='" + jmbg + "', telefon ='" + telefon
                + "', ulicaibroj ='" + UlicaIBroj + "', datumosnivanja ='"
                + sDatum + "', clanid ='" + clanID + "', mestoid ='" + mesto.getMestoID() + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "clanid = '" + clanID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Clan c = null;
        if (rs.next()) {
            c = new Clan();
            c.setClanID(rs.getString("clanid"));
            c.setIme(rs.getString("ime"));
            c.setPrezime(rs.getString("prezime"));
            c.setJmbg(rs.getString("jmbg"));
            c.setTelefon(rs.getString("telefon"));
            c.setUlicaIBroj(rs.getString("ulicaibroj"));
            c.setDatumOsnivanja(rs.getDate("datumosnivanja"));
            Mesto m = new Mesto();
            m.setMestoID("mestoid");
            c.setMesto(m);
        }
        rs.close();
        return c;

    }

}
