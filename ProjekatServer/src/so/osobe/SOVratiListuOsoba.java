/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.osobe;

import domen.IDomenskiObjekat;
import domen.Osoba;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOVratiListuOsoba extends AbstractSO{

    List<IDomenskiObjekat> lista;
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
    }

    @Override
    protected void proveriPreduslov() throws PreConditionException {
    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
         try {
            lista = db.vratiListuObjekata(new Osoba());
            System.out.println("so vratil listu osoba"+lista.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
        }
    }
    
    public List<IDomenskiObjekat> vratiListu(){
        return lista;
    }
    
}
