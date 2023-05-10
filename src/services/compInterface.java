/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Competences;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public interface compInterface {
    public void ajouterCompetence(Competences c) throws SQLException;
    public List<Competences> afficherCompetences() throws SQLException;
    public void supprimerCompetence(Competences c) throws SQLException;
    public void modifierCompetence(Competences c ) throws SQLException;
    
}
