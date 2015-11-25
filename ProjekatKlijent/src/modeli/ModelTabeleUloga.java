/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Uloga;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joca
 */
public class ModelTabeleUloga extends AbstractTableModel {

    private List<Uloga> listaUloga;

    public ModelTabeleUloga() {
        listaUloga = new ArrayList<>();
    }

    public ModelTabeleUloga(List<Uloga> listaUloga) {
        this.listaUloga = listaUloga;
    }

    @Override
    public int getRowCount() {
        return listaUloga.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Uloga k = listaUloga.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getOsoba().getIme();
            case 1:
                return k.getOsoba().getPrezime();
            case 2:
                return k.getNazivUloge();
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ime";
            case 1:
                return "prezime";
            case 2:
                return "naziv uloge";
            default:
                return "N/A";

        }
    }

    public void dodajUlogu(Uloga z) {
        listaUloga.add(z);
        fireTableDataChanged();
    }

    public void setListaUloga(List<Uloga> listaUloga) {
        this.listaUloga = listaUloga;
        fireTableDataChanged();
    }

    public List<Uloga> vratiListu() {
        return listaUloga;
    }

    public Uloga vratiUlogu(int rbr) {
        return listaUloga.get(rbr);
    }

    public void obrisiUlogu(int index) {
        listaUloga.remove(index);
        fireTableDataChanged();
    }

}
