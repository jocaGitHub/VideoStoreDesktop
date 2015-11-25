/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import forme.FGlavna;
import forme.FPodServera;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import komunikacija.Komunikacija;

/**
 *
 * @author student1
 */
public class StartKlijent {

    public static void main(String[] args) {

        if (!konfiguracija.KonfiguracijaKlijenta.vratiInstancu().vratiPodesenServer().equals("t")) {
            FPodServera fps = new FPodServera();
            fps.setVisible(true);
        } else {
            try {
                int port = Integer.parseInt(konfiguracija.KonfiguracijaKlijenta.vratiInstancu().vratiPort());
                Socket soket = new Socket(konfiguracija.KonfiguracijaKlijenta.vratiInstancu().vratiIPAdresu(), port);
                System.out.println("klijent se zakacio za server");

                BufferedReader inPoruka = new BufferedReader(new InputStreamReader(soket.getInputStream()));
                String poruka = inPoruka.readLine();
                System.out.println("poruka od servera" + poruka);
                if (poruka.equals("ok")) {
                    System.out.println("Klijent se povezao sa serverom");
                    Komunikacija.getInstanca().setSoket(soket);
                    new FGlavna().setVisible(true);

                } else {
                    System.out.println("Neuspesna konekcija");

                }

            } catch (IOException ex) {
                System.out.println("greska" + ex.getMessage());
            }

            
        }

    }

}
