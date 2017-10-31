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
public class Logout {
    public static void keluar(){
        Session.setId(0);
        Session.setUsername(null);
        Session.setRole(0);
        Session.setStatus(false);
        new HalamanAwal().setVisible(true);
    }
    
}
