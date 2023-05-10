/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Experiences;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utiles.MyDB;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public class ExperService implements experInterface{
    Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public ExperService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterExperience(Experiences exp) throws SQLException {
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(exp.getDateDebut());
//        String formattedDate2 = sdf.format(exp.getDateFin());
 
        String req = "INSERT INTO experiences (id_exp, nom_entreprise,poste,date_debut,date_fin) VALUES ('" + exp.getId() + "', '" + exp.getNomEntreprise()+ "','" + exp.getPoste() + "','" + exp.getDateDebut() + "','" + exp.getDateFin() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Experiences> afficherExperience() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     List<Experiences> experiences = new ArrayList<>();
        String req = "select * from experiences";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Experiences exp = new Experiences( rst.getInt("id_exp"),rst.getString("nom_entreprise"),rst.getString("poste"),rst.getDate("date_debut"),rst.getDate("date_fin"));
            experiences.add(exp);
        }
        return experiences;
    }

    @Override
    public void supprimerExperiences(Experiences exp) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

         String req = "DELETE FROM experiences WHERE id_exp = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1,exp.getId());

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);
    
    }


    @Override
    public void modifierExperiences(Experiences exp) throws SQLException {
        
           String req = "UPDATE experiences SET nom_entreprise = ?,poste = ?,date_debut = ? ,date_fin = ? WHERE id_exp = ?";
           PreparedStatement ps = connexion.prepareStatement(req);
           ps.setString(1, exp.getNomEntreprise());
           ps.setString(2,exp.getPoste() );
           ps.setDate(3, (Date) exp.getDateDebut());
           ps.setDate(4, (Date) exp.getDateFin());
           ps.setInt(5, exp.getId());
           int nbLignesModifiees = ps.executeUpdate();
           System.out.println("Nombre de lignes modifiées : " + nbLignesModifiees); 
    }
}
