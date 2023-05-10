/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.service;
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
import services.servicesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page1Controller implements Initializable {

    @FXML
    private TextField zonenomservice;
    @FXML
    private TextField zonenbservice;
    @FXML
    private Button Aservice;
    @FXML
    private Text text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterService(ActionEvent event) {
        
          
              servicesmethodes sm = new servicesmethodes();
        
              String conv1 = zonenbservice.getText();
              
              if (zonenbservice.getText().isEmpty()) {
        Alert alert2=new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("champ est vide");
        alert2.setContentText("champ nombre de service est vide");
        alert2.showAndWait();
                  
              }
              
              if (zonenbservice.getText().matches("\\d+")) {
    System.out.println("La chaîne est numérique");
} else {
          Alert alert3=new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("champ non numérique");
        alert3.setContentText("champ nombre de service non numérique");
        alert3.showAndWait();   
         }
              
              
            
              
              if (zonenomservice.getText().isEmpty()) {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("champ est vide");
        alert1.setContentText("champ nom de service est vide");
        alert1.showAndWait();}
              
              
  
              
                  
              
                  
               else {
       
              
              try {
                  int nbservice = Integer.parseInt(conv1);
              service se = new service(zonenomservice.getText(), nbservice);
        sm.ajouteservice(se);
        System.out.println("ajouté");
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("service ajouté");
            alert.showAndWait();
      } catch (SQLException ex) {
          ex.getMessage();
          
      }
               }
    }
    
    }
    

