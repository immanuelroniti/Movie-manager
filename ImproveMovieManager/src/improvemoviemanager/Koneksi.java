/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Roni
 */
public class Koneksi {
    public static Connection connect(){
        Connection conn = null;
        try{
            //koneksi ini besa beda beda tiap laptop
            String url = "jdbc:sqlite:/Users/yongzari/Documents/MovieManager Project/Movie-manager/ImproveMovieManager/MovieManagerDB.db";
            //String url = "jdbc:sqlite:E:\\RPL\\Movie-manager\\ImproveMovieManager\\MovieManagerDB.db";
            //String url = "jdbc:sqlite:/home/ivana/Desktop/RPL/ImproveMovieManager/MovieManagerDB.db";
            conn = DriverManager.getConnection(url);
            //System.out.println("Connect coy");
            //JOptionPane.showMessageDialog(null, "Berhasil koneksi database");
            //System.out.println("Success!");
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
