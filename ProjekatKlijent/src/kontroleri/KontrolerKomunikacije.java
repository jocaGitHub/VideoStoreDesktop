/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import domen.Clan;
import domen.Film;
import domen.IDomenskiObjekat;
import domen.Kopija;
import domen.Mesto;
import domen.Osoba;
import domen.Radnici;
import domen.Uloga;
import domen.Zaduzenje;
import forme.FUnosClana;
import forme.FUnosFilm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import konstante.Konstante;
import modeli.ModelTabeleSviClanovi;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Dario
 */
public class KontrolerKomunikacije {

    public static List<Zaduzenje> uzmiListuZaduzenjaZaClana(Clan c, JDialog aThis) {
        List<Zaduzenje> lista = null;
        List<Zaduzenje> zaVratiti = null;
        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(Konstante.OPERACIJA_VRATI_ZADUZENJA);
        try {
            Komunikacija.getInstanca().posaljiZahtev(kto);
            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();

            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                lista = (List<Zaduzenje>) sto.getPodaci();
                System.out.println(lista);
                zaVratiti = new ArrayList<>();
                for (Zaduzenje z : lista) {
                    if (z.getClan().getClanID().equals(c.getClanID())) {
                        zaVratiti.add(z);
                    }
                }
                System.out.println(zaVratiti);
                return zaVratiti;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije preuzeo listu zaduzenja" + sto.getGreska(), "Greska", JOptionPane.ERROR_MESSAGE);
                System.out.println("nije uspeo da vrati zaduzenje za clana");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("greska 1");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("greska 2");
        }

        return zaVratiti;
    }

    public static boolean obrisiClana(Clan c, JDialog aThis) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_OBRISI_CLANA);
            kto.setParametar(c);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
