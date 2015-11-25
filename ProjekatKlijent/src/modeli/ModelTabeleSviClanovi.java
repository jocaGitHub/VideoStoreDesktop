/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Clan;
import domen.KolekcijaClanova;
import domen.Mesto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author student1
 */
public class ModelTabeleSviClanovi extends AbstractTableModel {

    List<Clan> listaClanova;

    public ModelTabeleSviClanovi() {
        listaClanova = new ArrayList<>();
    }

    public ModelTabeleSviClanovi(List<Clan> listaClanova) {
        this.listaClanova = listaClanova;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ClanID";
            case 1:
                return "Ime";
            case 2:
                return "Prezime";
            case 3:
                return "JMBG";
            case 4:
                return "Telefon";
            default:
                return "greska";
        }
    }

    @Override
    public int getRowCount() {
        return listaClanova.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clan c = listaClanova.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getClanID();
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getJmbg();
            case 4:
                return c.getTelefon();
            default:
                return "greska";
        }
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Clan c = listaClanova.get(rowIndex);
//        switch (columnIndex) {
//            case 0:
//                c.setClanID(aValue.toString());
//                break;
//            case 1:
//                c.setIme(aValue.toString());
//                break;
//            case 2:
//                c.setPrezime(aValue.toString());
//                break;
//            case 3:
//                c.setJmbg(aValue.toString());
//                break;
//            case 4:
//                c.setUlicaIBroj(aValue.toString());
//                break;
//        }
//    }

    public void obrisi(int red) {
        listaClanova.remove(red);
        fireTableDataChanged();
    }

//    public void dodajNoviRed(Clan c) {
//        listaClanova.add(c);
//        fireTableDataChanged();
//    }

    public List<Clan> vratiListuClanova() {
        return listaClanova;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
    }
    
    public Clan vratiClana(int i){
        return listaClanova.get(i);
    }
    
}
