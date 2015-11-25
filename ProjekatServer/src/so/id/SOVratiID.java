/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.id;

import domen.IDomenskiObjekat;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOVratiID extends AbstractSO{
    String id;
    IDomenskiObjekat ido;
    public SOVratiID(IDomenskiObjekat s) {
        ido = s;
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
            id = db.pronadjiNajveci(ido) + "";
        } catch (Exception e) {
            e.printStackTrace();
            throw new SistemOperationException(e.getMessage());
        }
        
    }
    
    public String vratiID(){
        return id;
    }
    
}