//                JOptionPane.showConfirmDialog(aThis, "da li zelite da obriste", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da obrise clana", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
            return false;
        }
    }

    public static List<Clan> vratiListuClanova(JDialog aThis) {
        List<Clan> lista = new ArrayList<>();
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VRATI_SVE_CLANOVE);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                lista = (List<Clan>) sto.getPodaci();
                return lista;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da vrati listu clanova", "Greska", JOptionPane.ERROR_MESSAGE);
                System.out.println("nije uspeo da vrati clanove");
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("greska 1");
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("greska 2");
            return null;
        }

    }

    public static String dodajClana(Clan clan) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_UNESI_CLANA);
            kto.setParametar(clan);
            System.out.println(clan);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return "ok";
            } else {
                String greska = sto.getGreska().getMessage();
                return greska;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("greska " + ex.getMessage());
            return ex.getMessage();
        }

    }

    public static List<Zaduzenje> uzmiZaduzenja(JDialog aThis) {
        List<Zaduzenje> lista = new ArrayList<>();
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VRATI_ZADUZENJA);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                lista = (List<Zaduzenje>) sto.getPodaci();
                return lista;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da uzme zaduzenja", "Greska", JOptionPane.ERROR_MESSAGE);
                System.out.println("nije uspeo da vrati zaduzenja");
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static String vratiNajveciID(IDomenskiObjekat ido) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_NAJVECI_ID);
            kto.setParametar(ido);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return (String) sto.getPodaci();
            } else {
                return "nije uspeo da primi id od servera, greska " + sto.getGreska().getMessage();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return "nije uspeo da primi id od servera " + ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return "nije uspeo da primi id od servera " + ex.getMessage();
        }
    }

    public static List<Mesto> vratiListuMesta(JDialog aThis) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VRATI_SVA_MESTA);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return (List<Mesto>) sto.getPodaci();
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da vrati sva mesta", "Greska", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (IOException ex) {
            System.out.println("greska kod mesta" + ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("greska kod mesta 2" + ex.getMessage());
            return null;
        }

    }

    public static void odustani(IDomenskiObjekat ido) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();

            if (ido instanceof Clan) {
                kto.setOperacija(Konstante.OPERACIJA_OBRISI_CLANA);
                Clan c = (Clan) ido;
                kto.setParametar(c);
            }
            if (ido instanceof Zaduzenje) {
                kto.setOperacija(Konstante.OPERACIJA_OBRISI_ZADUZENJE);
                Zaduzenje z = (Zaduzenje) ido;
                kto.setParametar(z);
            }
            if (ido instanceof Film) {
                kto.setOperacija(Konstante.OPERACIJA_OBRISI_FILM);
                Film f = (Film) ido;
                kto.setParametar(f);
            }
            if (ido instanceof Kopija) {
                kto.setOperacija(Konstante.OPERACIJA_OBRISI_KOPIJU);
                Kopija f = (Kopija) ido;
                kto.setParametar(f);
            }

            Komunikacija.getInstanca().posaljiZahtev(kto);
            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() != 1) {
                System.out.println("odustao je");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("nije poslat kto unos clana");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static List<Film> vratiListuFilmova(JDialog aThis) {
        List<Film> lista = new ArrayList<>();
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VRATI_SVE_FILMOVE);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                lista = (List<Film>) sto.getPodaci();
                return lista;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da vrati sve filmove", "Greska", JOptionPane.ERROR_MESSAGE);
                System.out.println("nije uspeo da vrati clanove");
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("greska 1");
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("greska 2");
            return null;
        }

    }

    public static List<Kopija> vratiListuKopija(JDialog aThis) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VRATI_SVE_KOPIJE);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return (List<Kopija>) sto.getPodaci();
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da vrati listu kopija", "Greska", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (IOException ex) {
            System.out.println("greska kod mesta" + ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("greska kod mesta 2" + ex.getMessage());
            return null;
        }
    }

    public static boolean obrisiZaduzenje(Zaduzenje z, JDialog aThis) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_OBRISI_ZADUZENJE);
            kto.setParametar(z);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da obrise zaduzenje", "Greska" + sto.getGreska().getMessage(), JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
            return false;
        }
    }

    public static boolean sacuvajZaduzenje(Zaduzenje z, JDialog aThis) {

        try {

            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_UNESI_ZADUZENJE);
            kto.setParametar(z);
//            System.out.println(clan);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije sacuvano zaduzenje " + sto.getGreska(), "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("greska " + ex.getMessage());
            return false;
        }
    }

    public static boolean sacuvajFilm(Film film, FUnosFilm aThis) {
        try {

            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_UNESI_FILM);
            kto.setParametar(film);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije sacuvano film " + sto.getGreska(), "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("greska " + ex.getMessage());
            return false;
        }
    }

    public static List<Osoba> vratiListuOsoba(JDialog aThis) {
        List<Osoba> lista = new ArrayList<>();
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VRATI_SVE_OSOBE);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                lista = (List<Osoba>) sto.getPodaci();
                return lista;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da vrati listu osoba", "Greska", JOptionPane.ERROR_MESSAGE);
                System.out.println("nije uspeo da vrati clanove");
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("greska 1");
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("greska 2");
            return null;
        }

    }

    public static boolean sacuvajUlogu(Uloga u, FUnosFilm aThis) {
        try {

            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_UNESI_ULOGU);
            kto.setParametar(u);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije sacuvana uloga " + sto.getGreska(), "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("greska " + ex.getMessage());
            return false;
        }
    }

    public static boolean obrisiUlogu(Uloga u, JDialog aThis) {

        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_OBRISI_ULOGU);
            kto.setParametar(u);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da obrise ulogu", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
            return false;
        }
    }

    public static int vratiRbKopije() {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_RB_KOPIJE);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return (int) sto.getPodaci();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static boolean sacuvajKopiju(Kopija kopija, JDialog aThis) {
        try {

            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_UNESI_KOPIJU);
            kto.setParametar(kopija);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije sacuvana kopija " + sto.getGreska(), "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("greska " + ex.getMessage());
            return false;
        }
    }

    public static boolean obrisiKopiju(Kopija kopija, FUnosFilm aThis) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_OBRISI_KOPIJU);
            kto.setParametar(kopija);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return true;
            } else {
                JOptionPane.showMessageDialog(aThis, "Nije uspeo da obrise kopiju", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("nije obrisao");
        }
        return false;
    }

    public static Radnici prijavi(Radnici radnik, JFrame aThis) {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_PRIJAVI);
            kto.setParametar(radnik);
            Komunikacija.getInstanca().posaljiZahtev(kto);

            ServerTransferObjekat sto = Komunikacija.getInstanca().vratiOdgovor();
            if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                return (Radnici) sto.getPodaci();
            } else {
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("greska 1");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("greska 2");
        }
        return null;
    }

}
