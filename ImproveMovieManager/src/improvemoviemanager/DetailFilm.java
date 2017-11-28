/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author yongzari
 */
public class DetailFilm extends javax.swing.JFrame {

    int id, asal, star = 0, totalUser = 0;
    float avgStar = 0, totalStar = 0;
    String fileGambar, fileVideo, starGiver, reviewGiver, title;
    boolean isEditable = false;
    
    /**
     * Creates new form DetailFilm
     */
    
    public DetailFilm() {
        initComponents();
        btnRate.setVisible(false);
        btnAddReview.setVisible(false);
        if(Session.isStatus()){
            lblUsername.setText("Selamat datang, " + Session.getUsername());
            btnLogout.setVisible(true);
            btnLogin.setVisible(false);
            if(Session.getRole()==1){
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                toggleRating.setVisible(false);
            } else {
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                toggleRating.setVisible(true);
            }
        } else {
            toggleRating.setVisible(false);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnLogin.setVisible(true);
            btnLogout.setVisible(false);
        }

    }
    
    public String getGenre(int id){
        String name = "";
        String sql = "SELECT * FROM Genre WHERE id = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                name = rs.getString("genre");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return name;
    }
    
    public String getRatingUsia(int ratingUsia){
        String ratingComplete = "";
        String sql = "SELECT * FROM RatingUsia WHERE id = ?";
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ratingUsia);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ratingComplete = rs.getString("rating_usia");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return ratingComplete;
    }
    
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
    
    public void showStar(){
        try {
            BufferedImage img1 = null, img2 = null, img3 = null;
            img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
            img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));
            img3 = ImageIO.read(new File("src/Gambar/rsz_half_star.png"));
            
            Image hollowStar = img1;
            Image stuffedStar = img2;
            Image halfStar = img3;
            
            if(avgStar <= 0){
                star1.setIcon(new ImageIcon(hollowStar));
                star2.setIcon(new ImageIcon(hollowStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 0 && avgStar <= 0.5){
                star1.setIcon(new ImageIcon(halfStar));
                star2.setIcon(new ImageIcon(hollowStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 0.5 && avgStar <= 1){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(hollowStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 1 && avgStar <= 1.5){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(halfStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 1.5 && avgStar <=2){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 2 && avgStar <= 2.5){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(halfStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 2.5 && avgStar <= 3){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 3 && avgStar <= 3.5){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(halfStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 3.5 && avgStar <= 4){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(stuffedStar));
                star5.setIcon(new ImageIcon(hollowStar));
                
            } else if(avgStar > 4 && avgStar <= 4.5){
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(stuffedStar));
                star5.setIcon(new ImageIcon(halfStar));
                
            } else {
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(stuffedStar));
                star5.setIcon(new ImageIcon(stuffedStar));
                
            }
        } catch (IOException ex) {
            Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showDetail(int id, int asal){
        this.asal = asal;
        this.id = id;
        this.setVisible(true);
        
        String sql = "SELECT * FROM Movie WHERE id = ?";
        
        try{
            Connection conn = Koneksi.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                title = rs.getString("judul");
                isiJudul.setText(title);
                isiTahun.setText(rs.getString("tahun"));
                String genre = getGenre(rs.getInt("genre1")) + ", " + getGenre(rs.getInt("genre2")) + ", " + getGenre(rs.getInt("genre3"));
                isiGenre.setText(genre);
                isiDurasi.setText(Integer.toString(rs.getInt("durasi")) + " minutes");
                isiSutradara.setText(rs.getString("sutradara"));
                isiPenulis.setText(rs.getString("penulis"));
                isiProduser.setText(rs.getString("produser"));
                String ratingUsia = getRatingUsia(rs.getInt("rating_usia"));
                isiRatingUsia.setText(ratingUsia);
                isiDeskripsi.setText("<html>"+rs.getString("deskripsi")+"</html>");
                fileGambar=Paths.getGambarPath()+rs.getString("gambar");
                fileVideo=Paths.getVideoPath()+rs.getString("trailer");
                showImg(Paths.getGambarPath()+rs.getString("gambar"));
                avgStar = rs.getFloat("avg_star");
                totalStar = rs.getFloat("total_star");
                totalUser = rs.getInt("total_user");
                starGiver = rs.getString("star_giver");
                reviewGiver = rs.getString("review_giver");
                showStar();
                lblStar.setText(Float.toString(avgStar) + " Stars from " + Integer.toString(totalUser) + " user(s)");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        String[] tempStarGiver = starGiver.split(",");
        int count1 = 0;
        for(int i=0;i<tempStarGiver.length;i++){
            if(Integer.parseInt(tempStarGiver[i])==Session.getId()){
                count1++;
            }
        }

        if(count1 == 0 && Session.getRole() == 2){
            toggleRating.setVisible(true);
        } else {
            toggleRating.setVisible(false);
        }
        
        String[] tempReviewGiver = reviewGiver.split(",");
        int count2 = 0;
        for(int i=0;i<tempReviewGiver.length;i++){
            if(Integer.parseInt(tempReviewGiver[i])==Session.getId()){
                count2++;
            }
        }

        if(count2 == 0 && Session.getRole() == 2){
            btnAddReview.setVisible(true);
        } else {
            btnAddReview.setVisible(false);
        }
    }
    
    public void deleteMovie(){
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Confirm delete", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            String sql = "DELETE FROM Movie WHERE id = ?";
            try{
                Connection conn = Koneksi.getConnect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                File img = new File(fileGambar);
                File vid = new File(fileVideo);
                img.delete();
                vid.delete();
            
                JOptionPane.showMessageDialog(null, "Film berhasil dihapus");
                new HalamanAwal().setVisible(true);
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
        lblShowGambar = new javax.swing.JLabel();
        isiJudul = new javax.swing.JLabel();
        isiTahun = new javax.swing.JLabel();
        isiGenre = new javax.swing.JLabel();
        isiDurasi = new javax.swing.JLabel();
        isiSutradara = new javax.swing.JLabel();
        isiPenulis = new javax.swing.JLabel();
        isiProduser = new javax.swing.JLabel();
        isiRatingUsia = new javax.swing.JLabel();
        isiDeskripsi = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnDelete = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        star1 = new javax.swing.JButton();
        star2 = new javax.swing.JButton();
        star3 = new javax.swing.JButton();
        star4 = new javax.swing.JButton();
        star5 = new javax.swing.JButton();
        toggleRating = new javax.swing.JToggleButton();
        btnRate = new javax.swing.JButton();
        lblStar = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        btnReview = new javax.swing.JButton();
        btnAddReview = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(67, 67, 67));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Judul");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Tahun");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Genre");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setText("Durasi");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Sutradara");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 254, 254));
        jLabel6.setText("Penulis");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setText("Produser");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("Rating Usia");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("Deskripsi");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblShowGambar.setForeground(new java.awt.Color(254, 254, 254));
        lblShowGambar.setText("                           Preview gambar");
        lblShowGambar.setToolTipText("");

        isiJudul.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiJudul.setForeground(new java.awt.Color(254, 254, 254));

        isiTahun.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiTahun.setForeground(new java.awt.Color(254, 254, 254));

        isiGenre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiGenre.setForeground(new java.awt.Color(254, 254, 254));

        isiDurasi.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiDurasi.setForeground(new java.awt.Color(254, 254, 254));

        isiSutradara.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiSutradara.setForeground(new java.awt.Color(254, 254, 254));

        isiPenulis.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiPenulis.setForeground(new java.awt.Color(254, 254, 254));

        isiProduser.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiProduser.setForeground(new java.awt.Color(254, 254, 254));

        isiRatingUsia.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiRatingUsia.setForeground(new java.awt.Color(254, 254, 254));

        isiDeskripsi.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        isiDeskripsi.setForeground(new java.awt.Color(254, 254, 254));
        isiDeskripsi.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        isiDeskripsi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_rsz_2logo.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(254, 254, 254));
        jLabel14.setText("Detail Film");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Selamat datang, Guest");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setText("Rating");

        star1.setBackground(new java.awt.Color(67, 67, 67));
        star1.setForeground(new java.awt.Color(67, 67, 67));
        star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_hollowstar.png"))); // NOI18N
        star1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star1ActionPerformed(evt);
            }
        });

        star2.setBackground(new java.awt.Color(67, 67, 67));
        star2.setForeground(new java.awt.Color(67, 67, 67));
        star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_hollowstar.png"))); // NOI18N
        star2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star2ActionPerformed(evt);
            }
        });

        star3.setBackground(new java.awt.Color(67, 67, 67));
        star3.setForeground(new java.awt.Color(67, 67, 67));
        star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_hollowstar.png"))); // NOI18N
        star3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star3ActionPerformed(evt);
            }
        });

        star4.setBackground(new java.awt.Color(67, 67, 67));
        star4.setForeground(new java.awt.Color(67, 67, 67));
        star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_hollowstar.png"))); // NOI18N
        star4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star4ActionPerformed(evt);
            }
        });

        star5.setBackground(new java.awt.Color(67, 67, 67));
        star5.setForeground(new java.awt.Color(67, 67, 67));
        star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/rsz_hollowstar.png"))); // NOI18N
        star5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                star5ActionPerformed(evt);
            }
        });

        toggleRating.setText("Beri Rating");
        toggleRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleRatingActionPerformed(evt);
            }
        });

        btnRate.setText("Simpan");
        btnRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRateActionPerformed(evt);
            }
        });

        lblStar.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblStar.setForeground(new java.awt.Color(254, 254, 254));
        lblStar.setText("0 Stars from 0 user(s)");

        btnPlay.setText("Play Trailer");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnReview.setText("Lihat Review");
        btnReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewActionPerformed(evt);
            }
        });

        btnAddReview.setText("Tulis Review");
        btnAddReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddReviewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isiDurasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(isiGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(isiTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(isiSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(isiPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(isiProduser, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 23, Short.MAX_VALUE)))
                                .addGap(7, 7, 7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(69, 69, 69)
                                                .addComponent(isiJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel10))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(isiRatingUsia, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(star1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(star2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(star3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(star4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(star5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(lblStar))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(toggleRating)
                                                            .addComponent(btnRate)))))))
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(isiDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnReview, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                            .addComponent(btnAddReview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addGap(32, 32, 32)
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnback))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblShowGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogin)
                                .addGap(31, 31, 31)
                                .addComponent(btnLogout)
                                .addContainerGap())
                            .addComponent(jSeparator1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {isiPenulis, isiProduser, isiSutradara});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLogin)
                                .addComponent(btnLogout))
                            .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnDelete)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnback)
                        .addComponent(btnEdit))
                    .addComponent(isiJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isiSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(isiPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(isiTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(isiGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(isiDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isiRatingUsia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addComponent(isiProduser, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(star1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(star5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(toggleRating)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRate)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isiDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnReview)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddReview))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblShowGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlay)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewActionPerformed
        // TODO add your handling code here:
        new ReviewList().showReview(id, title);
    }//GEN-LAST:event_btnReviewActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().open(new File(fileVideo));
        } catch (IOException ex) {
            Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRateActionPerformed
        // TODO add your handling code here:
        totalStar = totalStar+star;
        totalUser++;
        avgStar = totalStar/totalUser;
        starGiver = starGiver + "," + Session.getId();
        String sql = "UPDATE Movie SET avg_star = ?, total_star = ?, total_user = ?, star_giver = ? WHERE id = ?";
        Connection conn = Koneksi.getConnect();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, avgStar);
            stmt.setFloat(2, totalStar);
            stmt.setInt(3, totalUser);
            stmt.setString(4, starGiver);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(jPanel2, "Rating anda berhasil disimpan");
            new DetailFilm().showDetail(id, 1);
            this.dispose();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(jPanel2, "Rating anda gagal disimpan");
        }
    }//GEN-LAST:event_btnRateActionPerformed

    private void toggleRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleRatingActionPerformed
        try {
            BufferedImage img1 = null, img2 = null;
            img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
            img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));

            Image hollowStar = img1;
            Image stuffedStar = img2;
            if(toggleRating.isSelected()){
                toggleRating.setText("Batal");
                isEditable = true;
                btnRate.setVisible(true);
                star1.setIcon(new ImageIcon(hollowStar));
                star2.setIcon(new ImageIcon(hollowStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));

            } else {
                toggleRating.setText("Beri Rating");
                isEditable = false;
                btnRate.setVisible(false);
                showStar();
            }
        } catch (IOException ex) {
            Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_toggleRatingActionPerformed

    private void star5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star5ActionPerformed
        // TODO add your handling code here:
        if(isEditable){
            try {
                BufferedImage img1 = null, img2 = null;
                img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
                img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));

                Image hollowStar = img1;
                Image stuffedStar = img2;
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(stuffedStar));
                star5.setIcon(new ImageIcon(stuffedStar));

            } catch (IOException ex) {
                Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            star = 5;
        }
    }//GEN-LAST:event_star5ActionPerformed

    private void star4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star4ActionPerformed
        // TODO add your handling code here:
        if(isEditable){
            try {
                BufferedImage img1 = null, img2 = null;
                img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
                img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));

                Image hollowStar = img1;
                Image stuffedStar = img2;
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(stuffedStar));
                star5.setIcon(new ImageIcon(hollowStar));

            } catch (IOException ex) {
                Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            star = 4;
        }
    }//GEN-LAST:event_star4ActionPerformed

    private void star3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star3ActionPerformed
        // TODO add your handling code here:
        if(isEditable){
            try {
                BufferedImage img1 = null, img2 = null;
                img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
                img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));

                Image hollowStar = img1;
                Image stuffedStar = img2;
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(stuffedStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));

            } catch (IOException ex) {
                Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            star = 3;
        }
    }//GEN-LAST:event_star3ActionPerformed

    private void star2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star2ActionPerformed
        // TODO add your handling code here:
        if(isEditable){
            try {
                BufferedImage img1 = null, img2 = null;
                img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
                img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));

                Image hollowStar = img1;
                Image stuffedStar = img2;
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(stuffedStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));

            } catch (IOException ex) {
                Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            star = 2;
        }
    }//GEN-LAST:event_star2ActionPerformed

    private void star1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_star1ActionPerformed
        // TODO add your handling code here:
        if(isEditable){
            try {
                BufferedImage img1 = null, img2 = null;
                img1 = ImageIO.read(new File("src/Gambar/rsz_hollowstar.png"));
                img2 = ImageIO.read(new File("src/Gambar/rsz_stuffedstar.png"));

                Image hollowStar = img1;
                Image stuffedStar = img2;
                star1.setIcon(new ImageIcon(stuffedStar));
                star2.setIcon(new ImageIcon(hollowStar));
                star3.setIcon(new ImageIcon(hollowStar));
                star4.setIcon(new ImageIcon(hollowStar));
                star5.setIcon(new ImageIcon(hollowStar));

            } catch (IOException ex) {
                Logger.getLogger(DetailFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            star = 1;
        }
    }//GEN-LAST:event_star1ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?", "Confirm logout", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            Logout.keluar();
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        new LoginUI().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteMovie();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        new Update().showDetail(id);
        this.dispose();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        if(asal==1){
            new HalamanAwal().setVisible(true);
            this.dispose();
        } else if(asal==2){
            this.dispose();
        }
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnAddReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddReviewActionPerformed
        // TODO add your handling code here:
        new TulisReview(id).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddReviewActionPerformed
    
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
            java.util.logging.Logger.getLogger(DetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailFilm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddReview;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRate;
    private javax.swing.JButton btnReview;
    private javax.swing.JButton btnback;
    private javax.swing.JLabel isiDeskripsi;
    private javax.swing.JLabel isiDurasi;
    private javax.swing.JLabel isiGenre;
    private javax.swing.JLabel isiJudul;
    private javax.swing.JLabel isiPenulis;
    private javax.swing.JLabel isiProduser;
    private javax.swing.JLabel isiRatingUsia;
    private javax.swing.JLabel isiSutradara;
    private javax.swing.JLabel isiTahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblShowGambar;
    private javax.swing.JLabel lblStar;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton star1;
    private javax.swing.JButton star2;
    private javax.swing.JButton star3;
    private javax.swing.JButton star4;
    private javax.swing.JButton star5;
    private javax.swing.JToggleButton toggleRating;
    // End of variables declaration//GEN-END:variables
}
