/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import db.DBKonekcija;
import forme.FrmServer;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Joca
 */
public class KontrolerForme {

    FrmServer glavna;

    public KontrolerForme(FrmServer glavna) {
        this.glavna = glavna;
    }

    public void srediFormu() {

    }

    public static void testirajKonekciju(JTextField naziv, JTextField username, JTextField password, JComboBox tipCmb, JLabel status) {
        if (tipCmb.getSelectedIndex() == 0) {
            status.setText("Izaberite tip baze");
        } else {
            if (naziv.getText().isEmpty()) {
                status.setText("Unesite naziv baze podataka");
            } else {
                String tip = tipCmb.getSelectedItem().toString();
                if (tip.equals("MySQL")) {
                    DBKonekcija db = new DBKonekcija();
                    try {
                        db.otvoriKonekciju("jdbc:mysql://localhost:3306/" + naziv.getText().trim(), username.getText().trim(), password.getText().trim());
                        db.zatvoriKonekciju();
                        status.setText("Uspesna konekcija");
                    } catch (ClassNotFoundException ex) {
                        status.setText("Neuspesna konekcija, drajver nije ucitan.");
                    } catch (SQLException ex) {
                        status.setText("Neuspesna konekcija, " + ex.getMessage());
                    }
                }
            }
        }
    }
}
