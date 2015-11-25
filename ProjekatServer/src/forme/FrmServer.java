/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.awt.CardLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import kontroler.KontrolerForme;
import model.ModelTabele_PrijavljeniKlijenti;
import niti.NitKlijent;
import niti.NitOsveziTabelu;
import niti.NitServer;

/**
 *
 * @author Joca
 */
public class FrmServer extends javax.swing.JFrame {

    KontrolerForme kontrolerForme;
    NitServer nit;
    boolean pokrenut;

    /**
     * Creates new form FrmServer
     */
    public FrmServer() {
        initComponents();
        pokrenut = false;
        jpnlPodesavanjeBazePodataka1.postaviFormu(this);
        kontrolerForme = new KontrolerForme(this);
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpnlPodesavanjeBazePodataka1 = new forme.JpnlPodesavanjeBazePodataka();
        jpnlServer = new javax.swing.JPanel();
        jbtnPokreni = new javax.swing.JButton();
        jbtnZaustavi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.CardLayout());
        jPanel1.add(jpnlPodesavanjeBazePodataka1, "panelPodesavanjeBazePodataka");

        jbtnPokreni.setText("Pokreni");
        jbtnPokreni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPokreniActionPerformed(evt);
            }
        });

        jbtnZaustavi.setText("Zaustavi");
        jbtnZaustavi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnZaustaviActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jpnlServerLayout = new javax.swing.GroupLayout(jpnlServer);
        jpnlServer.setLayout(jpnlServerLayout);
        jpnlServerLayout.setHorizontalGroup(
            jpnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlServerLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jbtnPokreni)
                .addGap(18, 18, 18)
                .addComponent(jbtnZaustavi)
                .addGap(129, 129, 129)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jpnlServerLayout.setVerticalGroup(
            jpnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlServerLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jpnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPokreni)
                    .addComponent(jbtnZaustavi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlServerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        jPanel1.add(jpnlServer, "panelServer");

        jMenu1.setText("Opcije");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Podesavanje baze podataka");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Upravljanje serverom");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnZaustaviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnZaustaviActionPerformed
        // TODO add your handling code here:
        
        for (NitKlijent nk : NitServer.getListaKlijenata()) {
            try {
                nk.ugasi();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        nit.ugasiServerSoket();
        nit = null;

        jbtnZaustavi.setVisible(false);
        jbtnPokreni.setVisible(true);
        
        pokrenut = false;
        
        jMenuItem1.setEnabled(true);
    }//GEN-LAST:event_jbtnZaustaviActionPerformed

    private void jbtnPokreniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPokreniActionPerformed
        // TODO add your handling code here:
        try {
            nit = new NitServer();
            nit.start();
            
            
            
            jbtnZaustavi.setVisible(true);
            jbtnPokreni.setVisible(false);
            pokrenut = true;
            jMenuItem1.setEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jbtnPokreniActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "panelPodesavanjeBazePodataka");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "panelServer");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnPokreni;
    private javax.swing.JButton jbtnZaustavi;
    private forme.JpnlPodesavanjeBazePodataka jpnlPodesavanjeBazePodataka1;
    private javax.swing.JPanel jpnlServer;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        if (Konfiguracija.vratiInstancu().vratiPodesenostBaze().equals("f")) {
            CardLayout cl = (CardLayout) jPanel1.getLayout();
            cl.show(jPanel1, "panelPodesavanjeBazePodataka");
        } else {
            promeniPanel(1);
        }
    }

    public void promeniPanel(int panel) {
        if (panel == 1) {
            CardLayout cl = (CardLayout) jPanel1.getLayout();
            cl.show(jPanel1, "panelServer");
            
            if(pokrenut == false){
                jbtnPokreni.setVisible(true);
                jbtnZaustavi.setVisible(false);
            }
            
            jTable1.setModel(new ModelTabele_PrijavljeniKlijenti(NitServer.getListaKlijenata()));
            NitOsveziTabelu not = new NitOsveziTabelu(jTable1);
            not.start();
            
            
        }
    }
}
