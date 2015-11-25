/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clanovi;

import domen.Clan;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOObrisiClana extends AbstractSO {
   Clan clan;

    public SOObrisiClana(Clan clan) {
        this.clan = clan;
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
//            db.obrisiClana(clan);
            db.obrisiObjekat(clan);
        } catch (Exception ex) {
            throw new SistemOperationException(ex.getMessage());
        }
    }
    
}
