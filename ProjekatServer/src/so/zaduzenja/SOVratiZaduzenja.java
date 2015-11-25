/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaduzenja;

import domen.Clan;
import domen.IDomenskiObjekat;
import domen.Zaduzenje;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOVratiZaduzenja extends AbstractSO {

//    Clan clan;
    private List<IDomenskiObjekat> listaZaduzenja;
    public SOVratiZaduzenja() {
//        this.clan = clan;
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
            listaZaduzenja = db.vratiListuObjekata(new Zaduzenje());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
        }
    }
    
    public List<IDomenskiObjekat> vratiListu() {
        return listaZaduzenja;
    }

}
