/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Osoba;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joca
 */
public class ModelTabeleOsoba extends AbstractTableModel {

    private List<Osoba> listaOsoba;

    public ModelTabeleOsoba() {
        listaOsoba = new ArrayList<>();
    }

    public ModelTabeleOsoba(List<Osoba> listaOsoba) {
        this.listaOsoba = listaOsoba;
    }

    @Override
    public int getRowCount() {
        return listaOsoba.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Osoba k = listaOsoba.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getIme();
            case 1:
                return k.getPrezime();
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
            default:
                return "N/A";

        }
    }

    public void dodajOsobu(Osoba z) {
        listaOsoba.add(z);
        fireTableDataChanged();
    }

    public void setListaOsoba(List<Osoba> listaOsoba) {
        this.listaOsoba = listaOsoba;
        fireTableDataChanged();
    }

    public List<Osoba> vratiListu() {
        return listaOsoba;
    }

    public Osoba vratiOsobu(int rbr) {
        return listaOsoba.get(rbr);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Osoba o = listaOsoba.get(rowIndex);
        switch (columnIndex) {
            case 0:
                o.setIme((String) aValue);
                break;
            case 1:
                o.setPrezime((String) aValue);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
}
