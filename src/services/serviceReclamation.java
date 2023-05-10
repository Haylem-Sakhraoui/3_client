/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utiles.MyDB;

/**
 *
 * @author User
 */
public class serviceReclamation implements Ireclamation {
    Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public serviceReclamation() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterReclamation(Reclamation r) throws SQLException {
         String req = "INSERT INTO reclamation (date, description) VALUES ('"
                + r.getDate() + "', '" + r.getDescription() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Reclamation> afficherReclamation() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Reclamation re = new Reclamation( rst.getInt("id"),
                    rst.getDate("date"),
                    rst.getString("description"));
            reclamations.add(re);
        }
        return reclamations;
    }

    @Override
    public void supprimerReclamation(int id) throws SQLException {
      
      
        int num = id;

         String req = "DELETE FROM reclamation WHERE id = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, num);

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);
    }

    @Override
    public void modifierReclamation(Reclamation r) throws SQLException {
    try {
            String req = "UPDATE reclamation SET date = '"+ r.getDate()+ "',`description` = '" +r.getDescription()+ "' WHERE reclamation.`id = " +r.getId();
            Statement st = connexion.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
}
    }
    public List<Reclamation> rechercherUsers(String critere) throws SQLException {
   
    String req = "SELECT * FROM reclamation WHERE id LIKE ? OR id_u LIKE ? OR description LIKE ?";
    stm = connexion.createStatement();
        //ensemble de resultat
       
    PreparedStatement pst = connexion.prepareStatement(req);
    pst.setString(1, "%" + critere + "%");
    pst.setString(2, "%" + critere + "%");
    pst.setString(3, "%" + critere + "%");
    ResultSet rs = pst.executeQuery();
    List<Reclamation> reclamations = new ArrayList<>();
   
    while (rs.next()) {
        Reclamation r = new Reclamation(rs.getInt("id"), rs.getDate("Date"),
                    rs.getString("description"));
                    
        reclamations.add(r);
    }
    return reclamations;
}
}








