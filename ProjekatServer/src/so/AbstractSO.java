/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBKonekcija;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;

/**
 *
 * @author student1
 */
public abstract class AbstractSO {

    protected DBKonekcija db;

    public AbstractSO() {
        db = new DBKonekcija();
    }

    public void izvrsiOperaciju() throws Exception {
        otvoriKonekciju();
        try {
            izvrsiValidaciju();
            proveriPreduslov();
            izvrsiTransakciju();
            potvrdiTransakciju();
        } catch (ValidationException ve) {
            throw new Exception(ve.getMessage());
        } catch (PreConditionException pce) {
            throw new Exception(pce.getMessage());
        } catch (SistemOperationException soe) {
            ponistiTransakciju();
            throw new Exception(soe.getMessage());
        } 
//        potvrdiTransakciju();
//        ponistiTransakciju();
        finally{
            zatvoriKonekciju();
        }
    }

    private void otvoriKonekciju() {
        db.otvoriKonekciju();
    }

    private void zatvoriKonekciju() {
        db.zatvoriKonekciju();
    }

    protected abstract void izvrsiValidaciju() throws ValidationException;

    protected abstract void proveriPreduslov() throws PreConditionException;

    protected abstract void izvrsiTransakciju() throws SistemOperationException;

    private void potvrdiTransakciju() {
        db.potvrdiTransakciju();
    }

    private void ponistiTransakciju() {
        db.ponistiTransakciju();
    }

}
