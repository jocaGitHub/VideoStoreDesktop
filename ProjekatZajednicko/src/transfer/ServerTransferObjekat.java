/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Joca
 */
public class ServerTransferObjekat implements Serializable{
    private Object podaci;
    private int uspesnostIzvrsenjaOperacije;
    private Exception greska;

    public Object getPodaci() {
        return podaci;
    }

    public int getUspesnostIzvrsenjaOperacije() {
        return uspesnostIzvrsenjaOperacije;
    }

    public Exception getGreska() {
        return greska;
    }

    public void setPodaci(Object podaci) {
        this.podaci = podaci;
    }

    public void setUspesnostIzvrsenjaOperacije(int uspesnostIzvrsenjaOperacije) {
        this.uspesnostIzvrsenjaOperacije = uspesnostIzvrsenjaOperacije;
    }

    public void setGreska(Exception greska) {
        this.greska = greska;
    }

    public ServerTransferObjekat() {
    }
}
