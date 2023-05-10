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
import services.categoriesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page4categorieController implements Initializable {

    @FXML
    private TextField nmcat;
    @FXML
    private TextField nvnmcat;
    @FXML
    private TextField nvnbser;
    @FXML
    private TableView<categorie> tvmodifier;
    @FXML
    private TableColumn<categorie, Integer> idcat;
    @FXML
    private TableColumn<categorie, String> nomcat;
    @FXML
    private TableColumn<categorie, Integer> nbfreelance;
    @FXML
    private TableColumn<categorie, Integer> idservice;
    int index=-1;

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
            nbfreelance.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("nb_tot_service"));
            idservice.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("id_categorie"));

          tvmodifier.setItems(cat);
        // TODO
    }    

    @FXML
    private void Modifercat(ActionEvent event) {
        
                          try {
              categoriesmethodes cm = new categoriesmethodes();
        
              String nom = nmcat.getText();
              String nouveaunom = nvnmcat.getText();
              String nvnombre = nvnbser.getText();
              int nbservice = Integer.parseInt(nvnombre);
        cm.modifier(nouveaunom, nbservice, nom);
        
        System.out.println("categorie modifié");
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("categorie modifié");
            alert.showAndWait();
      } catch (SQLException ex) {
          ex.getMessage();
          
      }
    }

    @FXML
    private void get_selected_item(MouseEvent event) {
        
          
        index=tvmodifier.getSelectionModel().getSelectedIndex();
        if (index <= -1){

        return;
        }
        nmcat.setText(String.valueOf(nomcat.getCellData(index)));
        nvnbser.setText(String.valueOf(nbfreelance.getCellData(index)));
        
    }
    
}
