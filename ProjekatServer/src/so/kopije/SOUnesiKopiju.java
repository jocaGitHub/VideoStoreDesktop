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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Dario
 */
public class SOUnesiKopiju extends AbstractSO {

    Kopija kopija;

    public SOUnesiKopiju(Kopija kopija) {
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
            db.azurirajObjekat(kopija);
        } catch (SQLException ex) {
            throw new SistemOperationException(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SOUnesiKopiju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
