/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.List;

/**
 *
 * @author Joca
 */
public class KolekcijaMesta {
    
    private List<Mesto>listaMesta;
    private static KolekcijaMesta instanca;

    private KolekcijaMesta() {
    }
    
    public static KolekcijaMesta vratiInstancu(){
        if(instanca==null)
            instanca = new KolekcijaMesta();
        return instanca;
    }
    
    public List<Mesto> vratiListuMesta(){
        return listaMesta;
    }
    
    public Mesto vratiMestoPoID(String mestoID){
        for (Mesto m : listaMesta) {
            if(m.getMestoID().equals(mestoID))
                return m;
        }
        return null;
    }
    
     public void postaviListu(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }
}
