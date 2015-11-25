/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author student1
 */
public interface IDomenskiObjekat extends Serializable{

    public String vratiNazivTabele();

    public String vratiVrednostiZaInsert();

    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs);
    
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException;

    public String vratiVrednostiZaAzuriranje();

    public String vratiIDOObjekta();

}
