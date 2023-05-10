/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commentaire;
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
public class serviceCommentaire implements Icommentaire {
    Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public serviceCommentaire() {
        connexion = MyDB.getInstance().getConnexion();
    }
   
    @Override
    public void ajouterCommentaire(Commentaire c) throws SQLException {
         String com = "INSERT INTO commentaire (date_c, description_c,reclamation_id) VALUES ('"
                + c.getDate_c() + "', '" + c.getDescription_c() + "', '" + c.getReclamation_id() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(com);
    }
     @Override
     public void modifierComm(Commentaire c) throws SQLException {
    try {
            String req = "UPDATE commentaire SET date_c = '"+ c.getDate_c()+ "',`description_c` = '" +c.getDescription_c()+ "' WHERE commentaire.`id` = " +c.getId();
            Statement st = connexion.createStatement();
            st.executeUpdate(req);
            System.out.println("commentaire updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
}
    }
    public List<Commentaire> affichercomm() throws SQLException {
        List<Commentaire> Commentaires = new ArrayList<>();
        String req = "select * from commentaire";
        Statement stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire re = new Commentaire( rst.getInt("id"),
                    rst.getDate("date_c"),
                    rst.getString("description_c"),
            rst.getInt("reclamation_id"));
            Commentaires.add(re);
        }
        return Commentaires;
    }
     public List<Commentaire> afficherCommentaire() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String com = "select * from commentaire";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(com);

        while (rst.next()) {
            Commentaire ce = new Commentaire( rst.getInt("id"),
                    rst.getDate("date_c"),
                    rst.getString("description_c"),
            rst.getInt("reclamation_id"));
            commentaires.add(ce);
        }
        return commentaires;
    }
    
   
    public void supprimerCommentaire(int id) throws SQLException {
      
      
        int num  = id;

         String req = "DELETE FROM commentaire WHERE id = ?";
         PreparedStatement ps = connexion.prepareStatement(req);
         ps.setInt(1, num);

      // Exécution de la requête
      int nbLignesSupprimees = ps.executeUpdate();
      System.out.println("Nombre de lignes supprimées : " + nbLignesSupprimees);
    }

public void modifierCommentaire() throws SQLException {
        System.out.println("choisir l id du commentaire que vous voulez le modifier");
        int id_com_modif = scanner.nextInt();
        scanner.nextLine();
        System.out.println("tapez le numero du champ a modifier \n 1 : date \n 2 : description  ");
        int num_champ = scanner.nextInt();
        scanner.nextLine();
        switch (num_champ) {
    case 1:{
        
              System.out.println("saisir votre nouvelle date de du commentaire");
         String nv_date_c = scanner.nextLine();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         java.util.Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(nv_date_c);
            } catch (ParseException ex) {
                System.out.print(ex.getMessage());
            }
        java.sql.Date nv_date_c_sql = new java.sql.Date(parsedDate.getTime());
        String com= "UPDATE commentaire SET date = ? WHERE id = ?";
         PreparedStatement ps = connexion.prepareStatement(com);
         ps.setDate(1, nv_date_c_sql);
         ps.setInt(2,id_com_modif );
        int a= ps.executeUpdate();
        break;}
    case 2:{       
        System.out.println("saisir votre nouvelle description");
        String nv_desc_c = scanner.nextLine();
        String com = "UPDATE reclamation SET description = ? WHERE id = ?";
         PreparedStatement ps = connexion.prepareStatement(com);
         ps.setString(1, nv_desc_c);
         ps.setInt(2,id_com_modif );
        int a= ps.executeUpdate();
        break;}
    
     
    default:
        // instructions par dÃ©faut
    }
    
}

}