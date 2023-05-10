/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utiles.MyDB;

/**
 *
 * @author lenovo
 */
public class categoriesmethodes {
    
     Connection connexion;
    Statement stm;

    public categoriesmethodes() {
        
        connexion = MyDB.getInstance().getConnexion();
    }
    
     public void ajoutecategorie(categorie c) throws SQLException {
        String req = "INSERT INTO `categories` (`nom_categorie`, `nb_tot_service`, `id_service`) VALUES ( '"
                + c.getNom_categorie() + "', '" + c.getNb_tot_service() + "', '" + c.getId_service() +  "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }
     
     public List<categorie> afficher() throws SQLException {
        List<categorie> cat = new ArrayList<>();
        String req = "select * from categories";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            categorie c1 = new categorie(rst.getInt("id_categorie"),//or rst.getInt(1)
                    rst.getString("nom_categorie"),
                    rst.getInt("nb_tot_service"));
                  
            
            cat.add(c1);
        }
        return cat;
    }
     
     
     public void supprimer(String nomcategorie) throws SQLException{
         String req = "DELETE FROM categories WHERE nom_categorie = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1,nomcategorie);
        ps.executeUpdate();
     }
     
      public void modifier(String nomcat, int nbtots, String nomcategorie) throws SQLException {
         
         String req = "UPDATE categories SET nom_categorie = ?, nb_tot_service = ? WHERE nom_categorie = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1,nomcat);
         ps.setInt(2, nbtots);
         ps.setString(3, nomcategorie);
         ps.executeUpdate();
     }
      
      
          public int calculer () throws SQLException {
        
        String requete = "SELECT SUM(nb_tot_service) AS somme_nombretotal FROM categories";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(requete);
        int somme = 0;
if (rst.next()) {
    somme = rst.getInt("somme_nombretotal");
}
return somme;
    }
    
           public List<categorie> afficherpar(String nomservice) throws SQLException {
        List<categorie> cat = new ArrayList<>();
        String req = "SELECT c.nom_categorie, c.id_categorie, c.id_service FROM categories c JOIN services s ON s.id_service = c.id_service WHERE s.nom_service = ?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1,nomservice);
        ResultSet rst = ps.executeQuery();
       
        while (rst.next()) {
            categorie c1 = new categorie(rst.getInt("id_categorie"),//or rst.getInt(1)
                    rst.getString("nom_categorie"),
                    rst.getInt("id_service"));
                  
            
            cat.add(c1);
        }
        return cat;
    }
    
    
}
