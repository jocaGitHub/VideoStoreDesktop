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
public class Uloga implements IDomenskiObjekat {

    private Osoba osoba;
    private Film film;
    private String ulogaID;
    private String nazivUloge;

    public Uloga(Osoba osoba, Film film, String ulogaID, String nazivUloge) {
        this.osoba = osoba;
        this.film = film;
        this.ulogaID = ulogaID;
        this.nazivUloge = nazivUloge;
    }

    public Uloga() {
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getUlogaID() {
        return ulogaID;
    }

    public void setUlogaID(String ulogaID) {
        this.ulogaID = ulogaID;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

    public void setNazivUloge(String nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    @Override
    public String vratiNazivTabele() {
        return "uloga";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + film.getFilmID() + "','" + osoba.getOsobaID() + "','" + ulogaID + "','" + nazivUloge + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                Film f = new Film();
                f.setFilmID(rs.getString("filmid"));
                Osoba o = new Osoba();
                o.setOsobaID(rs.getString("osobaid"));
                String ulogaid = rs.getString("ulogaid");
                String naziv = rs.getString("nazivuloge");

                IDomenskiObjekat uloga = new Uloga(o, f, ulogaid, naziv);
                objekti.add(uloga);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "filmid = '" + film.getFilmID() + "', osobaid = '" + osoba.getOsobaID() + "', ulogaid = '" + ulogaID + "', nazivuloge = '" + nazivUloge + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "filmid = '" + film.getFilmID() + "' and osobaid = '" + osoba.getOsobaID() + "' and ulogaid = '" + ulogaID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Uloga u = null;
        if (rs.next()) {
            u = new Uloga();
            Film f = new Film();
            f.setFilmID(rs.getString("filmid"));
            u.setFilm(f);
            Osoba o = new Osoba();
            o.setOsobaID(rs.getString("osobaid"));
            u.setOsoba(o);
            u.setUlogaID(rs.getString("ulogaid"));
            u.setNazivUloge(rs.getString("nazivuloge"));

        }
        rs.close();
        return u;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Uloga) {
            Uloga u = (Uloga) obj;
            if (u.getFilm().getFilmID().equals(film.getFilmID()) && u.getOsoba().getOsobaID().equals(osoba.getOsobaID()) && u.getUlogaID().equals(ulogaID)) {
                return true;
            }
        }
        return false;
    }
    
    

}
