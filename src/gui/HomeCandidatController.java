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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;
import services.RecruteurService;
import services.UserSession;
import utiles.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class HomeCandidatController implements Initializable {

    @FXML
    private TableView<Demande> tv_home_candidat;
    @FXML
    private TableColumn<Demande, Integer> id_cl_home;
    @FXML
    private TableColumn<Demande, String> desc_cl_home;
    @FXML
    private TableColumn<Demande, Integer> exp_cl_home;
    @FXML
    private TableColumn<Demande, Double> rem_cl_home;
    @FXML
    private TableColumn<Demande, Date> expir_cl_home;
    @FXML
    private TableColumn<Demande, Integer> tel_cl_home;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField tf_search_home_candidat;
    @FXML
    private Button btn_search_home_candidat;
    int index =-1;
    Connection connexion;
    Statement stm;
       UserSession session;
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
RecruteurService rs_auto_complet = new RecruteurService();
         List<String> listedesdescription = new ArrayList<>();
          List<Demande> listedesdemandes = new ArrayList<>();
        try {
            listedesdemandes = rs_auto_complet.afficherdemande();
                    } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Demande obj : listedesdemandes) {
                listedesdescription.add(obj.getDescription());
            }
        TextFields.bindAutoCompletion(tf_search_home_candidat, listedesdescription);
         
 
 
        connexion = MyDB.getInstance().getConnexion(); 
        List<Demande> demandesList = new ArrayList<>();
        String req = "select * from demande ";
        
        try {
            stm = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ensemble de resultat
        ResultSet rst = null;
        try {
            rst = stm.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rst.next()) {
                Demande de = new Demande( rst.getInt("id_demande"),rst.getInt("id_recruteur"),
                        rst.getString("nom_recruteur"),
                        rst.getString("description"), rst.getInt("experience"), rst.getDouble("remuneration"), rst.getDate("expiration") ,rst.getInt("telephone"));
                demandesList.add(de);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
 
 
 
 
 
   /* RecruteurService rs = new RecruteurService();
          List<Demande> demandesList = null;
        try {
            demandesList = rs.afficherdemande(u.getId());
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        } */
          ObservableList<Demande> demandes = FXCollections.observableArrayList(demandesList);
         
          id_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("id_demande"));
          desc_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, String> ("description"));
          exp_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("experience"));
          rem_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Double> ("remuneration"));
          expir_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Date> ("expiration"));
          tel_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("telephone"));
          tv_home_candidat.setItems(demandes);
          
          
          ObservableList<String> options = FXCollections.observableArrayList("date d expiration","rémunération","expérience","popularité");

           combobox.setItems(options);
           
           
             // add event listener to the combo box to update sorting
        combobox.setOnAction(event -> {
        String selectedValue = combobox.getSelectionModel().getSelectedItem();
        if (selectedValue.equals("date d expiration")) {
            // sort by expiration date
            tv_home_candidat.getSortOrder().clear();
            tv_home_candidat.getSortOrder().add(expir_cl_home);
        } else if (selectedValue.equals("rémunération")) {
            // sort by remuneration
            tv_home_candidat.getSortOrder().clear();
            tv_home_candidat.getSortOrder().add(rem_cl_home);
        } else if (selectedValue.equals("expérience")) {
        // sort by experience
        tv_home_candidat.getSortOrder().clear();
        tv_home_candidat.getSortOrder().add(exp_cl_home);
    }else if (selectedValue.equals("popularité")) {
        // sort by pop
        tv_home_candidat.getSortOrder().clear();
        List<Demande> demandesnb = new ArrayList<>();
        String sql = "SELECT demande.* FROM demande LEFT JOIN (SELECT id_demande, COUNT(*) AS nb_postes FROM poste GROUP BY id_demande) AS poste_counts ON demande.id_demande = poste_counts.id_demande ORDER BY poste_counts.nb_postes DESC";

                Connection connexion;
                connexion = MyDB.getInstance().getConnexion();
            try {
                stm = connexion.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        //ensemble de resultat
        ResultSet rst2=null;
            try {
                rst2 = stm.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                while (rst2.next()) {
                    Demande de = new Demande( rst2.getInt("id_demande"),
                            rst2.getString("nom_recruteur"),
                            rst2.getString("description"), rst2.getInt("experience"), rst2.getDouble("remuneration"), rst2.getDate("expiration") ,rst2.getInt("telephone"));
                    demandesnb.add(de);
                }   } catch (SQLException ex) {
                Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            ObservableList<Demande> demandesnbpart = FXCollections.observableArrayList(demandesnb);
             id_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("id_demande"));
          desc_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, String> ("description"));
          exp_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("experience"));
          rem_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Double> ("remuneration"));
          expir_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Date> ("expiration"));
          tel_cl_home.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("telephone"));
          tv_home_candidat.setItems(demandesnbpart);
          
       
            
                         }
        
        
        
    });
    }    

    @FXML
    private void chercher(ActionEvent event) {
        
                String searchQuery = tf_search_home_candidat.getText();
                ObservableList<Demande> filteredDemandes = FXCollections.observableArrayList();
                
            
        connexion = MyDB.getInstance().getConnexion(); 
        List<Demande> demandesList = new ArrayList<>();
        String req = "select * from demande ";
        
        try {
            stm = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ensemble de resultat
        ResultSet rst = null;
        try {
            rst = stm.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rst.next()) {
                Demande de = new Demande( rst.getInt("id_demande"),rst.getInt("id_recruteur"),
                        rst.getString("nom_recruteur"),
                        rst.getString("description"), rst.getInt("experience"), rst.getDouble("remuneration"), rst.getDate("expiration") ,rst.getInt("telephone"));
                demandesList.add(de);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeCandidatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
          ObservableList<Demande> demandes = FXCollections.observableArrayList(demandesList);
                
                
        for (Demande demande : demandes) {
             if (demande.getDescription().contains(searchQuery)) {
            filteredDemandes.add(demande);
        }
    }
    tv_home_candidat.setItems(filteredDemandes);
    }

    @FXML
    private void get_selected_item_id(MouseEvent event) throws SQLException {
        index=tv_home_candidat.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
        return;
        }
       int id = id_cl_home.getCellData(index).intValue();
       connexion = MyDB.getInstance().getConnexion();
      String sql_nb_pos = "SELECT COUNT(*) FROM poste WHERE id_demande = ?";
      PreparedStatement ps = connexion.prepareStatement(sql_nb_pos);
      ps.setInt(1, id);
      ResultSet rst = ps.executeQuery();

      if (rst.next()) {
       int nb = rst.getInt(1);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Info");
       alert.setHeaderText(null);
       alert.setContentText(nb+" : candidat(s) postulé(s) dans cette demande ! ");
       alert.showAndWait();
}
    }
    public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }
    
}