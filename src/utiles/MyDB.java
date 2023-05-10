/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.sql.*;

/**
 *
 * @author Salim Ben Hamida
 */
public class MyDB {

    public String url = "jdbc:mysql://localhost/pidev2";
    public String username = "root";
    public String pwd = "";
    private static MyDB instance;
    Connection connexion;

    public MyDB() {
        try {
            connexion = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }

}
