/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.service;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;
import services.servicesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page2Controller implements Initializable {

    private Label ShowArea;
    @FXML
    private Button afficherB;
    @FXML
    private TableView<service> tvafficher;
    @FXML
    private TableColumn<service, Integer> idservice;
    @FXML
    private TableColumn<service, String> nomservice;
    @FXML
    private TableColumn<service, Integer> nbtotfr;
    @FXML
    private TextField zonesomme;
    Connection connexion;
    Statement stm;
    @FXML
    private TextField zonerecherche;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            servicesmethodes sm = new servicesmethodes();
            
            
            List<String> nomServiceList = new ArrayList<>();
            List<service> l = sm.afficherpersonne();
            for (service obj : l) {
                nomServiceList.add(obj.getNom_service());
            }
            
            System.out.print(nomServiceList);
            
            
            String [] words = {"hhh" , "aaa" ,"aaajjj", "gggg" , "ddd"};
            TextFields.bindAutoCompletion(zonerecherche, nomServiceList);
        } catch (SQLException ex) {
            Logger.getLogger(Page2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void AfficherService(ActionEvent event) throws SQLException {
        
        
                      servicesmethodes sm = new servicesmethodes();
            List<service> l = sm.afficherpersonne();
            ObservableList<service> categories = FXCollections.observableArrayList(l);
            idservice.setCellValueFactory(new PropertyValueFactory<service, Integer> ("id_service"));
            nomservice.setCellValueFactory(new PropertyValueFactory<service, String> ("nom_service"));
            nbtotfr.setCellValueFactory(new PropertyValueFactory<service, Integer> ("nb_tot_freelance"));
            
            tvafficher.setItems(categories);
    }

    @FXML
    private void get_selected_item(MouseEvent event) {
        
    }

    @FXML
    private void calculersomme(MouseEvent event) throws SQLException {
         servicesmethodes sm = new servicesmethodes();
         int s=0;
         s=sm.calculer();
         String chaine = String.valueOf(s);
         zonesomme.setText(chaine);
        
  }

    @FXML
    private void tri(MouseEvent event) throws SQLException {
        servicesmethodes sm = new servicesmethodes();
        List<service> l = sm.listtri();
         ObservableList<service> categories = FXCollections.observableArrayList(l);
            idservice.setCellValueFactory(new PropertyValueFactory<service, Integer> ("id_service"));
            nomservice.setCellValueFactory(new PropertyValueFactory<service, String> ("nom_service"));
            nbtotfr.setCellValueFactory(new PropertyValueFactory<service, Integer> ("nb_tot_freelance"));
            
            tvafficher.setItems(categories);
    }

   
    @FXML
    private void chercherservice(MouseEvent event) throws SQLException {
        servicesmethodes sm = new servicesmethodes();
      
        
         
         String cherche= zonerecherche.getText();
        List<service> l = sm.chercher(cherche);
         ObservableList<service> categories = FXCollections.observableArrayList(l);
            idservice.setCellValueFactory(new PropertyValueFactory<service, Integer> ("id_service"));
            nomservice.setCellValueFactory(new PropertyValueFactory<service, String> ("nom_service"));
            nbtotfr.setCellValueFactory(new PropertyValueFactory<service, Integer> ("nb_tot_freelance"));
            
            tvafficher.setItems(categories);
    }
    
    
    
}
