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

/**
 *
 * @author Joca
 */
public class Film implements IDomenskiObjekat {

    private String filmID;
    private String naziv;
    private int godinaSnimanja;
    private String zanr;
    private List<Uloga>listaUloga;
    private int br;

    public Film() {
        listaUloga = new ArrayList<>();
        br = 0;
    }

    public Film(String filmID, String naziv, int godinaSnimanja, String zanr) {
        this.filmID = filmID;
        this.naziv = naziv;
        this.godinaSnimanja = godinaSnimanja;
        this.zanr = zanr;
        br = 0;
        listaUloga = new ArrayList<>();
    }
    
    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodinaSnimanja() {
        return godinaSnimanja;
    }

    public void setGodinaSnimanja(int godinaSnimanja) {
        this.godinaSnimanja = godinaSnimanja;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Film) {
            Film f = (Film) obj;
            if (filmID.equals(f.getFilmID())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "film";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + filmID + "','" + naziv + "'," + godinaSnimanja + ",'" + zanr + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("filmid");
                String naziv = rs.getString("naziv");
                String zanr = rs.getString("zanr");
                int godina = rs.getInt("godinasnimanja");

                IDomenskiObjekat film = new Film(id, naziv, godina, zanr);
                objekti.add(film);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "filmid ='" + filmID + "', naziv ='" + naziv + "', godinasnimanja =" + godinaSnimanja + ", zanr = '" + zanr + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "filmid = '" + filmID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Film f = null;
        if (rs.next()) {
            f = new Film();
            f.setFilmID(rs.getString("filmid"));
            f.setNaziv(rs.getString("naziv"));
            f.setZanr(rs.getString("zanr"));
            f.setGodinaSnimanja(rs.getInt("godinasnimanja"));
        }
        rs.close();
        return f;

    }

    public List<Uloga> getListaUloga() {
        return listaUloga;
    }

    public void setListaUloga(List<Uloga> listaUloga) {
        this.listaUloga = listaUloga;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int br) {
        this.br = br;
    }

}
