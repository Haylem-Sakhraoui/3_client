/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface Ireclamation {
    public void ajouterReclamation(Reclamation r) throws SQLException;
    public List<Reclamation> rechercherUsers(String critere) throws SQLException;
    public List<Reclamation> afficherReclamation() throws SQLException;
    public void supprimerReclamation(int id) throws SQLException;
    public void modifierReclamation(Reclamation r) throws SQLException;
}
