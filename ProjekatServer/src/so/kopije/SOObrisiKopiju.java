/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kopije;

import domen.Kopija;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import so.AbstractSO;

/**
 *
 * @author Dario
 */
public class SOObrisiKopiju extends AbstractSO{
    Kopija kopija;

    public SOObrisiKopiju(Kopija kopija) {
        this.kopija = kopija;
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
            db.obrisiObjekat(kopija);
        } catch (Exception ex) {
            throw new SistemOperationException(ex.getMessage());
        }
    }
    
}
