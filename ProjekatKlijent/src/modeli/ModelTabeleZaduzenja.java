/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Zaduzenje;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joca
 */
public class ModelTabeleZaduzenja extends AbstractTableModel {

    private List<Zaduzenje> listaZaduzenja;

    public ModelTabeleZaduzenja() {
        listaZaduzenja = new ArrayList<>();
    }

    public ModelTabeleZaduzenja(List<Zaduzenje> listaZaduzenja) {
        this.listaZaduzenja = listaZaduzenja;
    }

    @Override
    public int getRowCount() {
        return listaZaduzenja.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaduzenje z = listaZaduzenja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getZaduzenjeID();
            case 1:
                return z.getKopija().getKopijaID();
            case 2:
                return z.getKopija().getFilm().getNaziv();
            case 3:
                return z.getDatumZaduzenja();
            case 4:
                return z.isStatusZaduzenja();
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "zaduzenje id";
            case 1:
                return "kopija id";
            case 2:
                return "naziv filma";
            case 3:
                return "datum zaduzenja";
            case 4:
                return "status zaduzenja";
            default:
                return "N/A";

        }
    }

    public void dodajZaduzenje(Zaduzenje z) {
        listaZaduzenja.add(z);
        fireTableDataChanged();
    }

    public void setListaZaduzenja(List<Zaduzenje> listaZaduzenja) {
        this.listaZaduzenja = listaZaduzenja;
        fireTableDataChanged();
    }

    public List<Zaduzenje> vratiListu() {
        return listaZaduzenja;
    }
    
    public Zaduzenje vratiZaduzenje(int rbr){
        return listaZaduzenja.get(rbr);
    }

    public void obrisiZaduzenje(int rbr) {
        listaZaduzenja.remove(rbr);
        fireTableDataChanged();
    }
}
