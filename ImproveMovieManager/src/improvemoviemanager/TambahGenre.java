/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Roni
 */
public class TambahGenre extends javax.swing.JFrame {

    /**
     * Creates new form TambahGenre
     */
    public TambahGenre() {
        initComponents();
        if(Session.isStatus()){
            lblUsername.setText("Selamat datang, " + Session.getUsername());
            btnLogout.setVisible(true);
        } else {
            btnLogout.setVisible(false);
        }
        showAllGenre();
    }
    
    public void showAllGenre(){
        String sql = "SELECT * FROM Genre";
        
        Map<Integer, JButton> btnGenre = new HashMap<Integer, JButton>();
        jPanel2.setLayout(new WrapLayout());
        
        try{
            Connection conn = Koneksi.getConnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");                
                String genre = rs.getString("genre");                
                btnGenre.put(id, new JButton(genre){
                    {
                        setSize(1450, 210);
                        setMaximumSize(getSize());
                    }
                });
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //printing
        for(int key : btnGenre.keySet()){
            btnGenre.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
            btnGenre.get(key).setHorizontalTextPosition(SwingConstants.LEFT);
            
            jPanel2.add(btnGenre.get(key));
            
            btnGenre.get(key).addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGenreActionPerformed(evt, key);
                }
            });
        }
    }
    
    private void btnGenreActionPerformed(java.awt.event.ActionEvent evt, int id){
        String sql = "SELECT * FROM Genre WHERE genre = ? ";        
        String genre = null;
        Connection conn = Koneksi.getConnect();
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, id);           
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
           genre = rs.getString(genre);
           }           
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        txtEditGenre.setText(genre);
        
    }
    //cek apakah genre sudah ada, true belum, false sudah
    public boolean checkGenre(String genre){
        String sql = "SELECT * FROM Genre WHERE genre = ? ";
        int count = 0;
        Connection conn = Koneksi.getConnect();
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, genre);           
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               count++;
           }
           if(count == 0){
               return true;
           } else {
               return false;
           }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    //menambahkan genre baru
    public void tambahGenre(String newGenre){
        String sql = "INSERT INTO Genre (genre) VALUES(?)";            
            Connection conn = Koneksi.getConnect();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newGenre);                
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(jPanel2,newGenre + " berhasil ditambahkan");                
                // coba refresh not work!
                jPanel2.validate();
                jPanel2.repaint();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNewGenre = new javax.swing.JTextField();
        btnTambahGenre = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEditGenre = new javax.swing.JTextField();
        btnEditGenre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsername.setText("Alert");

        btnLogout.setText("Logout");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 743, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        jLabel1.setText("Tambah Genre");

        btnTambahGenre.setText("Tambah");
        btnTambahGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahGenreActionPerformed(evt);
            }
        });

        jLabel2.setText("Semua Genre");

        jLabel3.setText("Edit Genre");

        btnEditGenre.setText("Edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLogout))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNewGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTambahGenre)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtEditGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahGenre)
                    .addComponent(txtEditGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditGenre))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahGenreActionPerformed
        // TODO add your handling code here:
        String newGenre = txtNewGenre.getText();
        if(newGenre==""){
            JOptionPane.showMessageDialog(null, "Anda harus memasukkan genre telebih dahulu");
        }else if(newGenre.length()<4){
            JOptionPane.showMessageDialog(null, "minimal 4 kata");            
        }else if(newGenre.length()>20){
            JOptionPane.showMessageDialog(null, "Genre Terlalu Panjang");
        }else{            
            if(checkGenre(newGenre)==true){
                tambahGenre(newGenre);                                                
            }else{
                JOptionPane.showMessageDialog(null, "Genre yang sama telah ada");
            }
        }
    }//GEN-LAST:event_btnTambahGenreActionPerformed

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
            java.util.logging.Logger.getLogger(TambahGenre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahGenre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahGenre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahGenre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahGenre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditGenre;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnTambahGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtEditGenre;
    private javax.swing.JTextField txtNewGenre;
    // End of variables declaration//GEN-END:variables
}
