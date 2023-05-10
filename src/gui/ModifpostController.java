/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Poste;
import entities.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.CandidatService;
import services.UserSession;


/**
 * FXML Controller class
 *
 * @author amine
 */
public class ModifpostController implements Initializable {

    
    @FXML
    private Button btn_mod_post;
    @FXML
    private TableView<Poste> tv_modif_post;
    @FXML
    private TableColumn<Poste, Integer> cl_id_modif_post;
    @FXML
    private TableColumn<Poste, Integer> cl_exp_modif_post;
    @FXML
    private TableColumn<Poste, String> cl_id_desc_post;
    int index =-1;
    @FXML
    private TextField tf_id_post_modif;
    @FXML
    private TextField tf_exp_post_modif;
    @FXML
    private TextField tf_desc_post_modif;
UserSession session;
    User u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          u = getCurrentUser();
        
         CandidatService cs = new CandidatService();
          List<Poste> postesList=new ArrayList();
        try {
            postesList = cs.afficherPoste(u.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SupppostController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Poste> postes = FXCollections.observableArrayList(postesList);
         
          cl_id_modif_post.setCellValueFactory(new PropertyValueFactory<Poste, Integer> ("id_poste"));
          cl_exp_modif_post.setCellValueFactory(new PropertyValueFactory<Poste, Integer> ("experience"));
          cl_id_desc_post.setCellValueFactory(new PropertyValueFactory<Poste, String> ("description"));
          
          tv_modif_post.setItems(postes);
    }    

    @FXML
    private void modif_post(ActionEvent event) throws SQLException {  
        if(tf_id_post_modif.getText().isEmpty() || tf_exp_post_modif.getText().isEmpty() || tf_desc_post_modif.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("selectionner la poste a modifier !");
        alert.showAndWait();
    }
    else {
        
        CandidatService cs = new CandidatService ();
       int id = Integer.parseInt(tf_id_post_modif.getText());
       int exp = Integer.parseInt(tf_exp_post_modif.getText());
       String desc = tf_desc_post_modif.getText();
        
       
        cs.modifierPoste(id, exp, desc);
         System.out.print("demande modifié ");
    } 
    }
    
  
    
    
    /*
    
     public static ObservableList<evenementLocal> getDataEvenement(){
        
    
    ObservableList<evenementLocal>list = FXCollections.observableArrayList();
    try {
            
            String requete1 = "SELECT * FROM evenement_local ";
            Statement st = new MYconnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete1);
            
            while (rs.next()){
                
                Poste p = new Poste();
                p.setId_poste(rs.getInt(1));
                p.setExperience(rs.getString("experience"));
                p.setDescription(rs.getDate("description"));
                
                p.toString();
             
               list.add(p);
                
            
         }*/

    @FXML
    private void get_selected_item(MouseEvent event) {
         index=tv_modif_post.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
        return;
        }
        tf_id_post_modif.setText(cl_id_modif_post.getCellData(index).toString());
        tf_exp_post_modif.setText(cl_exp_modif_post.getCellData(index).toString());
        tf_desc_post_modif.setText(cl_id_desc_post.getCellData(index).toString());
    }

       public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }
    
}
