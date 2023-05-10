/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.idriss;

import entities.Commentaire;
import entities.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import services.serviceCommentaire;
import services.serviceReclamation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Page4Controller implements Initializable {

    @FXML
    private TableColumn<Commentaire, Integer> cl_idc;
    @FXML
    private TableColumn<Commentaire, Timestamp> cl_date;
    @FXML
    private TableColumn<Commentaire, String> cl_desc;
    @FXML
    private TableColumn<Commentaire, Integer> cl_idrec;
    @FXML
    private TableView<Commentaire> tvcm;
    @FXML
    private Button btm_supprimer;
    @FXML
    private TextField deletefield;
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_dsc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            cl_idc.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("id"));
            cl_idrec.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("reclamation_id"));
            cl_desc.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("description_c"));
            
            cl_date.setCellValueFactory(new PropertyValueFactory<Commentaire,Timestamp>("date_c"));
            ObservableList<Commentaire> listU = FXCollections.observableArrayList();
            serviceCommentaire ps=new serviceCommentaire();
            
        try {
            ps.affichercomm().forEach(r->{listU.add(r);});
        } catch (SQLException ex) {
            Logger.getLogger(Page4Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            tvcm.setItems(listU);
        
    }    
    
 

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         serviceCommentaire sc=new serviceCommentaire();
               

        int id = Integer.parseInt(deletefield.getText());
        sc.supprimerCommentaire(id);
       
        // System.out.print("demande supprimer ");
         JOptionPane.showMessageDialog(null,"reclamation Supprimer");
    }

    @FXML
    private void modif(ActionEvent event) throws SQLException {
        int id_R_modif = Integer.parseInt(deletefield.getText()) ;  
        serviceCommentaire sr=new serviceCommentaire();
        
      Commentaire r=new Commentaire(id_R_modif,Date.valueOf(tf_date.getText()),tf_dsc.getText());
       

        sr.modifierComm(r);
     
       
         
       
       
      // Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Information Dialog");
           // alert.setHeaderText(null);
           // alert.setContentText("Sponsor modifier avec succès!");
                 JOptionPane.showMessageDialog(null,"commentaire modifier avec succès!");
    }
    
    
}
