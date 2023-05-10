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
public interface IPasswordReset <T>{

    public void addPasswordResetRequest(T r) throws SQLException;

    public void updatePasswordResetRequest(T r, String newEmail) throws SQLException;

    public void deletePasswordResetRequest(T r) throws SQLException;

    public List<T> showEmailList() throws SQLException;

}
