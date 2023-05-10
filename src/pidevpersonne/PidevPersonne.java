/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpersonne;

import entities.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import services.*;

/**
 *
 * @author Salim Ben Hamida
 */
public class PidevPersonne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        User p = new User("Salim", "benhamidab@gmail.com", "11","admin");
        UserService sp = new UserService();
        RememberPassword rp = new RememberPassword("benhamidab@gmail.com");
        PasswordResetService prs = new PasswordResetService(); 
            //add User
            /*
            try {
            sp.addUser(p);
            System.out.println("ajout avec succes");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }*/
 /*
            //Show User
            try {
            System.out.println(sp.showUser());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }/*
            //Update Name
            try {
            sp.updateUserName(p, "fathi");
            System.out.println(sp.showUser());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            //Update Password
            try {
            sp.updateUserPassword(p, "123");
            System.out.println(sp.showUser());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            //Update email
            try {
            sp.updateUserEmail(p, "benhamida@gmail.com");
            System.out.println(sp.showUser());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            /*
            //Delete User
            try {
            sp.deleteUser(p);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }*/
 /*
    try {
            System.out.println(prs.showEmailList());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/

    }

}
