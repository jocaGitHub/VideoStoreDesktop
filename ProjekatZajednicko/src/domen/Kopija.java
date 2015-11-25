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
public class Kopija implements IDomenskiObjekat {

    private Film film;
    private String kopijaID;
    private int rbr;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getKopijaID() {
        return kopijaID;
    }

    public void setKopijaID(String kopijaID) {
        this.kopijaID = kopijaID;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public Kopija() {
    }

    public Kopija(Film film, String kopijaID, int rbr) {
        this.film = film;
        this.kopijaID = kopijaID;
        this.rbr = rbr;
    }

    @Override
    public String toString() {
        return kopijaID + film.getNaziv();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Kopija) {
            Kopija f = (Kopija) obj;
            if (kopijaID.equals(f.getKopijaID())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "kopija";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        int r = 0;
        if(rbr!=0){
            r = rbr;
        }
        return "'" + kopijaID + "','" + film.getFilmID() + "','" + r + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String kopijaid = rs.getString("kopijaid");
                Film f = new Film();
                f.setFilmID(rs.getString("filmid"));
                int rbr = rs.getInt("rbr");

                IDomenskiObjekat kopija = new Kopija(f, kopijaid, rbr);
                objekti.add(kopija);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "kopijaid = '" + kopijaID + "', filmid = '" + film.getFilmID() + "', rbr = '" + rbr + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "filmid = '" + film.getFilmID() + "' and kopijaid = '" + kopijaID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Kopija k = null;
        if (rs.next()) {
            k = new Kopija();
            k.setKopijaID(rs.getString("kopijaid"));
            Film f = new Film();
            f.setFilmID(rs.getString("filmid"));
            k.setFilm(f);
            k.setRbr(rs.getInt("rbr"));
        }
        rs.close();
        return k;
    }

}
