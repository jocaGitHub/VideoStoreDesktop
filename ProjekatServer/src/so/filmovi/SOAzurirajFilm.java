/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.filmovi;

import domen.Film;
import domen.Uloga;
import exception.PreConditionException;
import exception.SistemOperationException;
import exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Joca
 */
public class SOAzurirajFilm extends AbstractSO{
    Film film;

    public SOAzurirajFilm(Film film) {
        this.film = film;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        if (film.getNaziv().isEmpty()) {
            throw new ValidationException("unesite naziv");
        }
        if (film.getGodinaSnimanja()==0 || film.getGodinaSnimanja() > 9999 || film.getGodinaSnimanja()< 1000) {
            throw new ValidationException("unesite godinu snimanja u pravom formatu");
        }
        for (Uloga u1 : film.getListaUloga()) {
            int br = 0;
            for (Uloga u2 : film.getListaUloga()) {
                if(u1.equals(u2)){
                    br+=1;
                }
            }
            if(br>1){
                throw new ValidationException("ne mozete uneti dve iste uloge");
            }
            
        }
        
        
        
    }

    @Override
    protected void proveriPreduslov() throws PreConditionException {
    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.azurirajObjekat(film);
            if(film.getListaUloga().size()>0){
                for (Uloga u : film.getListaUloga()) {
                    db.upisiObjekat(u);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SistemOperationException(ex.getMessage());
            
        }
    }
    
}
