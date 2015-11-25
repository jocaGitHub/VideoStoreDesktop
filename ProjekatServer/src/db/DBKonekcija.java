/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Clan;
import domen.Film;
import domen.IDomenskiObjekat;
import domen.Kopija;
import domen.Mesto;
import domen.Radnici;
import domen.Zaduzenje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author Joca
 */
public class DBKonekcija {

    private Connection konekcija;

    private void ucitajDrajver() throws ClassNotFoundException {
        Class.forName(Konfiguracija.vratiInstancu().vratiDriver());
//        System.out.println("Ucitan je driver");
    }

    public void otvoriKonekciju() {
        try {
            ucitajDrajver();

            konekcija = DriverManager.
                    getConnection(Konfiguracija.vratiInstancu().vratiURL(),
                            Konfiguracija.vratiInstancu().vratiUsername(),
                            Konfiguracija.vratiInstancu().vratiPassword());
//            System.out.println("Uspostavljena konekcija");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Nije uspostavljena konekciaj: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Drajver nije ucitan");
        }
    }

//    public List<Mesto> vratiListuMesta() {
//        List<Mesto> listaMesta = new ArrayList<>();
//        try {
//            String upit = "Select * from mesto";
//            Statement st = konekcija.createStatement();
//
//            ResultSet rs = st.executeQuery(upit);
//            while (rs.next()) {
//                String id = rs.getString("mestoid");
//                int postanskiBroj = rs.getInt("ptt");
//                String naziv = rs.getString("naziv");
//                Mesto m = new Mesto(id, postanskiBroj, naziv);
//                listaMesta.add(m);
//            }
//            rs.close();
//            st.close();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return listaMesta;
//    
//    }
    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            System.out.println("Greska pri zatvaranju konekcije" + ex.getMessage());
        }
    }

    public void potvrdiTransakciju() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ponistiTransakciju() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<IDomenskiObjekat> vratiListuObjekata(IDomenskiObjekat ido) throws Exception {
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            String upit = "select * from " + ido.vratiNazivTabele();

            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            objekti = ido.vratiListuNaOsnovuRS(rs);

            rs.close();
            s.close();

            if (ido instanceof Clan) {
//                List<Clan> listaClanova = new ArrayList<>();
                for (IDomenskiObjekat o : objekti) {
                    Clan c = (Clan) o;
                    c.setMesto((Mesto) vratiObjekat(c.getMesto()));
//                    listaClanova.add(c);
                }
            }
            if (ido instanceof Zaduzenje) {
                for (IDomenskiObjekat o : objekti) {
                    Zaduzenje z = (Zaduzenje) o;
                    z.setClan((Clan) vratiObjekat(z.getClan()));
                    z.setZaduzio((Radnici) vratiObjekat(z.getZaduzio()));
                    z.setRazduzio((Radnici) vratiObjekat(z.getRazduzio()));
//                    Kopija k = (Kopija) vratiObjekat(z.getKopija());
//                    Film f = (Film) vratiObjekat(z.getKopija().getFilm());
//                    z.setKopija(k);
                    z.setKopija((Kopija) vratiObjekat(z.getKopija()));
                    z.getKopija().setFilm((Film) vratiObjekat(z.getKopija().getFilm()));

                }
            }
            if (ido instanceof Kopija) {
                for (IDomenskiObjekat o : objekti) {
                    Kopija k = (Kopija) o;
                    k.setFilm((Film) vratiObjekat(k.getFilm()));
                }
            }

            rs.close();
            s.close();

            return objekti;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }

    }

    public void upisiObjekat(IDomenskiObjekat ido) throws Exception {
////        String sifra = null;
//        if(ido.vratiNazivTabele().equals("clan")){
//            Clan c = (Clan) ido;
//            c.setClanID(pronadjiNajveci(ido.vratiNazivTabele())+"");
////            sifra = c.getClanID();
//        }
//        if(ido.vratiNazivTabele().equals("film")){
//            Film f = (Film) ido;
//            f.setFilmID(pronadjiNajveci("film")+"");
////            sifra = f.getFilmID();
//        }
//        if(ido.vratiNazivTabele().equals("zaduzenje")){
//            Zaduzenje z = (Zaduzenje) ido;
//            z.setZaduzenjeID(pronadjiNajveci("zaduzenje")+"");
////            sifra = z.getZaduzenjeID();
//        }
        try {
            System.out.println(ido+"iz azuriraj");
            String upit = "insert into " + ido.vratiNazivTabele() + " values (" + ido.vratiVrednostiZaInsert() + ")";
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            s.close();
//            return sifra;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }

    }

    public int pronadjiNajveci(IDomenskiObjekat ido) {
        int najveci = 0;
        try {
            try {
                String upit = "select * from " + ido.vratiNazivTabele();
                Statement ss = konekcija.createStatement();
//                System.out.println(upit);
                ResultSet rs = ss.executeQuery(upit);
                while (rs.next()) {
                    if (rs.getInt(ido.vratiNazivTabele() + "id") > najveci) {
                        najveci = rs.getInt(ido.vratiNazivTabele() + "id");
                    }
                }
                rs.close();
                ss.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (ido.vratiNazivTabele().equals("clan")) {
                Clan c = (Clan) ido;
                c.setClanID(najveci + 1 + "");
                Mesto m = new Mesto();
                m.setMestoID("1");
                c.setMesto(m);

            }
            if (ido.vratiNazivTabele().equals("film")) {
                Film f = (Film) ido;
                f.setFilmID(najveci + 1 + "");
            }
            if (ido.vratiNazivTabele().equals("zaduzenje")) {
                Zaduzenje z = (Zaduzenje) ido;
                z.setZaduzenjeID(najveci + 1 + "");
            }
            if (ido.vratiNazivTabele().equals("kopija")) {
                Kopija k = (Kopija) ido;
                k.setKopijaID(najveci + 1 + "");
            }
            upisiObjekat(ido);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return najveci + 1;
    }

    public Connection getKonekcija() {
        return konekcija;
    }

//    public void obrisiClana(String id) {
//        try {
//            String upit ="delete from clan where clanid=?";
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            ps.setString(1, id);
//            ps.executeUpdate();
//            ps.close();
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    public void obrisiObjekat(IDomenskiObjekat ido) throws SQLException {

        String upit = "delete from " + ido.vratiNazivTabele() + " where " + ido.vratiIDOObjekta();
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
        s.close();

    }

    public void azurirajObjekat(IDomenskiObjekat ido) throws SQLException {
        String upit = "UPDATE " + ido.vratiNazivTabele() + " SET " + ido.vratiVrednostiZaAzuriranje()
                + " WHERE " + ido.vratiIDOObjekta();
//            System.out.println(upit);
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
        s.close();
    }

    private IDomenskiObjekat vratiObjekat(IDomenskiObjekat ido) throws SQLException {

        IDomenskiObjekat objekat = null;

        String upit = "select * from " + ido.vratiNazivTabele() + " where " + ido.vratiIDOObjekta();
//            System.out.println(upit);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        objekat = ido.vratiObjekatNaOsnovuRS(rs);
        rs.close();
        s.close();
        return objekat;

//            throw new Exception(ex.getMessage());
    }

    public int pronadjiRB() {
        int najveci = 0;
        try {
            String upit = "select * from kopija";
            Statement ss = konekcija.createStatement();
//                System.out.println(upit);
            ResultSet rs = ss.executeQuery(upit);
            while (rs.next()) {
                if (rs.getInt("rbr") > najveci) {
                    najveci = rs.getInt("rbr");
                }
            }
            rs.close();
            ss.close();
            return najveci + 1;
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void otvoriKonekciju(String vratiURL, String trim, String trim0) throws SQLException, ClassNotFoundException {
        ucitajDrajver();

        konekcija = DriverManager.getConnection(vratiURL, trim, trim0);
        System.out.println("Uspostavljena konekcija");
        konekcija.setAutoCommit(false);
    }

}
