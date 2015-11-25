/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mesta;

import db.DBKonekcija;
import domen.IDomenskiObjekat;
import domen.Mesto;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author student1
 */
public class SOVratiListuMesta extends AbstractSO {

    List<IDomenskiObjekat> listaMesta;

    @Override
    protected void izvrsiValidaciju() throws ValidationException {

    }

    @Override
    protected void proveriPreduslov() throws PreConditionException {

    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            listaMesta = db.vratiListuObjekata(new Mesto());
            System.out.println("lista mesta SO" + listaMesta);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
        }
    }

    public List<IDomenskiObjekat> vratiListu() {
        return listaMesta;
    }
}
