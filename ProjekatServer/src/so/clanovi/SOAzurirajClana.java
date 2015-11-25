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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOAzurirajClana extends AbstractSO {

    Clan clan;
    public SOAzurirajClana(Clan clan) {
        this.clan = clan;
    }

    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
        if (clan.getIme().isEmpty()) {
            throw new ValidationException("Sistem ne moze da zapamti clana, niste uneli ime");
        }
        if (clan.getPrezime().isEmpty()) {
            throw new ValidationException("unesite prezime");
        }
        if (clan.getJmbg().isEmpty()) {
            throw new ValidationException("unesite jmbg");
        }
        if (clan.getTelefon().isEmpty()) {
            throw new ValidationException("unesite telefon");
        }
        if (clan.getUlicaIBroj().isEmpty()) {
            throw new ValidationException("unesite ulicu i broj");
        }
        
         for (int i = 0; i < clan.getIme().length(); i++) {
            if (Character.isDigit(clan.getIme().charAt(i))) {
                throw new ValidationException("ne mozete uneti broj u polje imena");
            } else {
            }
        }
        for (int i = 0; i < clan.getPrezime().length(); i++) {
            if (Character.isDigit(clan.getPrezime().charAt(i))) {
                throw new ValidationException("ne mozete uneti broj u polje prezimena");
            } else {
            }
        }
        for (int i = 0; i < clan.getJmbg().length(); i++) {
            if (Character.isDigit(clan.getJmbg().charAt(i))) {
            } else {
                throw new ValidationException("ne mozete uneti slovo u polje jmbg");
            }
        }
        for (int i = 0; i < clan.getTelefon().length(); i++) {
            if (Character.isDigit(clan.getTelefon().charAt(i))) {
            } else {
                throw new ValidationException("ne mozete uneti slovo u polje telefon");
            }
        }
        
    }

    @Override
    protected void proveriPreduslov() throws PreConditionException {
    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.azurirajObjekat(clan);
        } catch (SQLException ex) {
            throw new SistemOperationException(ex.getMessage());
        }
    }

}
