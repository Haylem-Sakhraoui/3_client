/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Demande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.MyDB;

/**
 *
 * @author amine
 */
public class RecruteurService implements Irecruteur {
    
    Connection connexion;
    Statement stm;
     Scanner scanner = new Scanner(System.in);

    public RecruteurService() {
        connexion = MyDB.getInstance().getConnexion(); }

    @Override
    public void ajouterdemande(Demande d) throws SQLException {
        
      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(d.getExpiration());
        
      //  java.sql.Date sqlExpiration = new java.sql.Date(d.getExpiration().getTime());
        
        
        String req = "INSERT INTO `demande` (`id_recruteur`,`nom_recruteur`,  `description`, `experience`, `remuneration`, `telephone`, `expiration`) VALUES ( '"
                + d.getId_recruteur()+ "', '"+ d.getNomRecruteur() + "', '" + d.getDescription() + "', '" + d.getExperience() + "', '" + d.getRemuneration() + "', '" + d.getTelephone() + "', '" + formattedDate + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Demande> afficherdemande(int id ) throws SQLException {
        List<Demande> demandes = new ArrayList<>();
        String req = "select * from demande where id_recruteur= '"+ id + "' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Demande de = new Demande( rst.getInt("id_demande"),rst.getInt("id_recruteur"),
                    rst.getString("nom_recruteur"),
                    rst.getString("description"), rst.getInt("experience"), rst.getDouble("remuneration"), rst.getDate("expiration") ,rst.getInt("telephone"));
            demandes.add(de);
        }
        return demandes;
    }

    @Override
    public void supprimerdemande(int id) throws SQLException {
       
        
        int num = id;
        
         String req = "DELETE FROM demande WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, num);

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);

    }

    @Override
    public void modifierdemande(String desc ,int exp,Double rem,int tel , Date date,int id) throws SQLException {  
        String req = "UPDATE demande SET description = ?,experience = ?,remuneration = ? ,telephone = ?,expiration = ? WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1, desc);
         ps.setInt(2, exp);
         ps.setDouble(3,rem);
         ps.setInt(4, tel);
         ps.setDate(5, date);
         ps.setInt(6,id );
        int a= ps.executeUpdate(); }
            
        
        

    public List<Demande> afficherdemande() throws SQLException {
        List<Demande> demandes = new ArrayList<>();
        String req = "select * from demande ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Demande de = new Demande( rst.getInt("id_demande"),rst.getInt("id_recruteur"),
                    rst.getString("nom_recruteur"),
                    rst.getString("description"), rst.getInt("experience"), rst.getDouble("remuneration"), rst.getDate("expiration") ,rst.getInt("telephone"));
            demandes.add(de);
        }
        return demandes;

    }
       
    
  
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        switch (num_champ) {
    case 1:{
        
        System.out.println("saisir votre nouvelle description");
        String nv_desc = scanner.nextLine();
        String req = "UPDATE demande SET description = ? WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setString(1, nv_desc);
         ps.setInt(2,id_dem_modif );
        int a= ps.executeUpdate();
        break;}
    case 2:{       
        System.out.println("saisir votre nouvelle experience");
        int nv_exp = scanner.nextInt();
        String req = "UPDATE demande SET experience = ? WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, nv_exp);
         ps.setInt(2,id_dem_modif );
        int a= ps.executeUpdate();
        break;}
    case 3:{       
        System.out.println("saisir votre nouvelle remuneration");
        Double nv_rem = scanner.nextDouble();
        String req = "UPDATE demande SET remuneration = ? WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setDouble(1, nv_rem);
         ps.setInt(2,id_dem_modif );
        int a= ps.executeUpdate();
        break;}
     case 4:{       
        System.out.println("saisir votre nouveau numero de telephone");
       int nv_num = scanner.nextInt();
        String req = "UPDATE demande SET telephone = ? WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, nv_num);
         ps.setInt(2,id_dem_modif );
        int a= ps.executeUpdate();
        break;}
     case 5:{       
        System.out.println("saisir votre nouvelle date d'expiration yyyy-MM-dd de la demande");
         String nv_date = scanner.nextLine();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         java.util.Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(nv_date);
            } catch (ParseException ex) {
                System.out.print(ex.getMessage());
            }
        java.sql.Date nv_date_sql = new java.sql.Date(parsedDate.getTime());
        String req = "UPDATE demande SET expiration = ? WHERE id_demande = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setDate(1, nv_date_sql);
         ps.setInt(2,id_dem_modif );
        int a= ps.executeUpdate();
        break;}
    default:
        // instructions par défaut
}   */
    }
    
    
    

