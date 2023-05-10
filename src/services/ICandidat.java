/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Poste;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author macbook
 */
public interface ICandidat {
    
    public void ajouterPoste(Poste p,int id) throws SQLException;
    public List<Poste> afficherPoste(int id) throws SQLException;
    public void supprimerPoste(int id) throws SQLException;
    public void modifierPoste(int id,int exp,String desc) throws SQLException;
    
}
