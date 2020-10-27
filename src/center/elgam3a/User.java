package center.elgam3a;

import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author goda4
 */
public class User {
    
    static int ID_Us;
    static String Name_Us;
    static String UserName_US;
    static int Role;
    static InputStream Photo_US;
    static String status;

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        User.status = status;
    }

    /**
     *
     * @return
     */
    public static int getID_Us() {
        return ID_Us;
    }

    /**
     *
     * @param ID_Us
     */
    public static void setID_Us(int ID_Us) {
        User.ID_Us = ID_Us;
    }

    /**
     *
     * @return
     */
    public static String getName_Us() {
        return Name_Us;
    }

    /**
     *
     * @param Name_Us
     */
    public static void setName_Us(String Name_Us) {
        User.Name_Us = Name_Us;
    }

    /**
     *
     * @return
     */
    public static String getUserName_US() {
        return UserName_US;
    }

    /**
     *
     * @param UserName_US
     */
    public static void setUserName_US(String UserName_US) {
        User.UserName_US = UserName_US;
    }

    /**
     *
     * @return
     */
    public static int getRole() {
        return Role;
    }

    /**
     *
     * @param Role
     */
    public static void setRole(int Role) {
        User.Role = Role;
    }

    /**
     *
     * @return
     */
    public static InputStream getPhoto_US() {
        return Photo_US;
    }

    /**
     *
     * @param Photo_US
     */
    public static void setPhoto_US(InputStream Photo_US) {
        User.Photo_US = Photo_US;
    }


    
    
}
