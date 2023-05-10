/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Salim Ben Hamida
 */
public class User {

    int Id;
    String Username, email, Password, Role, Remember_Token, Created_at, Updated_at;

   

    public User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public User(String Username, String email, String Password, String Role) {
        this.Username = Username;
        this.email = email;
        //Cryptage Mot de passe
        this.Password = getMd5(Password);
        this.Role = Role;
        //Génération du token 
        this.Remember_Token = usingRandomUUID();
        this.Created_at = getSysDate();
        this.Updated_at = null;
    }

    public User(int Id, String Username, String email, String Password, String Role, String Remember_Token, String Created_at, String Updated_at) {
        this.Id = Id;
        this.Username = Username;
        this.email = email;
        this.Password = Password;
        this.Role = Role;
        this.Remember_Token = Remember_Token;
        this.Created_at = Created_at;
        this.Updated_at = Updated_at;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = getMd5(Password);
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getRemember_Token() {
        return Remember_Token;
    }

    public void setRemember_Token(String Remember_Token) {
        this.Remember_Token = Remember_Token;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public void setUpdated_at() {
        this.Updated_at = getSysDate();
    }

    public String getUpdated_at() {
        return Updated_at;
    }

    public String usingRandomUUID() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("_", "");

    }

    public String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean matching(String orig, String compare) {
        String md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(compare.getBytes());
            byte[] digest = md.digest();
            md5 = new BigInteger(1, digest).toString(16);
            return md5.equals(orig);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }

    }

    public String getSysDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Username=" + Username + ", email=" + email + ", Password=" + Password + ", Role=" + Role + ", Remember_Token=" + Remember_Token + ", Created_at=" + Created_at + ", Updated_at=" + Updated_at + '}';
    }

    
}
