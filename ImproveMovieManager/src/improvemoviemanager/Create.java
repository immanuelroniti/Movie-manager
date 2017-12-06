/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author yongzari
 */
public class Create extends javax.swing.JFrame {

    private String judul, sutradara, penulis, produser, deskripsi, gambarLoc = null, trailerLoc = null;
    private int tahun, durasi, ratingUsia, genre1, genre2, genre3;
    private List<JTextField> listSutradara = new ArrayList<JTextField>();
    private List<JTextField> listPenulis = new ArrayList<JTextField>();
    private List<JTextField> listProduser = new ArrayList<JTextField>();
    private JFrame back;
    
    /**
     * Creates new form Create
     */
    public Create(JFrame back) {
        initComponents();
        if(Session.isStatus()){
            lblUsername.setText("Selamat datang, " + Session.getUsername());
            
        }
        setGenre();
        this.back = back;
    }
    
    public void setGenre(){
        List<String> genre1 = new ArrayList<String>();
        List<String> genre2 = new ArrayList<String>();
        List<String> genre3 = new ArrayList<String>();
        genre1.add("Genre 1");
        genre2.add("Genre 2");
        genre3.add("Genre 3");
        String sql = "SELECT * FROM Genre";
        Connection conn = Koneksi.getConnect();
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               if(rs.getInt("id")==0){
                    continue;
                }
               genre1.add(rs.getString("genre"));
               genre2.add(rs.getString("genre"));
               genre3.add(rs.getString("genre"));
           }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        cbGenre1.setModel(new DefaultComboBoxModel(genre1.toArray()));
        cbGenre2.setModel(new DefaultComboBoxModel(genre2.toArray()));
        cbGenre3.setModel(new DefaultComboBoxModel(genre3.toArray()));
    }
    
    public boolean insertCheck(String judul, int tahun, int durasi){
        String sql = "SELECT * " + "FROM Movie WHERE judul = ? AND tahun = ? AND durasi = ?";
        int count = 0;
        Connection conn = Koneksi.getConnect();
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, judul);
           stmt.setInt(2, tahun);
           stmt.setInt(3, durasi);
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
    
    public void insert(String judul, int tahun, int genre1, int genre2, int genre3, int durasi, String sutradara, String penulis, String produser, int ratingUsia, String deskripsi, String gambarLoc, String trailerLoc) throws Exception{
        
        File fileImg = new File(gambarLoc);
        String newImageLoc = Paths.getGambarPath()+fileImg.getName();
        File newFileImg = new File(newImageLoc);
        
        File fileVid = new File(trailerLoc);
        String newTrailerLoc = Paths.getVideoPath()+fileVid.getName();
        File newFileVid = new File(newTrailerLoc);
        
        InputStream inStream = null;
        OutputStream outStream = null;
        boolean result = false;
        
            try{
                byte[] buffer = new byte[1024];
                int length;
                
                inStream = new FileInputStream(fileVid);
                outStream = new FileOutputStream(newFileVid);
            
                while((length = inStream.read(buffer)) > 0){
                    outStream.write(buffer, 0, length);
                }
                
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
        
            String sql = "INSERT INTO Movie(judul, tahun, genre1, genre2, genre3, durasi, sutradara, penulis, produser, rating_usia, deskripsi, gambar, trailer, avg_star, total_star, total_user, star_giver, review_giver) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, '0', '0')";
            
            Connection conn = Koneksi.getConnect();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, judul);
                stmt.setInt(2, tahun);
                stmt.setInt(3, genre1);
                stmt.setInt(4, genre2);
                stmt.setInt(5, genre3);
                stmt.setInt(6, durasi);
                stmt.setString(7, sutradara);
                stmt.setString(8, penulis);
                stmt.setString(9, produser);
                stmt.setInt(10, ratingUsia);
                stmt.setString(11, deskripsi);
                stmt.setString(12, fileImg.getName());
                stmt.setString(13, fileVid.getName());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(jPanel2, "Film berhasil ditambahkan");
                new HalamanAwal().setVisible(true);
                this.dispose();
                this.back.dispose();
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

        jPanel3 = new javax.swing.JPanel();
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
        btnGambar = new javax.swing.JButton();
        btnTrailer = new javax.swing.JButton();
        lblGambar = new javax.swing.JLabel();
        lblTrailer = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        lblShowGambar = new javax.swing.JLabel();
        cbGenre1 = new javax.swing.JComboBox<>();
        cbGenre2 = new javax.swing.JComboBox<>();
        cbGenre3 = new javax.swing.JComboBox<>();
        cbRatingUsia = new javax.swing.JComboBox<>();
        btnTambah = new java.awt.Button();
        btnCancel = new java.awt.Button();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblUsername = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelSutradara = new javax.swing.JPanel();
        txtSutradara = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelPenulis = new javax.swing.JPanel();
        txtPenulis = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelProduser = new javax.swing.JPanel();
        txtProduser = new javax.swing.JTextField();
        btnTambahSutradara = new javax.swing.JButton();
        btnTambahProduser = new javax.swing.JButton();
        btnTambahPenulis = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(67, 67, 67));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Judul");

        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Tahun");

        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Genre");

        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setText("Durasi");

        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Sutradara");

        jLabel6.setForeground(new java.awt.Color(254, 254, 254));
        jLabel6.setText("Penulis");

        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setText("Produser");

        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("Rating Usia");

        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("Deskripsi");

        jLabel10.setBackground(new java.awt.Color(67, 67, 67));
        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setText("Gambar");

        jLabel11.setForeground(new java.awt.Color(254, 254, 254));
        jLabel11.setText("Trailer");

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

        lblGambar.setForeground(new java.awt.Color(254, 254, 254));
        lblGambar.setText("File path:");

        lblTrailer.setForeground(new java.awt.Color(254, 254, 254));
        lblTrailer.setText("File path:");

        lblShowGambar.setForeground(new java.awt.Color(254, 254, 254));
        lblShowGambar.setText("              Preview gambar");
        lblShowGambar.setToolTipText("");
        lblShowGambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbRatingUsia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MPAA Age Rating", "G", "PG", "PG-13", "R", "NC-17" }));

        btnTambah.setBackground(new java.awt.Color(13, 1, 180));
        btnTambah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah.setLabel("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(174, 28, 28));
        btnCancel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(254, 254, 254));
        jLabel12.setText("Admin Zone");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_rsz_2logo.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(254, 254, 254));
        jLabel14.setText("Tambah Film");

        lblUsername.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Selamat datang, Guest");

        txtDeskripsi.setColumns(20);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setRows(5);
        txtDeskripsi.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDeskripsi);

        jLabel15.setForeground(new java.awt.Color(254, 254, 254));
        jLabel15.setText("minutes");

        txtSutradara.setMinimumSize(new java.awt.Dimension(160, 26));
        txtSutradara.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout panelSutradaraLayout = new javax.swing.GroupLayout(panelSutradara);
        panelSutradara.setLayout(panelSutradaraLayout);
        panelSutradaraLayout.setHorizontalGroup(
            panelSutradaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSutradaraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSutradara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        panelSutradaraLayout.setVerticalGroup(
            panelSutradaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSutradaraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelSutradara);

        txtPenulis.setMinimumSize(new java.awt.Dimension(160, 26));
        txtPenulis.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout panelPenulisLayout = new javax.swing.GroupLayout(panelPenulis);
        panelPenulis.setLayout(panelPenulisLayout);
        panelPenulisLayout.setHorizontalGroup(
            panelPenulisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPenulisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        panelPenulisLayout.setVerticalGroup(
            panelPenulisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPenulisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(panelPenulis);

        txtProduser.setMinimumSize(new java.awt.Dimension(160, 26));
        txtProduser.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout panelProduserLayout = new javax.swing.GroupLayout(panelProduser);
        panelProduser.setLayout(panelProduserLayout);
        panelProduserLayout.setHorizontalGroup(
            panelProduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProduserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtProduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        panelProduserLayout.setVerticalGroup(
            panelProduserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProduserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtProduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(panelProduser);

        btnTambahSutradara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_1rsz_add-2.png"))); // NOI18N
        btnTambahSutradara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahSutradaraActionPerformed(evt);
            }
        });

        btnTambahProduser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_1rsz_add-2.png"))); // NOI18N
        btnTambahProduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProduserActionPerformed(evt);
            }
        });

        btnTambahPenulis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_1rsz_add-2.png"))); // NOI18N
        btnTambahPenulis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenulisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jSeparator2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 310, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbRatingUsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTahun)
                                            .addComponent(txtJudul)
                                            .addComponent(jScrollPane2)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cbGenre1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbGenre2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbGenre3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane1)
                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(9, 9, 9)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btnTambahSutradara)
                                                    .addComponent(btnTambahProduser)
                                                    .addComponent(btnTambahPenulis)))))
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(25, 25, 25)
                                                .addComponent(btnGambar))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblShowGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnTrailer)))
                                            .addComponent(lblTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(97, 97, 97))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGambar, btnTrailer});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lblUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnGambar))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblGambar)
                        .addGap(18, 18, 18)
                        .addComponent(lblShowGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(btnTrailer))
                        .addGap(18, 18, 18)
                        .addComponent(lblTrailer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbGenre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(btnTambahSutradara)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(btnTambahPenulis)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(btnTambahProduser)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbRatingUsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.back.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    public int getIdGenre(String genre){
        int id = 0;
        String sql = "SELECT id FROM Genre where genre = ?";
        Connection conn = Koneksi.getConnect();
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, genre);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               id = rs.getInt("id");
           }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return id;
    }
    
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
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
            
            if(!listSutradara.isEmpty()){
                for(JTextField text:listSutradara){
                    if(!text.getText().equals("")){
                        sutradara = sutradara + " & " +text.getText();
                    }
                }
            }
            if(!listPenulis.isEmpty()){
                for(JTextField text:listPenulis){
                    if(!text.getText().equals("")){
                        penulis = penulis + " & " +text.getText();
                    }
                }
            }
            if(!listProduser.isEmpty()){
                for(JTextField text:listProduser){
                    if(!text.getText().equals("")){
                        produser = produser + " & " +text.getText();
                    }
                }
            }
            
            deskripsi = txtDeskripsi.getText();
            genre1 = getIdGenre((String) cbGenre1.getSelectedItem());
            genre2 = getIdGenre((String) cbGenre2.getSelectedItem());
            genre3 = getIdGenre((String) cbGenre3.getSelectedItem());
            ratingUsia = cbRatingUsia.getSelectedIndex();

            try{
                result = insertCheck(judul, tahun, durasi);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if(result){
                try{
                    insert(judul, tahun, genre1, genre2, genre3, durasi, sutradara, penulis, produser, ratingUsia, deskripsi, gambarLoc, trailerLoc);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(jPanel2, "Film sudah ada!");
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

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

    private void btnTambahSutradaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahSutradaraActionPerformed
        // TODO add your handling code here:
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(160, 26));
        panelSutradara.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSutradara.add(text);
        panelSutradara.revalidate();
        
        listSutradara.add(text);
    }//GEN-LAST:event_btnTambahSutradaraActionPerformed

    private void btnTambahPenulisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenulisActionPerformed
        // TODO add your handling code here:
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(160, 26));
        panelPenulis.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelPenulis.add(text);
        panelPenulis.revalidate();
        
        listPenulis.add(text);
    }//GEN-LAST:event_btnTambahPenulisActionPerformed

    private void btnTambahProduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProduserActionPerformed
        // TODO add your handling code here:
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(160, 26));
        panelProduser.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelProduser.add(text);
        panelProduser.revalidate();
        
        listProduser.add(text);
    }//GEN-LAST:event_btnTambahProduserActionPerformed
    
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
    
    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnCancel;
    private javax.swing.JButton btnGambar;
    private java.awt.Button btnTambah;
    private javax.swing.JButton btnTambahPenulis;
    private javax.swing.JButton btnTambahProduser;
    private javax.swing.JButton btnTambahSutradara;
    private javax.swing.JButton btnTrailer;
    private javax.swing.JComboBox<String> cbGenre1;
    private javax.swing.JComboBox<String> cbGenre2;
    private javax.swing.JComboBox<String> cbGenre3;
    private javax.swing.JComboBox<String> cbRatingUsia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblShowGambar;
    private javax.swing.JLabel lblTrailer;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel panelPenulis;
    private javax.swing.JPanel panelProduser;
    private javax.swing.JPanel panelSutradara;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtDurasi;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtProduser;
    private javax.swing.JTextField txtSutradara;
    private javax.swing.JTextField txtTahun;
    // End of variables declaration//GEN-END:variables
}
