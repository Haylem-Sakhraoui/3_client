/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.RecruteurService;
import services.UserSession;


/**
 * FXML Controller class
 *
 * @author amine
 */
public class suppdemController implements Initializable {

    @FXML
    private TextField tf_id_supp;
    @FXML
    private Button button_suppression;
    @FXML
    private TableColumn<Demande, String> cl_desc_supp;
    @FXML
    private TableColumn<Demande, Integer> cl_exp_supp;
    @FXML
    private TableColumn<Demande, Double> cl_rem_supp;
    @FXML
    private TableColumn<Demande, Date> cl_expir_supp;
    @FXML
    private TableColumn<Demande, Integer> cl_tel_supp;
    @FXML
    private TableView<Demande> tv_supp;
    int index =-1;
    public ObservableList<Demande> listM;
    @FXML
    private TableColumn<Demande, Integer> cl_id_supp;
    UserSession session;
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   u = getCurrentUser();
         
         //  idE.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("id_demande"));
         RecruteurService rs = new RecruteurService();
          List<Demande> demandesList= new ArrayList();
          

          
        try {
            demandesList = rs.afficherdemande(u.getId());
        } catch (SQLException ex) {
            Logger.getLogger(suppdemController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Demande> demandes = FXCollections.observableArrayList(demandesList);

                      cl_id_supp.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("id_demande"));
          cl_desc_supp.setCellValueFactory(new PropertyValueFactory<Demande, String> ("description"));
          cl_exp_supp.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("experience"));
          cl_rem_supp.setCellValueFactory(new PropertyValueFactory<Demande, Double> ("remuneration"));
          cl_expir_supp.setCellValueFactory(new PropertyValueFactory<Demande, Date> ("expiration"));
          cl_tel_supp.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("telephone"));
          tv_supp.setItems(demandes);
        
        
        
        
          
    }    

    @FXML
    private void supprimer_demande(ActionEvent event) throws SQLException {
         if( tf_id_supp.getText().isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }
    else {
        RecruteurService rs = new RecruteurService();
        
        int id = Integer.parseInt(tf_id_supp.getText());
        rs.supprimerdemande(id);
         System.out.print("demande supprimer ");
       
    }
    
    }

    @FXML
    private void get_selected_item(MouseEvent event) {
         index=tv_supp.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
        return;
        }
        tf_id_supp.setText(cl_id_supp.getCellData(index).toString());
        
    }
    
       public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }
    
    
}
