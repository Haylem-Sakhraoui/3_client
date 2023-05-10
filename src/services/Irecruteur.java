/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Demande;
import java.sql.Date;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amine
 */
public interface Irecruteur {
    
    public void ajouterdemande(Demande d) throws SQLException;
    public List<Demande> afficherdemande(int id) throws SQLException;
    public void supprimerdemande(int id) throws SQLException;
    public void modifierdemande(String desc ,int exp,Double rem,int tel , Date date,int id) throws SQLException;
    
}
