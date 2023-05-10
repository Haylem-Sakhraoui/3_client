/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.RememberPassword;
import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utiles.MyDB;

/**
 *
 * @author Salim Ben Hamida
 */
public class PasswordResetService implements IPasswordReset<RememberPassword> {

    Connection connexion;
    Statement stm;

    public PasswordResetService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addPasswordResetRequest(RememberPassword r) throws SQLException {
        String req = "INSERT INTO `password-reset` (`email`,`token`,`created_at`) VALUES ( '" + r.getEmail() + "','" + r.getToken() + "', '" + r.getCreated_at() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void updatePasswordResetRequest(RememberPassword r, String newEmail) throws SQLException {
        r.setEmail(newEmail);
        String req = "UPDATE `password-reset` SET email = '" + r.getEmail() + "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void deletePasswordResetRequest(RememberPassword r) throws SQLException {
        String req = "DELETE from `password-reset` where `email` = '" + r.getEmail()+ "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<RememberPassword> showEmailList() throws SQLException {
       List<RememberPassword> requests = new ArrayList<>();
        String  req = "select * from `password-reset` ";
        stm = connexion.createStatement(); 
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            RememberPassword rp = new RememberPassword(
                    rst.getString("email"), rst.getString("token"),
                   rst.getString("created_at"));
            requests.add(rp);
        }
        return requests;
    }

}
