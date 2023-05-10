/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Poste;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CandidatService;
import services.UserSession;



/**
 * FXML Controller class
 *
 * @author amine
 */
public class AffichpostController implements Initializable {

    @FXML
    private TableView<Poste> tb_affich_post;
    @FXML
    private Button btn_affich_post;
    @FXML
    private TableColumn<Poste, Integer> cl_id_post;
    @FXML
    private TableColumn<Poste, Integer> cl_exp_post;
    @FXML
    private TableColumn<Poste, String> cl_desc_post;
UserSession session;
    User u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = getCurrentUser();
    }    
       public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

    @FXML
    private void afficher_post(ActionEvent event) throws SQLException {
         CandidatService cs = new CandidatService();
          List<Poste> postesList = cs.afficherPoste(u.getId());
          ObservableList<Poste> postes = FXCollections.observableArrayList(postesList);
         
          cl_id_post.setCellValueFactory(new PropertyValueFactory<Poste, Integer> ("id_poste"));
          cl_exp_post.setCellValueFactory(new PropertyValueFactory<Poste, Integer> ("experience"));
          cl_desc_post.setCellValueFactory(new PropertyValueFactory<Poste, String> ("description"));
          
          tb_affich_post.setItems(postes);
    }
    
}
