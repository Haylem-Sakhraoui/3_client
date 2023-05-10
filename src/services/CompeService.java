/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Competences;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utiles.MyDB;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public class CompeService implements compInterface{

    Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public CompeService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterCompetence(Competences c) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String req = "INSERT INTO competences (id_comp, nom) VALUES ('" + c.getId() + "', '" + c.getNom() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Competences> afficherCompetences() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Competences> competences = new ArrayList<>();
        String req = "select * from competences";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Competences c = new Competences( rst.getInt("id_comp"),rst.getString("nom"));
            competences.add(c);
        }
        return competences;
      
    }

    @Override
    public void supprimerCompetence(Competences c) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
//         int num = id_comp;

         String req = "DELETE FROM competences WHERE id_comp = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, c.getId());

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);
    }

    @Override
    public void modifierCompetence(Competences c ) throws SQLException {
        
    try {
           String req = "UPDATE competences SET nom = ? WHERE id_comp = ?";
           PreparedStatement ps = connexion.prepareStatement(req);
           
           ps.setString(1, c.getNom());
           ps.setInt(2,c.getId() );
           
//           ps.executeUpdate();
           
           int nbLignesModifiees = ps.executeUpdate();
           System.out.println("Nombre de lignes modifiées : " + nbLignesModifiees);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
}
    }
    
    
}
