/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.*;
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
public class UserService implements IUser<User> {

    Connection connexion;
    Statement stm;

    public UserService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addUser(User u) throws SQLException {

        String req = "INSERT INTO `user` (`username`,`email`, `password`,`role`,`remember_token`,`created_at`,`updated_at`) VALUES ( '"
                + u.getUsername() + "', '" + u.getEmail() + "','" + u.getPassword() + "','" + u.getRole() + "', '" + u.getRemember_Token() + "', '" + u.getCreated_at() + "', '" + null + "') ";

        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void updateUserName(User u, String newName) throws SQLException {
        u.setUpdated_at();
        String req = "UPDATE `user` SET username = '" + newName + "',updated_at = '" + u.getUpdated_at() + "' where username = '" + u.getUsername() + "'  ";
        u.setUsername(newName);

        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void updateUserPassword(User u, String password) throws SQLException {
        u.setPassword(password);
        u.setUpdated_at();
        String req = "UPDATE `user` SET password = '" + password + "',updated_at = '" + u.getUpdated_at() + "' where username = '" + u.getUsername() + "' ";
        u.setPassword(password);
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void updateUserEmail(User u, String newEmail) throws SQLException {
        u.setEmail(newEmail);
        u.setUpdated_at();
        String req = "UPDATE `user` SET email = '" + newEmail + "',updated_at = '" + u.getUpdated_at() + "' where username = '" + u.getUsername() + "' ";
        u.setEmail(newEmail);
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void deleteUser(User u) throws SQLException {
        String req = "DELETE from `user` where username = '" + u.getUsername() + "' ";

        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<User> showUser() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User u = new User(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("username"), rst.getString("email"),
                    rst.getString("password"), rst.getString("role"), rst.getString("remember_token"), rst.getString("created_at"), rst.getString("updated_at"));
            users.add(u);
        }
        return users;
    }

    @Override
    public void updateRole(User u, String newRole) throws SQLException {
        u.setRole(newRole);
        u.setUpdated_at();
        String req = "UPDATE `user` SET role = '" + newRole + "',updated_at = '" + u.getUpdated_at() + "' WHERE username = '" + u.getUsername() + "' ";
        u.setRole(newRole);
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public User findUser(User u) throws SQLException {
        String req = "select * from `user` where `username` = '" + u.getUsername() + "'  ";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        if (rst.first() && u.matching(rst.getString("password"), u.getPassword())) {
              User user = new User(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("username"), rst.getString("email"),
                    rst.getString("password"), rst.getString("role"), rst.getString("remember_token"), rst.getString("created_at"), rst.getString("updated_at"));
            
            return user;
        } else {
            return null;
        }

    }
        @Override
    public boolean findUserByEmail(String email) throws SQLException {
        String req = "select * from `user` where `email` = '" + email + "'  ";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        if (rst.first()) {
            return true;
        } else {
            return false;
        }

    }


}
