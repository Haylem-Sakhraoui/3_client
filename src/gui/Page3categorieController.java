/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.categorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import services.categoriesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page3categorieController implements Initializable {

    private TextField nomsupp;
    @FXML
    private TableView<categorie> tbsupprimer;
    @FXML
    private TableColumn<categorie, Integer> idcat;
    @FXML
    private TableColumn<categorie, String> nomcat;
    @FXML
    private TableColumn<categorie, Integer> nbtotfree;
    @FXML
    private TableColumn<categorie, Integer> idservice;
    @FXML
    private TextField zonesupprimer;
    int index=-1;
     WebView webView = new WebView();
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriesmethodes cs = new categoriesmethodes();
          List<categorie> categorielist=new ArrayList();
        try {
            categorielist = cs.afficher();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
          ObservableList<categorie> cat = FXCollections.observableArrayList(categorielist);

          idcat.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("id_service"));
            nomcat.setCellValueFactory(new PropertyValueFactory<categorie, String> ("nom_categorie"));
            nbtotfree.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("nb_tot_service"));
            idservice.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("id_categorie"));

          tbsupprimer.setItems(cat);
        // TODO
    }    

    @FXML
    private void supprimercategorie(ActionEvent event) {
        
         try {
              categoriesmethodes cm = new categoriesmethodes();
        
              String supp = zonesupprimer.getText();
             if (zonesupprimer.getText().isEmpty()) {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("champ est vide");
        alert1.setContentText("champ est vide");
        alert1.showAndWait();
    }  else {
        cm.supprimer(supp);
        System.out.println(" categorie supprimé");
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
        
        index=tbsupprimer.getSelectionModel().getSelectedIndex();
        if (index <= -1){

        return;
        }
        zonesupprimer.setText(String.valueOf(nomcat.getCellData(index)));
    }
    

    @FXML
    private void rafraichir(ActionEvent event) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load("/gui/page3categorie");
        webEngine.reload();
        
    }
    
}
