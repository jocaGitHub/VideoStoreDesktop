/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clanovi;

import domen.Clan;
import domen.IDomenskiObjekat;
import domen.Mesto;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOVratiListuClanova extends AbstractSO {

    List<IDomenskiObjekat> listaClanova;

    @Override
    protected void izvrsiValidaciju() throws ValidationException {

    }

    @Override
    protected void proveriPreduslov() throws PreConditionException {

    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            listaClanova = db.vratiListuObjekata(new Clan());
            System.out.println("so vratil listu clanova"+listaClanova.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
        }
    }

    public List<IDomenskiObjekat> vratiListu() {
        return listaClanova;
    }

}
