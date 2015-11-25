/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomenskiObjekat;
import domen.Radnici;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import java.util.List;

/**
 *
 * @author Joca
 */
public class SOVratiListuRadnika extends AbstractSO{
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
            lista = db.vratiListuObjekata(new Radnici());
            System.out.println("lista radnika u so"+lista.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
        }
    }

    public List<IDomenskiObjekat> vratiListu() {
        return lista;
    }
}
