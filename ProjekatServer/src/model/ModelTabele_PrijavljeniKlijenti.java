/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import niti.NitKlijent;

/**
 *
 * @author Dario
 */
public class ModelTabele_PrijavljeniKlijenti extends AbstractTableModel {

    private List<NitKlijent> listaKlijenata;

    public ModelTabele_PrijavljeniKlijenti(List<NitKlijent> li) {
        listaKlijenata = li;
    }

    @Override
    public int getRowCount() {
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaKlijenata.get(rowIndex).getKorisnickoIme();
            case 1:
                return "aktivan";
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "korisnicko ime";
            case 1:
                return "status";
            default:
                return "N/A";
        }
    }

}
