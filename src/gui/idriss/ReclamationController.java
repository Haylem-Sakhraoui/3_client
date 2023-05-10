/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;




/**
 * FXML Controller class
 *
 * @author User
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button page2;
    @FXML
    private Button page3;
    @FXML
    private BorderPane bp;
    @FXML
    private Button page4;
    @FXML
    private Button quitter;
    @FXML
    private Button Pmail;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(MouseEvent event) {
      Loadpage("/gui/idriss/page1");
    }

    @FXML
    private void afficher(MouseEvent event) {
        Loadpage("/gui/idriss/page2");
    }

    @FXML
    private void supprimer(MouseEvent event) {
        Loadpage("/gui/idriss/page3");
    }
    @FXML
    private void modifier(MouseEvent event) {
        Loadpage("/gui/idriss/page4");
    }
     @FXML
    private void mails(MouseEvent event) {
         Loadpage("/gui/idriss/pmails");
    }

    
    
    private void Loadpage(String page){

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void quitter(MouseEvent event) {
        Label label = null;
        Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("quitter l'application");
      alert.setHeaderText("vous etes sure de quitter");
      alert.setContentText("*****");


      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
         Platform.exit();
      } else if (option.get() == ButtonType.CANCEL) {
         //Loadpage("/gui/FXMLCandidat");
      } else {
         label.setText("-");
      }
    }

   
}

   
    
    

 
   
   
