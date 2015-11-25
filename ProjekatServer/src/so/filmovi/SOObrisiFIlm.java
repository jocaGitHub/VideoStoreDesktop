/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.filmovi;

import domen.Film;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOObrisiFIlm extends AbstractSO{
    Film film;

    public SOObrisiFIlm(Film film) {
        this.film = film;
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
            db.obrisiObjekat(film);
        } catch (Exception ex) {
            throw new SistemOperationException(ex.getMessage());
        }
    }
}
