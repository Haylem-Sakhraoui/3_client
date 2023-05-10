/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Demande;
import entities.Poste;
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
 * @author macbook
 */
public class CandidatService implements ICandidat {

    Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public CandidatService() {
        connexion = MyDB.getInstance().getConnexion();
    }


    @Override
    public void ajouterPoste(Poste p,int id) throws SQLException {
        
            int id_dem = id;
            
          String req = "INSERT INTO `poste` (`id_demande`, `experience`, `description`, `id_candidat`) VALUES ( '" + id_dem + "', '" + p.getExperience() + "', '" + p.getDescription() + "', '" + p.getId_candidat()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    
    }

    @Override
    public List<Poste> afficherPoste(int id) throws SQLException {
        List<Poste> postes = new ArrayList<>();
        String req = "select * from poste where id_candidat= '" + id + "'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Poste po = new Poste( rst.getInt("id_poste"),rst.getInt("experience"),rst.getString("description"));
            postes.add(po);
        }
        return postes;
    }

    @Override
    public void supprimerPoste(int id) throws SQLException {
        
        int num = id;
        
         String req = "DELETE FROM poste WHERE id_poste = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, num);

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);
    }

    @Override
    public void modifierPoste(int id, int exp, String desc) throws SQLException {
        
     
       
        
        String req = "UPDATE poste SET experience = ? , description = ? WHERE id_poste = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, exp);
         ps.setString(2,desc );
         ps.setInt(3,id );
         
        int a= ps.executeUpdate();
        
        
        }
    
    }

         
         
         
         
        
     

