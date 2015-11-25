/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import javax.swing.JTable;
import model.ModelTabele_PrijavljeniKlijenti;

/**
 *
 * @author Joca
 */
public class NitOsveziTabelu extends Thread{

    JTable tabela;
    boolean signal;

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }
    
    public NitOsveziTabelu(JTable jTable1) {
        tabela = jTable1;
        signal = true;
    }

    @Override
    public void run() {
        while(signal){
            try {
                sleep(5000);
                tabela.setModel(new ModelTabele_PrijavljeniKlijenti(NitServer.getListaKlijenata()));
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
    
    
}
