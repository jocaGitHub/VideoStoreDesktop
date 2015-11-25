/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Clan;
import domen.Film;
import domen.IDomenskiObjekat;
import domen.Kopija;
import domen.Zaduzenje;
import java.util.List;
import so.AbstractSO;
import so.SOVratiListuRadnika;
import so.SOVratiRbKopije;
import so.clanovi.SOObrisiClana;
import so.clanovi.SOAzurirajClana;
import so.clanovi.SOVratiListuClanova;
import so.filmovi.SOAzurirajFilm;
import so.filmovi.SOObrisiFIlm;
import so.filmovi.SOVratiListuFilmova;
import so.id.SOVratiID;
import so.kopije.SOObrisiKopiju;
import so.kopije.SOUnesiKopiju;
import so.kopije.SOVratiListuKopija;
import so.mesta.SOVratiListuMesta;
import so.osobe.SOVratiListuOsoba;
import so.zaduzenja.SOAzurirajZaduzenje;
import so.zaduzenja.SOObrisiZaduzenje;
import so.zaduzenja.SOVratiZaduzenja;

/**
 *
 * @author Joca
 */
public class Kontroler {

    public static List<IDomenskiObjekat> vratiListuMesta() throws Exception {
        AbstractSO sos = new SOVratiListuMesta();
        sos.izvrsiOperaciju();
        return ((SOVratiListuMesta) sos).vratiListu();
    }

    public static List<IDomenskiObjekat> vratiListuClanova() throws Exception {
        AbstractSO sos = new SOVratiListuClanova();
        sos.izvrsiOperaciju();
        return ((SOVratiListuClanova) sos).vratiListu();
    }

    public static void unesiClana(Clan c) throws Exception {
        AbstractSO sos = new SOAzurirajClana(c);
        sos.izvrsiOperaciju();
    }

    public static String vratiID(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOVratiID(ido);
        sos.izvrsiOperaciju();
        return ((SOVratiID) sos).vratiID();
    }

    public static void obrisiClana(Clan c) throws Exception {
        AbstractSO sos = new SOObrisiClana(c);
        sos.izvrsiOperaciju();
    }

    public static List<IDomenskiObjekat> vratiZaduzenja() throws Exception {
        AbstractSO sos = new SOVratiZaduzenja();
        sos.izvrsiOperaciju();
        return ((SOVratiZaduzenja) sos).vratiListu();
    }

    public static List<IDomenskiObjekat> vratiListuFilmova() throws Exception {
        AbstractSO sos = new SOVratiListuFilmova();
        sos.izvrsiOperaciju();
        return ((SOVratiListuFilmova) sos).vratiListu();
    }

    public static List<IDomenskiObjekat> vratiListuKopija() throws Exception {
        AbstractSO sos = new SOVratiListuKopija();
        sos.izvrsiOperaciju();
        return ((SOVratiListuKopija) sos).vratiListu();
    }

    public static void obrisiZaduzenje(Zaduzenje zaduzenje) throws Exception {
        AbstractSO sos = new SOObrisiZaduzenje(zaduzenje);
        sos.izvrsiOperaciju();
    }

    public static void unesiZaduzenje(Zaduzenje zaduzenje) throws Exception {
        AbstractSO sos = new SOAzurirajZaduzenje(zaduzenje);
        sos.izvrsiOperaciju();
    }

    public static void unesiFilm(Film film) throws Exception {
        AbstractSO sos = new SOAzurirajFilm(film);
        sos.izvrsiOperaciju();
    }

    public static List<IDomenskiObjekat> vratiListuOsoba() throws Exception {
        AbstractSO sos = new SOVratiListuOsoba();
        sos.izvrsiOperaciju();
        return ((SOVratiListuOsoba) sos).vratiListu();
    }

    public static void obrisiFilm(Film film) throws Exception {
        AbstractSO sos = new SOObrisiFIlm(film);
        sos.izvrsiOperaciju();
    }

    public static int vratiRbKopije() throws Exception{
        AbstractSO sos = new SOVratiRbKopije();
        sos.izvrsiOperaciju();
        return ((SOVratiRbKopije) sos).vratiRb();
    }

    public static void unesiKopiju(Kopija kopija) throws Exception{
        AbstractSO sos = new SOUnesiKopiju(kopija);
        sos.izvrsiOperaciju();
    }

    public static void obrisiKopiju(Kopija kopija) throws Exception{
         AbstractSO sos = new SOObrisiKopiju(kopija);
        sos.izvrsiOperaciju();
    }

    public static List<IDomenskiObjekat> vratiSveRadnike() throws Exception{
        AbstractSO sos = new SOVratiListuRadnika();
        sos.izvrsiOperaciju();
        return ((SOVratiListuRadnika) sos).vratiListu();
    }
    
}
