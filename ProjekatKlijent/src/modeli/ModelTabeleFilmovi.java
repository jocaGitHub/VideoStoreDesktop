/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Film;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joca
 */
public class ModelTabeleFilmovi extends AbstractTableModel {

    private List<Film> listaFilmova;

    public ModelTabeleFilmovi() {
        listaFilmova = new ArrayList<>();
    }

    public ModelTabeleFilmovi(List<Film> listaFilmova) {
        this.listaFilmova = listaFilmova;
    }

    @Override
    public int getRowCount() {
        return listaFilmova.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Film k = listaFilmova.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getFilmID();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getZanr();
            case 3:
                return k.getGodinaSnimanja();
            case 4:
                return k.getBr();
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "film id";
            case 1:
                return "naziv filma";
            case 2:
                return "zanr";
            case 3:
                return "godina snimanja";
            case 4:
                return "broj raspolozivih kopija";

            default:
                return "N/A";

        }
    }

    public void dodajFilm(Film z) {
        listaFilmova.add(z);
        fireTableDataChanged();
    }

    public void setListaFilm(List<Film> listaFilmova) {
        this.listaFilmova = listaFilmova;
        fireTableDataChanged();
    }

    public List<Film> vratiListu() {
        return listaFilmova;
    }

    public Film vratiFilm(int rbr) {
        return listaFilmova.get(rbr);
    }

    public void osvezi() {
        fireTableDataChanged();
    }
}
