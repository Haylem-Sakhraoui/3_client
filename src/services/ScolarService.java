/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Scolarite;
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
public class ScolarService implements scolarInterface {
     Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public ScolarService() {
        connexion = MyDB.getInstance().getConnexion();
    }


    @Override
    public void ajouterScolar(Scolarite Sc) throws SQLException {
        
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(Sc.getDateDebut());
        
        String req = "INSERT INTO scolarite (id_etab, nom_etablissement,ville,pays,diplome,date_obtention) VALUES ('" + Sc.getId_etab() + "', '" + Sc.getNom_etablissement()+ "','" + Sc.getVille() + "','" + Sc.getPays()+ "','" + Sc.getDiplome() + "','" + Sc.getDateObtention() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Scolarite> afficherScolar() throws SQLException {
        List<Scolarite> scolarite = new ArrayList<>();
        String req = "select * from scolarite";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Scolarite Sc = new Scolarite( rst.getInt("id_etab"),rst.getString("nom_etablissement"),rst.getString("ville"),rst.getString("pays"),rst.getString("diplome"),rst.getDate("date_obtention"));
            scolarite.add(Sc);
        }
        return scolarite;
    }

    @Override
    public void supprimerScolar(Scolarite Sc) throws SQLException {


         String req = "DELETE FROM scolarite WHERE id_etab = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, Sc.getId_etab());

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);
    }

    @Override
    public void modifierScolar(Scolarite Sc) throws SQLException {
        
        String req = "UPDATE scolarite SET nom_etablissement = ?,ville = ?,pays = ? ,diplome = ? ,date_obtention = ? WHERE id_etab = ?";
           PreparedStatement ps = connexion.prepareStatement(req);
           ps.setString(1, Sc.getNom_etablissement());
           ps.setString(2,Sc.getVille() );
           ps.setString(3,Sc.getPays() );
           ps.setString(4,Sc.getDiplome() );
           ps.setDate(5, (Date) Sc.getDateObtention() );
           ps.setInt(6, Sc.getId_etab());
           int nbLignesModifiees = ps.executeUpdate();
           System.out.println("Nombre de lignes modifiées : " + nbLignesModifiees);
    }
    
}
