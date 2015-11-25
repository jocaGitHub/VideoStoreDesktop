/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Kopija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joca
 */
public class ModelTabeleKopija extends AbstractTableModel{
     private List<Kopija> listaKopija;

    public ModelTabeleKopija() {
        listaKopija = new ArrayList<>();
    }

    public ModelTabeleKopija(List<Kopija> listaKopija) {
        this.listaKopija = listaKopija;
    }

    @Override
    public int getRowCount() {
        return listaKopija.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kopija k = listaKopija.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKopijaID();
            case 1:
                return k.getRbr();
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "kopija id";
            case 1:
                return "rbr";
            default:
                return "N/A";

        }
    }

    public void dodajKopiju(Kopija z) {
        listaKopija.add(z);
        fireTableDataChanged();
    }

    public void setListaKopija(List<Kopija> listaKopija) {
        this.listaKopija = listaKopija;
        fireTableDataChanged();
    }

    public List<Kopija> vratiListu() {
        return listaKopija;
    }
    
    public Kopija vratiKopiju(int rbr){
        return listaKopija.get(rbr);
    }

    public void obrisiKopiju(int rbr) {
        listaKopija.remove(rbr);
        fireTableDataChanged();
    }
}
