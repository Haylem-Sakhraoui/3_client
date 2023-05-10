/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Poste;
import entities.User;
import java.net.URL;
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
import services.CandidatService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class SupppostController implements Initializable {

    @FXML
    private TextField tf_id_post_supp;
    @FXML
    private Button btn_supp_post;
    @FXML
    private TableColumn<Poste, Integer> cl_id_supp_post;
    @FXML
    private TableColumn<Poste, Integer> cl_exp_supp_post;
    @FXML
    private TableColumn<Poste, String> cl_desc_supp_post;
    @FXML
    private TableView<Poste> tv_supp_post ;
    int index =-1;
UserSession session;
    User u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = getCurrentUser();
        
        CandidatService cs = new CandidatService();
          List<Poste> postesList=new ArrayList();
        try {
            postesList = cs.afficherPoste(u.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SupppostController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Poste> postes = FXCollections.observableArrayList(postesList);
         
          cl_id_supp_post.setCellValueFactory(new PropertyValueFactory<Poste, Integer> ("id_poste"));
          cl_exp_supp_post.setCellValueFactory(new PropertyValueFactory<Poste, Integer> ("experience"));
          cl_desc_supp_post.setCellValueFactory(new PropertyValueFactory<Poste, String> ("description"));
          
          tv_supp_post.setItems(postes);
    }    

    @FXML
    private void supprimer_post(ActionEvent event) throws SQLException {
        if(tf_id_post_supp.getText().isEmpty() ) {
            
        // afficher un message d'erreur indiquant qu'un champ est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir le champ id");
        alert.showAndWait();
    }
    else {
         CandidatService cs = new CandidatService();
        
        int id = Integer.parseInt(tf_id_post_supp.getText());
        cs.supprimerPoste(id);
         System.out.print("Poste supprimee ");
    }
    }

    @FXML
    private void get_selected_item(MouseEvent event) {
         index=tv_supp_post.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
        return;
        }
        tf_id_post_supp.setText(cl_id_supp_post.getCellData(index).toString());
    }
           public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

}
