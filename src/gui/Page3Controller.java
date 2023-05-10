/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.service;
import java.net.URL;
import java.sql.SQLException;
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
import services.servicesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page3Controller implements Initializable {

    private TextField zonenomserviceSP;
    @FXML
    private Button bsupprimer;
    @FXML
    private TableView<service> tvsupprimer;
    @FXML
    private TableColumn<service, Integer> idservice;
    @FXML
    private TableColumn<service, String> nomservice;
    @FXML
    private TableColumn<service, Integer> nbtotfr;
    @FXML
    private TextField zonesupprimer;
    int index=-1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            servicesmethodes sm = new servicesmethodes();
            List<service> l = sm.afficherpersonne();
            ObservableList<service> categories = FXCollections.observableArrayList(l);
            idservice.setCellValueFactory(new PropertyValueFactory<service, Integer> ("id_service"));
            nomservice.setCellValueFactory(new PropertyValueFactory<service, String> ("nom_service"));
            nbtotfr.setCellValueFactory(new PropertyValueFactory<service, Integer> ("nb_tot_freelance"));
            
            tvsupprimer.setItems(categories);
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(Page3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        
         try {
              servicesmethodes sm = new servicesmethodes();
        
              String conv1 = zonesupprimer.getText();
             if (zonesupprimer.getText().isEmpty()) {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("champ est vide");
        alert1.setContentText("champ est vide");
        alert1.showAndWait();
    }  else {
        sm.supprimer(conv1);
        System.out.println(" service supprimé");
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("deleted");
            alert.setContentText("service supprimé");
            alert.showAndWait();
         }
      } catch (SQLException ex) {
          ex.getMessage();
          
      }
    }

    @FXML
    private void get_selected_item(MouseEvent event) {
        
        index=tvsupprimer.getSelectionModel().getSelectedIndex();
        if (index <= -1){

        return;
        }
        zonesupprimer.setText(String.valueOf(nomservice.getCellData(index)));
    }
    
}
