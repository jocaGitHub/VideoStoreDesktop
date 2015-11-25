/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomenskiObjekat;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;

/**
 *
 * @author Dario
 */
public class SOVratiRbKopije extends AbstractSO{
    int rb;
    public SOVratiRbKopije() {
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
    }

    @Override
    protected void proveriPreduslov() throws PreConditionException {
    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            rb = db.pronadjiRB();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SistemOperationException(e.getMessage());
        }
    }
    
    public int vratiRb(){
        return rb;
    }
}
