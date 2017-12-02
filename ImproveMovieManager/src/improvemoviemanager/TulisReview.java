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

        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblReviewTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnDeleteReview = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtContent.setColumns(20);
        txtContent.setLineWrap(true);
        txtContent.setRows(5);
        txtContent.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtContent);

        jPanel1.setBackground(new java.awt.Color(67, 67, 67));

        lblUsername.setBackground(new java.awt.Color(254, 254, 254));
        lblUsername.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(254, 254, 254));
        lblUsername.setText("Alert");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_rsz_2logo.png"))); // NOI18N

        lblReviewTitle.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblReviewTitle.setForeground(new java.awt.Color(254, 254, 254));
        lblReviewTitle.setText("Tulis Review");

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
                            .addComponent(lblReviewTitle, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(293, Short.MAX_VALUE))
                    .addComponent(jSeparator1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblReviewTitle)
                        .addGap(6, 6, 6))))
        );

        jPanel2.setBackground(new java.awt.Color(67, 67, 67));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(67, 67, 67));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(67, 67, 67));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(67, 67, 67));

        btnDeleteReview.setText("Hapus");
        btnDeleteReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteReviewActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteReview)
                .addGap(124, 124, 124)
                .addComponent(btnBatal)
                .addGap(18, 18, 18)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteReview)
                    .addComponent(btnBatal)
                    .addComponent(btnSimpan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblReviewTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextArea txtContent;
    // End of variables declaration//GEN-END:variables
}
