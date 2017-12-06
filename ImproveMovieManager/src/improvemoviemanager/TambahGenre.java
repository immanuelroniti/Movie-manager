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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Roni
 */
public class TambahGenre extends javax.swing.JFrame {
    private int tempID;
    Map<Integer, JButton> btnGenre = new HashMap<Integer, JButton>();
    private boolean isDelete = false;
    private JFrame back;
    
    /**
     * Creates new form TambahGenre
     */
    public TambahGenre(boolean isDelete, JFrame back) {
        initComponents();
        this.isDelete = isDelete;
        
        if(Session.isStatus()){
            lblUsername.setText("Selamat datang, " + Session.getUsername());
        }
        
        if(isDelete){
            toggleDelete.setSelected(true);
            txtNewGenre.setEditable(false);
            btnTambahGenre.setEnabled(false);
            btnEditGenre.setEnabled(false);
        } else{
            toggleDelete.setSelected(false);
            txtNewGenre.setEditable(true);
            btnTambahGenre.setEnabled(true);
            btnEditGenre.setEnabled(true);
        }
        txtEditGenre.setEditable(false);
        showAllGenre();
        this.back = back;
    }
    
    public void showAllGenre(){
        String sql = "SELECT * FROM Genre";
        jPanel2.setLayout(new WrapLayout());
        
        try{
            Connection conn = Koneksi.getConnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                if(rs.getInt("id")==0){
                    continue;
                }
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
        
        showButton();
    }
    
    public void showButton(){
        //printing
        
        if(isDelete){
            toggleDelete.setText("Batal");
            for(int key : btnGenre.keySet()){
                btnGenre.get(key).setText(btnGenre.get(key).getText() + " (hapus)");
                btnGenre.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
                btnGenre.get(key).setHorizontalTextPosition(SwingConstants.LEFT);

                jPanel2.add(btnGenre.get(key));

                btnGenre.get(key).addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGenreActionPerformedDelete(evt, key);
                    }
                });
            }
        } else {
            toggleDelete.setText("Delete");
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
    }
    
    private void btnGenreActionPerformed(java.awt.event.ActionEvent evt, int id){
        String sql = "SELECT * FROM Genre WHERE id = ? ";        
        String genre = null;
        Connection conn = Koneksi.getConnect();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);           
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                genre = rs.getString("genre");
            }           
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        txtEditGenre.setEditable(true);
        txtEditGenre.setText(genre);
        tempID = id;
    }
    
    public void deleteGenreInMovie(int id, int index){
        String sql;
        if(index==1){
            sql = "UPDATE Movie SET genre1 = 0 WHERE id = ?";
            Connection conn = Koneksi.getConnect();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);   
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else if(index==2){
            sql = "UPDATE Movie SET genre2 = 0 WHERE id = ?";
            Connection conn = Koneksi.getConnect();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);   
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else if(index==3){
            sql = "UPDATE Movie SET genre3 = 0 WHERE id = ?";
            Connection conn = Koneksi.getConnect();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);   
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void checkMovieGenre(int id){
        String sql = "SELECT * FROM Movie";
        try{
            Connection conn = Koneksi.getConnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                if(rs.getInt("genre1")==id){
                    deleteGenreInMovie(rs.getInt("id"), 1);
                }else if(rs.getInt("genre2")==id){
                    deleteGenreInMovie(rs.getInt("id"), 2);
                }else if(rs.getInt("genre3")==id){
                    deleteGenreInMovie(rs.getInt("id"), 3);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void btnGenreActionPerformedDelete(java.awt.event.ActionEvent evt, int id){
        int confirm = JOptionPane.showConfirmDialog(jPanel2, "Apakah anda yakin?", "Confirm delete", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            checkMovieGenre(id);
            String sql = "DELETE FROM Genre WHERE id = ?";
            try{
                Connection conn = Koneksi.getConnect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
            
                JOptionPane.showMessageDialog(null, "Genre berhasil dihapus");
                new TambahGenre(true, this.back).setVisible(true);
                this.dispose();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    //cek apakah genre sudah ada, true belum, false sudah
    public boolean checkGenre(String genre){
        String sql = "SELECT * FROM Genre WHERE genre = ?";
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
            txtNewGenre.setText("");
            new TambahGenre(false, this.back).setVisible(true);
            this.dispose();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editGenre(String newGenre){
        String sql = "UPDATE Genre SET genre = ? WHERE id = ?";
        Connection conn = Koneksi.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newGenre);   
            stmt.setInt(2, tempID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(jPanel2,"Genre berhasil diedit"); 
            txtEditGenre.setText("");
            new TambahGenre(false, this.back).setVisible(true);
            this.dispose();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        toggleDelete = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNewGenre = new javax.swing.JTextField();
        btnTambahGenre = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtEditGenre = new javax.swing.JTextField();
        btnEditGenre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(67, 67, 67));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        jPanel1.setBackground(new java.awt.Color(67, 67, 67));

        lblUsername.setBackground(new java.awt.Color(254, 254, 254));
        lblUsername.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(254, 254, 254));
        lblUsername.setText("Alert");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        toggleDelete.setText("Hapus");
        toggleDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleDeleteActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_rsz_2logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Genre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toggleDelete))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                                .addComponent(btnBack)
                                .addGap(116, 116, 116)))
                        .addContainerGap())
                    .addComponent(jSeparator1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack)
                            .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(toggleDelete))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))))))
        );

        jPanel3.setBackground(new java.awt.Color(67, 67, 67));

        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Tambah Genre");

        btnTambahGenre.setText("Tambah");
        btnTambahGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahGenreActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Edit Genre (Klik salah satu genre untuk mengedit)");

        btnEditGenre.setText("Edit");
        btnEditGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditGenreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTambahGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNewGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEditGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEditGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNewGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtEditGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditGenre)
                    .addComponent(btnTambahGenre))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnEditGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditGenreActionPerformed
        // TODO add your handling code here:
        String newGenre = txtEditGenre.getText();
        if(newGenre==""){
            JOptionPane.showMessageDialog(null, "Anda harus memasukkan genre telebih dahulu");
        }else if(newGenre.length()<4){
            JOptionPane.showMessageDialog(null, "minimal 4 kata");            
        }else if(newGenre.length()>20){
            JOptionPane.showMessageDialog(null, "Genre Terlalu Panjang");
        }else{            
            if(checkGenre(newGenre)==true){
                editGenre(newGenre);                                               
            }else{
                JOptionPane.showMessageDialog(null, "Genre yang sama telah ada");
            }
        }
    }//GEN-LAST:event_btnEditGenreActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new HalamanAwal().setVisible(true);
        this.dispose();
        this.back.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void toggleDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleDeleteActionPerformed
        // TODO add your handling code here:
        if(toggleDelete.isSelected()){
            isDelete = true;
            new TambahGenre(true,this.back).setVisible(true);
            this.dispose();
        } else {
            isDelete = false;
            new TambahGenre(false,this.back).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_toggleDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEditGenre;
    private javax.swing.JButton btnTambahGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JToggleButton toggleDelete;
    private javax.swing.JTextField txtEditGenre;
    private javax.swing.JTextField txtNewGenre;
    // End of variables declaration//GEN-END:variables
}
