/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utiles.MyDB;

/**
 *
 * @author lenovo
 */
public class servicesmethodes implements IService {
     Connection connexion;
    Statement stm;

    public servicesmethodes() {
        
        connexion = MyDB.getInstance().getConnexion();
    }
    
     @Override
    public void ajouteservice(service s) throws SQLException {
        String req = "INSERT INTO `services` (`nom_service`, `nb_tot_freelance`) VALUES ( '"
                + s.getNom_service() + "', '" + s.getNb_tot_freelance() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }
    
    public List<service> afficherpersonne() throws SQLException {
        List<service> serv = new ArrayList<>();
        String req = "select * from services";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            service s1 = new service(rst.getInt("id_service"),//or rst.getInt(1)
                    rst.getString("nom_service"),
                    rst.getInt("nb_tot_freelance"));
            serv.add(s1);
        }
        return serv;
    }
    
    public void supprimer(String nomservice1) throws SQLException{
         String req = "DELETE FROM services WHERE nom_service = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1,nomservice1);
        ps.executeUpdate();
     }
    
    
    
    public void modifier(String nomservm, int nbtot, String nservice) throws SQLException {
         
         String req = "UPDATE services SET nom_service = ?, nb_tot_freelance = ? WHERE nom_service = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1,nomservm);
         ps.setInt(2, nbtot);
         ps.setString(3, nservice);
         ps.executeUpdate();
     }
    
    public int calculer () throws SQLException {
        
        String requete = "SELECT SUM(nb_tot_freelance) AS somme_nombretotal FROM services";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(requete);
        int somme = 0;
if (rst.next()) {
    somme = rst.getInt("somme_nombretotal");
}
return somme;
    }
    
    
      public List<service> listtri() throws SQLException {
        List<service> serv = new ArrayList<>();
        String req = "select * from services";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            service s1 = new service(rst.getInt("id_service"),//or rst.getInt(1)
                    rst.getString("nom_service"),
                    rst.getInt("nb_tot_freelance"));
            serv.add(s1);
        }
        
         ArrayList<service> listeTriee = new ArrayList<>(serv);
         
         Collections.sort(listeTriee, new Comparator<service>() {
            public int compare(service o1, service o2) {
                return o1.getNb_tot_freelance() - o2.getNb_tot_freelance();
            }
        });

         return listeTriee;
      }
      
      public List<service> chercher(String nomservice2) throws SQLException {
        List<service> serv = new ArrayList<>();
        String req = "SELECT * FROM services WHERE nom_service = ?";
        PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1,nomservice2);
        
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            service s1 = new service(rst.getInt("id_service"),//or rst.getInt(1)
                    rst.getString("nom_service"),
                    rst.getInt("nb_tot_freelance"));
            serv.add(s1);
        }
        return serv;
    }


      
      public List<service> afficherservice() throws SQLException {
        List<service> serv = new ArrayList<>();
        String req = "select nom_service from services";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            service s1 = new service(//or rst.getInt(1)
                    rst.getString("nom_service")
                    );
            serv.add(s1);
        }
        return serv;
    }
}
