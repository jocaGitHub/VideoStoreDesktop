/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joca
 */
public class Mesto implements IDomenskiObjekat {

    private String mestoID;
    private int ptt;
    private String naziv;

    public Mesto() {

    }

    public Mesto(String mestoID, int ptt, String naziv) {
        this.mestoID = mestoID;
        this.ptt = ptt;
        this.naziv = naziv;
    }

    public long getPtt() {
        return ptt;
    }

    public void setPtt(int ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Mesto) {
            Mesto mesto = (Mesto) obj;
            if (getPtt() == mesto.getPtt()) {
                return true;
            }
        }
        return false;
    }

    public String getMestoID() {
        return mestoID;
    }

    public void setMestoID(String mestoID) {
        this.mestoID = mestoID;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + mestoID + "'," + ptt + ", '" + naziv + "'";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuNaOsnovuRS(ResultSet rs) {

        List<IDomenskiObjekat> objekti = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("mestoid");
                int ptt = rs.getInt("ptt");
                String naziv = rs.getString("naziv");
                IDomenskiObjekat mesto = new Mesto(id, ptt, naziv);
                objekti.add(mesto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objekti;

    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "mestoid = '" + mestoID + "', ptt = " + ptt + ", naziv = '" + naziv + "'";
    }

    @Override
    public String vratiIDOObjekta() {
        return "mestoid = '" + mestoID + "'";
    }

    @Override
    public IDomenskiObjekat vratiObjekatNaOsnovuRS(ResultSet rs) throws SQLException {
        Mesto m = null;
        if (rs.next()) {
            m = new Mesto();
            m.setMestoID(rs.getString("mestoid"));
            m.setPtt(rs.getInt("ptt"));
            m.setNaziv(rs.getString("naziv"));
        }
        rs.close();
        return m;

    }
}

