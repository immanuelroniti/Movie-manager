/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Roni
 */
public class Koneksi {
    public static Connection connect(){
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:/Users/yongzari/Documents/MovieManager Project/Movie-manager/MovieManagerDB.db";
            conn = DriverManager.getConnection(url);
            //System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
