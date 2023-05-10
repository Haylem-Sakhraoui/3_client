/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class HomeRecruteurController implements Initializable {

    @FXML
    private Label welcomeText;
      UserSession session;
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = getCurrentUser();
        welcomeText.setText(welcomeText.getText()+" "+u.getUsername());
    }    
       public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }
}
