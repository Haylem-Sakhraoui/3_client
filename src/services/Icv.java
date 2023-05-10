/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CV;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public  interface Icv {
    
    public void addCV(CV cv) throws SQLException;
    public CV getCVById(int id) throws SQLException;
    public List<CV> getAllCVs() throws SQLException;
    public void updateCV(CV cv) throws SQLException;
    public void deleteCV(int id) throws SQLException;
}
