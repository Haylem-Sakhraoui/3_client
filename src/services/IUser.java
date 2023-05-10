/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Salim Ben Hamida
 */
public interface IUser<T> {

    public void addUser(T u) throws SQLException;

    public void updateUserName(T u, String newName) throws SQLException;

    public void updateUserPassword(T u, String newPassword) throws SQLException;

    public void updateUserEmail(T u, String newEmail) throws SQLException;

    public void updateRole(T u, String newRole) throws SQLException;

    public void deleteUser(T u) throws SQLException;

    public T findUser(T u) throws SQLException;

    public boolean findUserByEmail(String email) throws SQLException;

    public List<T> showUser() throws SQLException;

}
