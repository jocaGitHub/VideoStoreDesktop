/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Clan;
import domen.Film;
import domen.IDomenskiObjekat;
import domen.Kopija;
import domen.Radnici;
import domen.Zaduzenje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import kontroler.Kontroler;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Joca
 */
public class NitKlijent extends Thread {

    private Socket soket;
    private boolean signal;
    private String korisnickoIme;

    public NitKlijent(Socket soket) {
        this.soket = soket;
        signal = true;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            while (signal) {
                //prihvati zahtev
                KlijentTransferObjekat kto = (KlijentTransferObjekat) in.readObject();
                //obradi zahtev
                int operacija = kto.getOperacija();
                if(operacija == konstante.Konstante.KRAJ){
                    System.out.println("Klijent " + korisnickoIme + " je napustio server");
                    break;
                }
                ServerTransferObjekat sto = new ServerTransferObjekat();
                if (operacija == konstante.Konstante.OPERACIJA_VRATI_SVA_MESTA) {
                    try {
                        List<IDomenskiObjekat> listaDO = Kontroler.vratiListuMesta();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(listaDO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_VRATI_SVE_CLANOVE) {
                    try {
                        List<IDomenskiObjekat> listaDO = Kontroler.vratiListuClanova();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(listaDO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_UNESI_CLANA) {
                    try {
                        Kontroler.unesiClana((Clan) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_NAJVECI_ID) {
                    try {
                        String id = Kontroler.vratiID((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(id);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_RB_KOPIJE) {
                    try {
                        int rb = Kontroler.vratiRbKopije();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(rb);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_UNESI_KOPIJU) {
                    try {
                        Kontroler.unesiKopiju((Kopija) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_OBRISI_KOPIJU) {
                    try {
                        Kontroler.obrisiKopiju((Kopija) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_ZADUZENJA_ZA_CLANA) {
                    try {
                        Clan clan = (Clan) kto.getParametar();
                        List<IDomenskiObjekat> listaZaduzenja = Kontroler.vratiZaduzenja();
                        System.out.println("lista zaduzenja u lista zaduzenja za clana" + listaZaduzenja.size());
                        for (IDomenskiObjekat ido : listaZaduzenja) {
                            Zaduzenje z = (Zaduzenje) ido;
                            if (z.getClan().getClanID().equals(clan.getClanID())) {
                            } else {
                                listaZaduzenja.remove(ido);
                            }
                        }
                        sto.setPodaci(listaZaduzenja);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_OBRISI_CLANA) {
                    try {
                        Kontroler.obrisiClana((Clan) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_VRATI_ZADUZENJA) {
                    try {
                        List<IDomenskiObjekat> listaDO = Kontroler.vratiZaduzenja();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(listaDO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_VRATI_SVE_FILMOVE) {
                    try {
                        List<IDomenskiObjekat> listaDO = Kontroler.vratiListuFilmova();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(listaDO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_VRATI_SVE_OSOBE) {
                    try {
                        List<IDomenskiObjekat> listaOsoba = Kontroler.vratiListuOsoba();
                        sto.setPodaci(listaOsoba);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_VRATI_SVE_KOPIJE) {
                    try {
                        List<IDomenskiObjekat> listaDO = Kontroler.vratiListuKopija();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(listaDO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_OBRISI_ZADUZENJE) {
                    try {
                        Kontroler.obrisiZaduzenje((Zaduzenje) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_UNESI_ZADUZENJE) {
                    try {
                        Kontroler.unesiZaduzenje((Zaduzenje) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_UNESI_FILM) {
                    try {
                        Kontroler.unesiFilm((Film) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == konstante.Konstante.OPERACIJA_OBRISI_FILM) {
                    try {
                        Kontroler.obrisiFilm((Film) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }
                if (operacija == konstante.Konstante.OPERACIJA_PRIJAVI) {
                    try {
                        List<IDomenskiObjekat> lista = Kontroler.vratiSveRadnike();
                        Radnici radnik = (Radnici) kto.getParametar();
                        if (lista.contains(radnik)) {
                            sto.setUspesnostIzvrsenjaOperacije(1);
                            Radnici r = null;
                            for (IDomenskiObjekat ido : lista) {
                                Radnici rr = (Radnici) ido;
                                if (rr.equals(radnik)) {
                                    r = rr;
                                }
                            }
                            sto.setPodaci(r);
                            korisnickoIme = r.getKorisnickoIme();
                        } else {
                            sto.setUspesnostIzvrsenjaOperacije(-1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setGreska(ex);
                    }
                    out.writeObject(sto);

                }

            }

            System.out.println("ugasila se nit kijent");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // brise sebe iz liste prijavljenih klijenata
        NitServer.izbaciIzListeKlijenata(this);
    }

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Socket getSoket() {
        return soket;
    }

    public void setSoket(Socket soket) {
        this.soket = soket;
    }

    public void ugasi() throws IOException {
        soket.close();
    }

}
