/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.RecruteurService;
import services.UserSession;
import utiles.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class affichdemController implements Initializable {

   
    @FXML
    private TableColumn<Demande, Integer> cl_id;
    @FXML
    private TableColumn<Demande, String> cl_desc;
    @FXML
    private TableColumn<Demande, Integer> cl_exper;
    @FXML
    private TableColumn<Demande, Double> cl_rem;
    @FXML
    private TableColumn<Demande, Date> cl_expir;
    @FXML
    private TableColumn<Demande, Integer> cl_tel;
    @FXML
    private TableView<Demande> tb_affich;
    UserSession session;
    User u;
    Connection connexion;
    Statement stm;
    ResultSet rst2;
    int nbdem=0;
    @FXML
    private TextField tf_nb_tot_dem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    u = getCurrentUser();
         connexion = MyDB.getInstance().getConnexion();
        
       String requete = "SELECT COUNT(*) AS nb FROM demande WHERE id_recruteur='" + u.getId() + "'"; 

        try {
            stm = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(affichdemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rst = null;
        try {
            rst = stm.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(affichdemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int somme = 0;
        try {
            if (rst.next()) {
                try {
                    nbdem = rst.getInt("nb");
                } catch (SQLException ex) {
                    Logger.getLogger(affichdemController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }       } catch (SQLException ex) {
            Logger.getLogger(affichdemController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        tf_nb_tot_dem.setText(""+nbdem);
        
        //
        
        
        
                   
         RecruteurService rs = new RecruteurService();
          List<Demande> demandesList = null;
        try {
            demandesList = rs.afficherdemande(u.getId());
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
          ObservableList<Demande> demandes = FXCollections.observableArrayList(demandesList);
         
          cl_id.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("id_demande"));
          cl_desc.setCellValueFactory(new PropertyValueFactory<Demande, String> ("description"));
          cl_exper.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("experience"));
          cl_rem.setCellValueFactory(new PropertyValueFactory<Demande, Double> ("remuneration"));
          cl_expir.setCellValueFactory(new PropertyValueFactory<Demande, Date> ("expiration"));
          cl_tel.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("telephone"));
          tb_affich.setItems(demandes);
    }    

    
       public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }
    
}
