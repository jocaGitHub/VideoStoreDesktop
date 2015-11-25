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
public class KolekcijaClanova {

    private List<Clan> listaClanova;
    private static KolekcijaClanova instanca;

    private KolekcijaClanova() {
    }

    public static KolekcijaClanova vratiInstancu() {
        if (instanca == null) {
            instanca = new KolekcijaClanova();
        }
        return instanca;
    }

    public List<Clan> vratiListuClanova() {
        return listaClanova;
    }

    public Clan vratiClanaPoID(String id) {
        for (Clan c : listaClanova) {
            if (c.getClanID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void postaviListu(List<Clan> listaClanova) {
        this.listaClanova = listaClanova;
    }
    
    public void dodajClana(Clan clan){
        listaClanova.add(clan);
    }
}
