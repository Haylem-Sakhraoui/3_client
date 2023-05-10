/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author User
 */
public interface Icommentaire {
     public void ajouterCommentaire(Commentaire c) throws SQLException;
     public List<Commentaire> affichercomm() throws SQLException;
    public List<Commentaire> afficherCommentaire() throws SQLException;
    public void supprimerCommentaire(int id) throws SQLException;
    public void modifierCommentaire() throws SQLException;
     public void modifierComm(Commentaire c) throws SQLException;
}


