/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import services.RecruteurService;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class FXMLRecruteurController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnaffich;
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
     private Label label;
    @FXML
    private Button btn_home_recruteur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void diriger_vers_ajout(MouseEvent event) throws IOException {
        Loadpage("/gui/ajoutdem");
    }

    @FXML
    private void diriger_vers_suppression(MouseEvent event) {
         Loadpage("/gui/suppdem");
    }

    @FXML
    private void diriger_vers_modification(MouseEvent event) {
         Loadpage("/gui/modifdem");
    }

    @FXML
    private void diriger_vers_affichage(MouseEvent event) {
        Loadpage("/gui/affichdem");
    }
    
    
    private void Loadpage(String page){
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLRecruteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void quitter(MouseEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("quitter l'application");
      alert.setHeaderText("vous etes sure de quitter");
      alert.setContentText("*****");


      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
         Platform.exit();
      } else if (option.get() == ButtonType.CANCEL) {
         //Loadpage("/gui/FXMLCandidat");
      } else {
         this.label.setText("-");
      }
        
    }

    @FXML
    private void diriger_vers_home_recruteur(MouseEvent event) {
        Loadpage("/gui/homeRecruteur");
    }

   
}
