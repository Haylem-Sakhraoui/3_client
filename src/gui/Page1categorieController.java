/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.categorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.categoriesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page1categorieController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TextField zonenomcat;
    @FXML
    private TextField zonenbtotservice;
    @FXML
    private TextField zoneidservice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajoutercategorie(ActionEvent event) {
        
        categoriesmethodes cm = new categoriesmethodes();
        String idnb = zonenbtotservice.getText();
        String idser = zoneidservice.getText();
        
        
           try {
                  int id = Integer.parseInt(idnb);
                  int idse = Integer.parseInt(idser);
              categorie cat = new categorie(idse,zonenomcat.getText(),id);
              cm.ajoutecategorie(cat);
                 System.out.println("ajouté");
                 Alert alert=new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Success");
             alert.setContentText("categorie ajouté");
                 alert.showAndWait();
      } catch (SQLException ex) {
          ex.getMessage();
          
      }
    }
    
}
