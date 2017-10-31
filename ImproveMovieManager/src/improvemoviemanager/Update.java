/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author yongzari
 */
public class Update extends javax.swing.JFrame {

    private String judul, genre, sutradara, penulis, produser, deskripsi, gambarLoc = null, trailerLoc = null;
    private int id, tahun, durasi, ratingUsia;
    
    /**
     * Creates new form Update
     */
    public Update() {
        initComponents();
        if(Session.isStatus()){
            lblUsername.setText("Selamat datang, " + Session.getUsername());
            btnLogout.setVisible(true);
        } else {
            btnLogout.setVisible(false);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTahun = new javax.swing.JTextField();
        txtDurasi = new javax.swing.JTextField();
        txtSutradara = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        txtProduser = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        btnGambar = new javax.swing.JButton();
        btnTrailer = new javax.swing.JButton();
        lblGambar = new javax.swing.JLabel();
        lblTrailer = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        lblShowGambar = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        cbGenre1 = new javax.swing.JComboBox<>();
        cbGenre2 = new javax.swing.JComboBox<>();
        cbGenre3 = new javax.swing.JComboBox<>();
        cbRatingUsia = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tambah film"));

        jLabel1.setText("Judul");

        jLabel2.setText("Tahun");

        jLabel3.setText("Genre");

        jLabel4.setText("Durasi");

        jLabel5.setText("Sutradara");

        jLabel6.setText("Penulis");

        jLabel7.setText("Produser");

        jLabel8.setText("Rating Usia");

        jLabel9.setText("Deskripsi");

        jLabel10.setText("Gambar");

        jLabel11.setText("Trailer");

        txtDeskripsi.setColumns(20);
        txtDeskripsi.setRows(5);
        jScrollPane1.setViewportView(txtDeskripsi);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnGambar.setText("Pilih Gambar");
        btnGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGambarActionPerformed(evt);
            }
        });

        btnTrailer.setText("Pilih Video");
        btnTrailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrailerActionPerformed(evt);
            }
        });

        lblGambar.setText("File path:");

        lblTrailer.setText("File path:");

        lblShowGambar.setText("Preview gambar");
        lblShowGambar.setToolTipText("");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cbGenre1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genre 1", "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western" }));

        cbGenre2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genre 2", "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western" }));

        cbGenre3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genre 3", "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western" }));

        cbRatingUsia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MPAA Age Rating", "G", "PG", "PG-13", "R", "NC-17" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbGenre1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbGenre2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbGenre3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRatingUsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGambar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(btnTrailer))
                            .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblShowGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(108, 108, 108))
                                    .addComponent(lblTrailer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSimpan))))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDurasi, txtJudul, txtPenulis, txtProduser, txtSutradara, txtTahun});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbGenre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtProduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbRatingUsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(btnGambar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGambar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblShowGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrailer)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTrailer)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnCancel))))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLogout.setText("Logout");

        lblUsername.setText("Selamat datang, Guest");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(lblUsername)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showImg(String imageLoc){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(imageLoc));
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        Image newImg = img.getScaledInstance(lblShowGambar.getWidth(), lblShowGambar.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(newImg);
        lblShowGambar.setIcon(imgIcon);
    }
    
    public void showDetail(int id){
        this.id = id;
        this.setVisible(true);
        
        String sql = "SELECT * FROM Movie WHERE id = ?";
        
        try{
            Connection conn = Koneksi.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                judul = rs.getString("judul");
                tahun = rs.getInt("tahun");
                genre = rs.getString("genre");
                durasi = rs.getInt("durasi");
                sutradara = rs.getString("sutradara");
                penulis = rs.getString("penulis");
                produser = rs.getString("produser");
                ratingUsia = rs.getInt("rating_usia");
                deskripsi = rs.getString("deskripsi");
                gambarLoc = Paths.getGambarPath()+rs.getString("gambar");
                trailerLoc = Paths.getVideoPath()+rs.getString("trailer");
                
                txtJudul.setText(judul);
                txtTahun.setText(Integer.toString(tahun));
                String[] split = genre.split(",");
                cbGenre1.setSelectedIndex(Integer.parseInt(split[0]));
                cbGenre2.setSelectedIndex(Integer.parseInt(split[1]));
                cbGenre3.setSelectedIndex(Integer.parseInt(split[2]));
                txtDurasi.setText(Integer.toString(durasi));
                txtSutradara.setText(sutradara);
                txtPenulis.setText(penulis);
                txtProduser.setText(produser);
                cbRatingUsia.setSelectedIndex(ratingUsia);
                txtDeskripsi.setText(deskripsi);
                showImg(gambarLoc);
                lblGambar.setText("File path: "+gambarLoc);
                lblTrailer.setText("File path: "+trailerLoc);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean edit(int id, String judul, int tahun, String genre, int durasi, String sutradara, String penulis, String produser, int ratingUsia, String deskripsi, String gambarLoc, String trailerLoc) throws Exception{
        
        InputStream inStream = null;
        OutputStream outStream = null;
        boolean result = false;
        
        //String newTrailerLoc = "E:\\RPL\\Movie-manager\\Video";
        File fileImg = new File(gambarLoc);
        //ini yang beda
        String newImageLoc = Paths.getGambarPath()+fileImg.getName();
        File newFileImg = new File(newImageLoc);
        if(gambarLoc.equals(newImageLoc)){
            newImageLoc = gambarLoc;
        } else{
            try{
                byte[] buffer = new byte[1024];
                int length;
                
                inStream = new FileInputStream(fileImg);
                outStream = new FileOutputStream(newFileImg);
            
                    while((length = inStream.read(buffer)) > 0){
                        outStream.write(buffer, 0, length);
                    }
                
                inStream.close();
                outStream.close();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        
        File fileVid = new File(trailerLoc);
        //ini juga beda
        String newTrailerLoc = Paths.getVideoPath()+fileVid.getName();
        File newFileVid = new File(newTrailerLoc);
        if(trailerLoc.equals(newTrailerLoc)){
            newTrailerLoc = trailerLoc;
        } else{
            try{
                byte[] buffer = new byte[1024];
                int length;
                
                inStream = new FileInputStream(fileVid);
                outStream = new FileOutputStream(newFileVid);
            
                    while((length = inStream.read(buffer)) > 0){
                        outStream.write(buffer, 0, length);
                    }
               
                inStream.close();
                outStream.close();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        
            String sql = "UPDATE Movie SET judul = ?, tahun = ?, genre = ?, durasi = ?, sutradara = ?, penulis = ?, produser = ?, rating_usia = ?, deskripsi = ?, gambar = ?, trailer = ? WHERE id = ?";
        
            try {
                Connection conn = Koneksi.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, judul);
                stmt.setInt(2, tahun);
                stmt.setString(3, genre);
                stmt.setInt(4, durasi);
                stmt.setString(5, sutradara);
                stmt.setString(6, penulis);
                stmt.setString(7, produser);
                stmt.setInt(8, ratingUsia);
                stmt.setString(9, deskripsi);
                stmt.setString(10, fileImg.getName());
                stmt.setString(11, fileVid.getName());
                stmt.setInt(12, id);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
    }
    
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(txtJudul.equals("") || txtTahun.getText().equals("") || txtDurasi.getText().equals("") || txtSutradara.equals("") || txtPenulis.equals("") || txtProduser.equals("") || txtDeskripsi.equals("") || cbGenre1.getSelectedIndex() == 0 || cbGenre2.getSelectedIndex() == 0 || cbGenre3.getSelectedIndex() == 0 || cbRatingUsia.getSelectedIndex() == 0 || gambarLoc == null || trailerLoc == null){
            JOptionPane.showMessageDialog(jPanel2, "Data film belum lengkap!");
        } else {
            boolean result = false;
            judul = txtJudul.getText();
            tahun = Integer.parseInt(txtTahun.getText());
            durasi = Integer.parseInt(txtDurasi.getText());
            sutradara = txtSutradara.getText();
            penulis = txtPenulis.getText();
            produser = txtProduser.getText();
            deskripsi = txtDeskripsi.getText();
            genre = String.valueOf(cbGenre1.getSelectedIndex()) + "," + String.valueOf(cbGenre2.getSelectedIndex()) + "," +  String.valueOf(cbGenre3.getSelectedIndex());
            ratingUsia = cbRatingUsia.getSelectedIndex();

            try{
                result = edit(id, judul, tahun, genre, durasi, sutradara, penulis, produser, ratingUsia, deskripsi, gambarLoc, trailerLoc);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if(result){
                JOptionPane.showMessageDialog(jPanel2, "Perubahan berhasil disimpan");
                new DetailFilm().showDetail(id);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(jPanel2, "Perubahan gagal disimpan");
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGambarActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        String ext, temp;
        fc.addChoosableFileFilter(new OpenFileFilter("jpeg", ".jpeg"));
        fc.addChoosableFileFilter(new OpenFileFilter("jpg", ".jpg"));
        int result = fc.showOpenDialog(jPanel2);
        if (result == JFileChooser.APPROVE_OPTION){
            temp = fc.getSelectedFile().getAbsolutePath();
            ext = temp.substring(temp.lastIndexOf(".")+1, temp.length());
            if(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("JPG") || ext.equals("JPEG")){
                gambarLoc = temp;
                lblGambar.setText("File path: " + gambarLoc);
                showImg(gambarLoc);
            } else {
                JOptionPane.showMessageDialog(jPanel2, "Gambar harus berjenis JPEG/JPG");
            }
        }
    }//GEN-LAST:event_btnGambarActionPerformed

    private void btnTrailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrailerActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        String ext, temp;
        fc.addChoosableFileFilter(new OpenFileFilter("mp4", ".mp4"));
        int result = fc.showOpenDialog(jPanel2);
        if (result == JFileChooser.APPROVE_OPTION){
            temp = fc.getSelectedFile().getAbsolutePath();
            File tempFile = new File(temp);
            ext = temp.substring(temp.lastIndexOf(".")+1, temp.length());
            if(ext.equals("mp4") || ext.equals("MP4")){
                if(tempFile.length() <= 50000000){
                    trailerLoc = temp;
                    lblTrailer.setText("File path: " + trailerLoc);
                } else {
                    JOptionPane.showMessageDialog(jPanel2, "Ukuran file video tidak boleh melebihi 50MB");
                }
            } else {
                JOptionPane.showMessageDialog(jPanel2, "Video harus berjenis MP4");
            }
        }
    }//GEN-LAST:event_btnTrailerActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        //new MovieManagerUI().setVisible(true);
        new DetailFilm().showDetail(id);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGambar;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTrailer;
    private javax.swing.JComboBox<String> cbGenre1;
    private javax.swing.JComboBox<String> cbGenre2;
    private javax.swing.JComboBox<String> cbGenre3;
    private javax.swing.JComboBox<String> cbRatingUsia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblShowGambar;
    private javax.swing.JLabel lblTrailer;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtDurasi;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtProduser;
    private javax.swing.JTextField txtSutradara;
    private javax.swing.JTextField txtTahun;
    // End of variables declaration//GEN-END:variables
}
