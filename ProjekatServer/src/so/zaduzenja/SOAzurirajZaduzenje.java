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
public class SOAzurirajZaduzenje extends AbstractSO{

    Zaduzenje zaduzenje;

    public SOAzurirajZaduzenje(Zaduzenje zaduzenje) {
        this.zaduzenje = zaduzenje;
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
            db.azurirajObjekat(zaduzenje);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
            
        }
    }
}
