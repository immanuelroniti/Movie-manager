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
public class Session {
    private static boolean status = false;
    private static int id = 0;
    private static String username = null;
    private static int role = 0;

    /**
     * @return the status
     */
    public static boolean isStatus() {
        return status;
    }

    /**
     * @param aStatus the status to set
     */
    public static void setStatus(boolean aStatus) {
        status = aStatus;
    }

    /**
     * @return the id
     */
    public static int getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(int aId) {
        id = aId;
    }

    /**
     * @return the username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * @param aUsername the username to set
     */
    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * @return the role
     */
    public static int getRole() {
        return role;
    }

    /**
     * @param aRole the role to set
     */
    public static void setRole(int aRole) {
        role = aRole;
    }
    
    
}
