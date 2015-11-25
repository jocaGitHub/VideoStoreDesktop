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
public class Zaduzenje implements IDomenskiObjekat {

    private String zaduzenjeID;
    private Clan clan;
    private Kopija kopija;
    private Date datumZaduzenja;
    private Date datumRazduzenja;
    private String statusZaduzenja;
    private Radnici zaduzio;
    private Radnici razduzio;

    public Zaduzenje(String zaduzenjeID, Clan clan, Kopija kopija, Date datumZaduzenja, Date datumRazduzenja, String statusZaduzenja, Radnici zaduzio, Radnici razduzio) {
        this.zaduzenjeID = zaduzenjeID;
        this.clan = clan;
        this.kopija = kopija;
        this.datumZaduzenja = datumZaduzenja;
        this.datumRazduzenja = datumRazduzenja;
        this.statusZaduzenja = statusZaduzenja;
        this.zaduzio = zaduzio;
        this.razduzio = razduzio;
    }

    public Zaduzenje() {
    }

    
    public String getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(String zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Kopija getKopija() {
        return kopija;
    }

    public void setKopija(Kopija kopija) {
        this.kopija = kopija;
    }

    public Date getDatumZaduzenja() {
        return datumZaduzenja;
    }

    public void setDatumZaduzenja(Date datumZaduzenja) {
        this.datumZaduzenja = datumZaduzenja;
    }

    public Date getDatumRazduzenja() {
        return datumRazduzenja;
    }

    public void setDatumRazduzenja(Date datumRazduzenja) {
        this.datumRazduzenja = datumRazduzenja;
    }

    public String isStatusZaduzenja() {
        return statusZaduzenja;
    }

    public void setStatusZaduzenja(String statusZaduzenja) {
        this.statusZaduzenja = statusZaduzenja;
    }

    public Radnici getZaduzio() {
        return zaduzio;
    }

    public void setZaduzio(Radnici zaduzio) {
        this.zaduzio = zaduzio;
    }

    public Radnici getRazduzio() {
        return razduzio;
    }

    public void setRazduzio(Radnici razduzio) {
        this.razduzio = razduzio;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaduzenje";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        String datZ = null;
//        String datR = new SimpleDateFormat("dd/MM/yyyy").format(datumRazduzenja);
        String datR = null;
        return "'" + zaduzenjeID + "','" + clan.getClanID() + "','" + kopija.getKopijaID() + "','"
                + kopija.getFilm().getFilmID() + "'," + datZ + "," + datR + ",'"
                + 0 + "','" + 0 + "','" + statusZaduzenja + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String zaduzenjeid = rs.getString("zaduzenjeid");
                Clan c = new Clan();
                c.setClanID(rs.getString("clanid"));
                Kopija kopija = new Kopija();
                kopija.setKopijaID(rs.getString("kopijaid"));
                Film f = new Film();
                f.setFilmID(rs.getString("filmid"));
                kopija.setFilm(f);
                String status = rs.getString("statuszaduzenja");
                Date datumZ = rs.getDate("datumzaduzenja");
                Date datumR = rs.getDate("datumrazduzenja");
                Radnici zaduzio = new Radnici();
                Radnici razduzio = new Radnici();
                zaduzio.setRadniciID(rs.getString("zaduzio"));
                razduzio.setRadniciID(rs.getString("razduzio"));

                IDomenskiObjekat zaduzenje = new Zaduzenje(zaduzenjeid, c, kopija, datumZ, datumR, status, zaduzio, razduzio);
                objekti.add(zaduzenje);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        String datZ = null;
        String datR = null;
        String raz = null;
        String zad = null;
        if (razduzio != null) {
             raz = "'"+razduzio.getRadniciID()+"'";
        }else{
            raz = "0";
        }
        if(zaduzio!=null){
            zad = "'"+zaduzio.getRadniciID()+"'";
        }else{
            zad = "0";
        }
        if (datumRazduzenja != null) {
            datR = "'"+new SimpleDateFormat("yyyy-MM-dd").format(datumRazduzenja)+"'";
        }
        if(datumZaduzenja != null){
            datZ = "'"+new SimpleDateFormat("yyyy-MM-dd").format(datumZaduzenja)+"'";
        }
        return "zaduzenjeid = '" + zaduzenjeID + "', clanid = '" + clan.getClanID() + "', kopijaid = '" + kopija.getKopijaID() + "', filmid = '"
                + kopija.getFilm().getFilmID() + "',  datumzaduzenja = " + datZ + " , datumrazduzenja = " + datR + " , razduzio = "
                + raz +", zaduzio = "+ zad + ", statuszaduzenja = '" + statusZaduzenja + "'";

    }

    @Override
    public String vratiIDOObjekta() {
        return "zaduzenjeid = '" + zaduzenjeID + "' and clanid = '" + clan.getClanID() + "' and kopijaid = '" + kopija.getKopijaID() + "' and filmid = '" + kopija.getFilm().getFilmID() + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Zaduzenje z = null;
        if (rs.next()) {
            z = new Zaduzenje();
            z.setZaduzenjeID(rs.getString("zaduzenjeid"));
            Clan c = new Clan();
            c.setClanID(rs.getString("clanid"));
            z.setClan(c);
            Kopija kopija = new Kopija();
            kopija.setKopijaID(rs.getString("kopijaid"));
            Film f = new Film();
            f.setFilmID(rs.getString("filmid"));
            kopija.setFilm(f);
            z.setKopija(kopija);
            z.setStatusZaduzenja(rs.getString("statuszaduzenja"));
            Date datumZ = rs.getDate("datumzaduzenja");
            z.setDatumZaduzenja(datumZ);
            Date datumR = rs.getDate("datumrazduzenja");
            z.setDatumRazduzenja(datumR);
            Radnici zaduzio = new Radnici();
            Radnici razduzio = new Radnici();
            zaduzio.setRadniciID(rs.getString("zaduzio"));
            z.setZaduzio(zaduzio);
            razduzio.setRadniciID(rs.getString("razduzio"));
            z.setRazduzio(razduzio);

        }
        rs.close();
        return z;
    }

}
