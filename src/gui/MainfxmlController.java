/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class MainfxmlController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(MouseEvent event) {
         Loadpage("/gui/page1");
    }

    @FXML
    private void afficher(MouseEvent event) {
        Loadpage("/gui/page2");
    }

    @FXML
    private void supprimer(MouseEvent event) {
        Loadpage("/gui/page3");
        
    }
    
    @FXML
    private void modifier(MouseEvent event) {
        
        Loadpage("/gui/page4");
    }
    
    @FXML
    private void quitter(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("quitter l'application");
      alert.setHeaderText("vous etes sure de quitter");
      alert.setContentText("C:/quitter.java");

      
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
         Platform.exit();
      } else if (option.get() == ButtonType.CANCEL) {
         Loadpage("/gui/page1");
      } else {
         this.label.setText("-");
      }
    }
    
     private void Loadpage(String page){
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    

    
    
}
