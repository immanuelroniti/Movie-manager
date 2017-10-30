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
    private static String pathDB = "jdbc:sqlite:/Users/yongzari/Documents/MovieManager Project/Movie-manager/ImproveMovieManager/MovieManagerDB.db";
    private static String gambarPath = "/Users/yongzari/Documents/MovieManager Project/Movie-manager/Gambar/";
    private static String videoPath = "/Users/yongzari/Documents/MovieManager Project/Movie-manager/Video/";

    /**
     * @return the pathDB
     */
    public static String getPathDB() {
        return pathDB;
    }

    /**
     * @param aPathDB the pathDB to set
     */
    public static void setPathDB(String aPathDB) {
        pathDB = aPathDB;
    }

    /**
     * @return the gambarPath
     */
    public static String getGambarPath() {
        return gambarPath;
    }

    /**
     * @param aGambarPath the gambarPath to set
     */
    public static void setGambarPath(String aGambarPath) {
        gambarPath = aGambarPath;
    }

    /**
     * @return the videoPath
     */
    public static String getVideoPath() {
        return videoPath;
    }

    /**
     * @param aVideoPath the videoPath to set
     */
    public static void setVideoPath(String aVideoPath) {
        videoPath = aVideoPath;
    }
}
