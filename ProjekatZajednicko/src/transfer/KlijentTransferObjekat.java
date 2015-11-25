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
public class KlijentTransferObjekat implements Serializable{
    private int operacija;
    private Object parametar;

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public int getOperacija() {
        return operacija;
    }

    public Object getParametar() {
        return parametar;
    }
}
