/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

/**
 *
 * @author yongzari
 */
public class Paths {
    //Ragil:
    private static String pathDB = "jdbc:sqlite:src/MovieManagerDB.db";
    private static String gambarPath = "src/Gambar/";
    private static String videoPath = "src/Video/";
    
    //Roni:
    //private static String pathDB = "jdbc:sqlite:E:\\RPL\\Movie-manager\\ImproveMovieManager\\src\\MovieManagerDB.db";
    //private static String gambarPath = "E:\\RPL\\Movie-manager\\ImproveMovieManager\\src\\Gambar\\";
    //private static String videoPath = "E:\\RPL\\Movie-manager\\ImproveMovieManager\\src\\Video\\";
    
    //Dody:
    //private static String pathDB = "";
    //private static String gambarPath = "";
    //private static String videoPath = "";
    

    /**
     * @return the pathDB
     */
    public static String getPathDB() {
        return pathDB;
    }

    /**
     * @return the gambarPath
     */
    public static String getGambarPath() {
        return gambarPath;
    }

    /**
     * @return the videoPath
     */
    public static String getVideoPath() {
        return videoPath;
    }

}
