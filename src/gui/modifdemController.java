/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import entities.Poste;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import services.RecruteurService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class modifdemController implements Initializable {

    private TextField tf_id_modif;
    @FXML
    private Button btn_choisir;
    
    @FXML
    private TableColumn<Demande, Integer> tv_id;
    @FXML
    private TableColumn<Demande, String> tv_desc;
    @FXML
    private TableColumn<Demande, Integer> tv_exp;
    @FXML
    private TableColumn<Demande, Double> tv_rem;
    @FXML
    private TableColumn<Demande, Date> tv_expir;
    @FXML
    private TableColumn<Demande, Integer> tv_tel;
    @FXML
    private TextField tf_id_modif_dem;
    @FXML
    private TextField tf_desc_modif_dem;
    @FXML
    private TextField tf_exp_modif_dem;
    @FXML
    private TextField tf_rem_modif_dem;
    @FXML
    private TextField tf_expir_modif_dem;
    @FXML
    private TextField tf_tel_modif_dem;
    @FXML
    private TableView<Demande> tv_modif_dem;
    int index =-1;
    UserSession session;
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              u = getCurrentUser();
        RecruteurService rs = new RecruteurService();
          List<Demande> demandesList = new ArrayList();
        try {
            demandesList = rs.afficherdemande(u.getId());
        } catch (SQLException ex) {
            Logger.getLogger(modifdemController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Demande> demandes = FXCollections.observableArrayList(demandesList);
         
           tv_id.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("id_demande"));
          tv_desc.setCellValueFactory(new PropertyValueFactory<Demande, String> ("description"));
          tv_exp.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("experience"));
          tv_rem.setCellValueFactory(new PropertyValueFactory<Demande, Double> ("remuneration"));
          tv_expir.setCellValueFactory(new PropertyValueFactory<Demande, Date> ("expiration"));
           tv_tel.setCellValueFactory(new PropertyValueFactory<Demande, Integer> ("telephone"));
          tv_modif_dem.setItems(demandes);
    }    

    @FXML
    private void Modifier_demande(ActionEvent event) throws SQLException, ParseException {
        
        
    if( tf_id_modif_dem.getText().isEmpty() ||  tf_desc_modif_dem.getText().isEmpty()||  tf_exp_modif_dem.getText().isEmpty()||  tf_rem_modif_dem.getText().isEmpty()||  tf_expir_modif_dem.getText().isEmpty() || tf_tel_modif_dem.getText().isEmpty()){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }
    else {
        
        RecruteurService rs = new RecruteurService();
        int i_d = Integer.parseInt(tf_id_modif_dem.getText());
        String d_escrip =tf_desc_modif_dem.getText() ;
        int e_xperience =Integer.parseInt(tf_exp_modif_dem.getText()) ;
        Double r_em =Double.parseDouble(tf_rem_modif_dem.getText()); ;
        String e_xpire_str = tf_expir_modif_dem.getText(); // suppose que tf_e_xpire_modif_dem est un champ texte pour la date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // définition du format de date
        java.sql.Date e_xpire = new java.sql.Date(sdf.parse(e_xpire_str).getTime());
        int t_el = Integer.parseInt(tf_tel_modif_dem.getText());
        
        rs.modifierdemande( d_escrip , e_xperience, r_em, t_el , e_xpire, i_d);
         System.out.print("demande modifié ");
    }
        
    }

    @FXML
    private void get_selected_item(MouseEvent event) {
        index=tv_modif_dem.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
        return;
        }
        tf_id_modif_dem.setText(tv_id.getCellData(index).toString());
        tf_desc_modif_dem.setText(tv_desc.getCellData(index).toString());
        tf_exp_modif_dem.setText(tv_exp.getCellData(index).toString());
        tf_rem_modif_dem.setText(tv_rem.getCellData(index).toString());
        tf_expir_modif_dem.setText(tv_expir.getCellData(index).toString());
        tf_tel_modif_dem.setText(tv_tel.getCellData(index).toString());
        
    }
    
       public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

     
}
