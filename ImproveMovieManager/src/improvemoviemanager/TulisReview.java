/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author yongzari
 */
public class TulisReview extends javax.swing.JFrame {
    private String text, reviewer;
    private int idUser = Session.getId(), idMovie;
    private boolean isEditable = false;
    /**
     * Creates new form TulisReview
     */
    public TulisReview(int id, boolean isEditable) {
        initComponents();
        idMovie = id;
        this.isEditable = isEditable;
        if(this.isEditable){
            lblReviewTitle.setText("Edit Review");
            btnDeleteReview.setVisible(true);
            showReview();
        } else{
            btnDeleteReview.setVisible(false);
        }
    }

    public void showReview(){
        String sql = "SELECT content From Review WHERE id_movie = ? AND id_user = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idMovie);
            stmt.setInt(2, idUser);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                txtContent.setText(rs.getString("content"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void addReviewer(){
        String sql = "SELECT review_giver FROM Movie WHERE id = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idMovie);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                reviewer = rs.getString("review_giver");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        reviewer = reviewer + "," + idUser;
        
        sql = "UPDATE Movie SET review_giver = ? WHERE id = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, reviewer);
            stmt.setInt(2, idMovie);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void simpanReview(){
        String sql = "INSERT INTO Review(id_movie, id_user, content) VALUES(?, ?, ?)";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idMovie);
            stmt.setInt(2, idUser);
            stmt.setString(3, txtContent.getText());
            stmt.executeUpdate();
            addReviewer();
            JOptionPane.showMessageDialog(this, "Review berhasil ditambahkan!");
            new DetailFilm().showDetail(idMovie, 1);
            this.dispose();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void editReview(){
        String sql = "UPDATE Review SET content = ? WHERE id_movie = ? AND id_user = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtContent.getText());
            stmt.setInt(2, idMovie);
            stmt.setInt(3, idUser);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Review berhasil diedit!");
            new DetailFilm().showDetail(idMovie, 1);
            this.dispose();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void updateReviewer(){
        String sql = "SELECT review_giver FROM Movie WHERE id = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idMovie);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                reviewer = rs.getString("review_giver");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        String[] tempReviewer = reviewer.split(",");
        reviewer = "0";
        for(int i=0;i<tempReviewer.length;i++){
            if(Integer.parseInt(tempReviewer[i])==idUser){
                tempReviewer[i] = "0";
            }
            if(i == 0){
                continue;
            }else{
                reviewer = reviewer + "," + tempReviewer[i];
            }
        }
        
        sql = "UPDATE Movie SET review_giver = ? WHERE id = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, reviewer);
            stmt.setInt(2, idMovie);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteReview(){
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Confirm delete", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            String sql = "DELETE FROM Review WHERE id_movie = ? AND id_user = ?";
            try{
                Connection conn = Koneksi.getConnect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idMovie);
                stmt.setInt(2, idUser);
                stmt.executeUpdate();
                updateReviewer();
                JOptionPane.showMessageDialog(this, "Review berhasil dihapus!");
                new DetailFilm().showDetail(idMovie, 1);
                this.dispose();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
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

        lblReviewTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnDeleteReview = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblReviewTitle.setText("Tulis Review");

        txtContent.setColumns(20);
        txtContent.setLineWrap(true);
        txtContent.setRows(5);
        txtContent.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtContent);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnDeleteReview.setText("Hapus");
        btnDeleteReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteReviewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblReviewTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDeleteReview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReviewTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnDeleteReview))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        new DetailFilm().showDetail(idMovie, 1);
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(txtContent.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Review masih kosong!");
        }else if(isEditable){
            editReview();
        }else{
            simpanReview();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnDeleteReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteReviewActionPerformed
        // TODO add your handling code here:
        deleteReview();
    }//GEN-LAST:event_btnDeleteReviewActionPerformed

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
            java.util.logging.Logger.getLogger(TulisReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TulisReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TulisReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TulisReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TulisReview(0, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnDeleteReview;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReviewTitle;
    private javax.swing.JTextArea txtContent;
    // End of variables declaration//GEN-END:variables
}
