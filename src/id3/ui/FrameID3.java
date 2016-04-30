/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3.ui;

import id3.Arbol;
import id3.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Paco
 */
public class FrameID3 extends javax.swing.JFrame {

    public Conexion conexion = new Conexion();

    /**
     * Creates new form FrameID3
     */
    public FrameID3() {
        initComponents();
        this.setTitle("ID3");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conexion.setSubname("//localhost:3306/id3");
        conexion.setUsuario("root");
        conexion.setContrasena("paco");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        mItemRealizarID3 = new javax.swing.JMenuItem();
        mItemConsultarID3 = new javax.swing.JMenuItem();
        mItemSalir = new javax.swing.JMenuItem();
        menuConfigurar = new javax.swing.JMenu();
        mItemParametrosConexion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Herramienta para calcular ID3 obteniendo la información de tablas en una base de datos.");

        jLabel2.setText("Hecho por: Francisco Gabriel Montiel Gómez");

        menuArchivo.setText("Archivo");

        mItemRealizarID3.setText("Realizar ID3");
        mItemRealizarID3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemRealizarID3ActionPerformed(evt);
            }
        });
        menuArchivo.add(mItemRealizarID3);

        mItemConsultarID3.setText("ConsultarID3");
        mItemConsultarID3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConsultarID3ActionPerformed(evt);
            }
        });
        menuArchivo.add(mItemConsultarID3);

        mItemSalir.setText("Salir");
        mItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(mItemSalir);

        jMenuBar1.add(menuArchivo);

        menuConfigurar.setText("Configurar");

        mItemParametrosConexion.setText("Conexion DB");
        mItemParametrosConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemParametrosConexionActionPerformed(evt);
            }
        });
        menuConfigurar.add(mItemParametrosConexion);

        jMenuBar1.add(menuConfigurar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(168, 168, 168))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_mItemSalirActionPerformed

    private void mItemConsultarID3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemConsultarID3ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        Arbol arbol = null;
        fileChooser.setDialogTitle("Especifica un archivo para abrir");
        FileNameExtensionFilter arbolFilter = new FileNameExtensionFilter("Arbol Paco", "arbPaco");
        fileChooser.setFileFilter(arbolFilter);
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(fileToSave.getAbsolutePath()));
            } catch (IOException e) {
                System.out.println("There was a problem opening the file: " + e);
            }
            try {
                arbol = (Arbol) inputStream.readObject();
                inputStream.close();
            } catch (Exception e) {
                System.out.println("There was an issue reading from the file: " + e);
                System.exit(0);
            }
            System.out.println("Arbol leido: " + arbol);
            FramePaintTree dibujarArbol = new FramePaintTree();
            PnlPaintTree pnl = new PnlPaintTree();
            pnl.arbol = arbol;
            dibujarArbol.arbol = arbol;
            dibujarArbol.setTitle("Arbol : " + fileToSave.getName());
            dibujarArbol.setContentPane(pnl);
            dibujarArbol.setVisible(true);
        }
    }//GEN-LAST:event_mItemConsultarID3ActionPerformed

    private void mItemRealizarID3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemRealizarID3ActionPerformed
        PnlPrepareID3 pnl = new PnlPrepareID3();
        pnl.frame = this;
        pnl.conexion = conexion.getConexion();
        pnl.subname = conexion.getSubname();
        pnl.password = conexion.getContrasena();
        pnl.usuario = conexion.getUsuario();
        pnl.prepareLists();
        this.setContentPane(pnl);
    }//GEN-LAST:event_mItemRealizarID3ActionPerformed

    private void mItemParametrosConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemParametrosConexionActionPerformed
        PnlConexionDB pnl = new PnlConexionDB();
        pnl.frame = this;
        pnl.setTextos();
        this.setContentPane(pnl);
        this.revalidate();
    }//GEN-LAST:event_mItemParametrosConexionActionPerformed

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
            java.util.logging.Logger.getLogger(FrameID3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameID3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameID3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameID3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameID3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mItemConsultarID3;
    private javax.swing.JMenuItem mItemParametrosConexion;
    private javax.swing.JMenuItem mItemRealizarID3;
    private javax.swing.JMenuItem mItemSalir;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuConfigurar;
    // End of variables declaration//GEN-END:variables
}
