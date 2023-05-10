/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JFrame;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class FXMLCandidatController implements Initializable {

    @FXML
    private Button btn_ajout_post;
    @FXML
    private Button btn_supp_post;
    @FXML
    private Button btn_mod_post;
    @FXML
    private Button btn_affich_post;
    @FXML
    private AnchorPane ap_post;
    @FXML
    private BorderPane bp_post;
    private Label label;
    @FXML
    private Button btn_home;
    @FXML
    private Text animatedtext;
        UserSession session;
    User u;
    @FXML
    private Label welcomeText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          u = getCurrentUser();
        welcomeText.setText(welcomeText.getText()+" "+u.getUsername());
         Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
        boolean visible = true;
        @Override
        public void run() {
            Platform.runLater(() -> {
                animatedtext.setVisible(visible);
                visible = !visible;
            });
        }
    }, 0, 600);
        
     
        
        
        
    }    
   public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }
    @FXML
    private void diriger_vers_ajout_post(MouseEvent event) {
        Loadpage("/gui/ajoutpost");
    }

    @FXML
    private void diriger_vers_supp_post(MouseEvent event) {
        Loadpage("/gui/supppost");
    }

    @FXML
    private void diriger_vers_mod_post(MouseEvent event) {
        Loadpage("/gui/modifpost");
    }

    @FXML
    private void diriger_vers_affich_post(MouseEvent event) {
        Loadpage("/gui/affichpost");
    }
    
    private void Loadpage(String page){
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLRecruteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp_post.setCenter(root);
    }

    @FXML
    private void quitter(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("quitter l'application");
      alert.setHeaderText("vous etes sure de quitter");
      alert.setContentText("______________");


      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
         Platform.exit();
      } else if (option.get() == ButtonType.CANCEL) {
         //Loadpage("/gui/FXMLCandidat");
      } else {
         this.label.setText("-");
      }
        
          
    }

    @FXML
    private void diriger_vers_home_candidat(MouseEvent event) {
        Loadpage("/gui/homeCandidat");
    }
    
}
