/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joca
 */
public class NitServer extends Thread{
    
    ServerSocket serverSoket;
    private boolean signal;
    private static List<NitKlijent> listaKlijenata = new ArrayList<>();

    public NitServer() {
        signal = true;
    }
    
    

    @Override
    public void run() {
        try {
            serverSoket = new ServerSocket(9001);
            while (signal) {      
                System.out.println("Server je pokrenut, ceka klijenta");
                Socket soket = serverSoket.accept();
                System.out.println("Klijent se povezao sa serverom");
                
                PrintWriter out = new PrintWriter(soket.getOutputStream(), true);
                out.println("ok");
                
                NitKlijent nit = new NitKlijent(soket);
                nit.start();
                ubaciUListuKlijenata(nit);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void ugasiServerSoket(){
        try {
            serverSoket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }
    
    
    public synchronized static List<NitKlijent> getListaKlijenata() {
        return listaKlijenata;
    }
    
    public synchronized static void izbaciIzListeKlijenata(NitKlijent nit){
        for (NitKlijent nk : listaKlijenata) {
            if(nk.equals(nit)){
                listaKlijenata.remove(nk);
                break;
            }
        }
    }
    
    public synchronized static void ubaciUListuKlijenata(NitKlijent nit){
        listaKlijenata.add(nit);
    }

    public static void setListaKlijenata(List<NitKlijent> aListaKlijenata) {
        listaKlijenata = aListaKlijenata;
    }
    
}
