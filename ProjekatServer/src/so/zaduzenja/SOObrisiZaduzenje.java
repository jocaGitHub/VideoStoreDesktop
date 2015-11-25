/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaduzenja;

import domen.Zaduzenje;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOObrisiZaduzenje extends AbstractSO{
    Zaduzenje z;

    public SOObrisiZaduzenje(Zaduzenje z) {
        this.z = z;
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
            db.obrisiObjekat(z);
        } catch (Exception ex) {
            throw new SistemOperationException(ex.getMessage());
        }
    }
}
