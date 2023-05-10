/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import services.RecruteurService;
import services.UserSession;
import utiles.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ajoutdemController implements Initializable {

    @FXML
    private TextField nom_rec;
    @FXML
    private TextField exp_dem;
    @FXML
    private TextField rem_dem;
    @FXML
    private TextField tel_dem;
    @FXML
    private DatePicker date_dem;
    @FXML
    private Button button_ajouter;
    @FXML
    private TextField desc_dem;
    UserSession session;
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        u = getCurrentUser();
        System.out.println(u);
    }

    @FXML
    private void ajouter_demande(ActionEvent event) throws SQLException {

        /*
        if (tel_dem.getText().matches("\\d+")) {
            System.out.println("La chaîne est numérique");
            } else {
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
             alert3.setTitle("champ non numérique");
            alert3.setContentText("le champ telephone  doit etre de type numérique");
            alert3.showAndWait();
            }
        
            if (exp_dem.getText().matches("\\d+")) {
            System.out.println("La chaîne est numérique");
            } else {
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
             alert3.setTitle("champ non numérique");
            alert3.setContentText("le champ Experience  doit etre de type numérique");
            alert3.showAndWait();
            } */
        if (nom_rec.getText().isEmpty() || desc_dem.getText().isEmpty() || exp_dem.getText().isEmpty()
                || rem_dem.getText().isEmpty() || tel_dem.getText().isEmpty() || date_dem.getValue() == null) {
            // afficher un message d'erreur indiquant qu'un champ est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else if (!(exp_dem.getText().matches("\\d+"))) {
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("champ non numérique");
            alert3.setContentText("le champ Experience  doit etre de type numérique");
            alert3.showAndWait();

        } else if (!(tel_dem.getText().matches("\\d+"))) {
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("champ non numérique");
            alert3.setContentText("le champ telephone  doit etre de type numérique");
            alert3.showAndWait();

        } else {

            RecruteurService rs = new RecruteurService();
            Demande d;

            int exp = Integer.parseInt(exp_dem.getText());
            int tel = Integer.parseInt(tel_dem.getText());
            double rem = Double.parseDouble(rem_dem.getText());
            LocalDate localDate = date_dem.getValue();
            Date date = java.sql.Date.valueOf(localDate);

            d = new Demande(u.getId(),nom_rec.getText(), desc_dem.getText(), exp, rem, (java.sql.Date) date, tel);
            System.out.println(d);
            rs.ajouterdemande(d);

            try {
                JavaMailUtil.sendMail("mohammedkhalil.tebessi@esprit.tn");
            } catch (MessagingException ex) {
                System.out.print(ex.getMessage());
            }
            System.out.print("demande ajoutee");
        }
       
    }
      public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

}
