/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Competences;
import entities.Scolarite;
import entities.Experiences;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import services.CompeService;
import services.ScolarService;
import services.ExperService;
import services.SynthesizerSingleton;

/**
 * FXML Controller class
 *
 * @author HAYLEM SAKHRAOUI
 */
public class CreateProfileController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextField Acompetence;
    @FXML
    private TextField AnomEntreprise;
    @FXML
    private TextField Aposte;
    @FXML
    private DatePicker AdateD;
    @FXML
    private DatePicker AdateF;
    @FXML
    private TextField Aville;
    @FXML
    private TextField Apays;
    @FXML
    private TextField Adiplome;
    @FXML
    private DatePicker AdateObtention;
    @FXML
    private TextField A_Edu_id;
    @FXML
    private TextField A_Exp_id;
    @FXML
    private TextField A_Cmp_id;
    @FXML
    private TextField Aetab;
    @FXML
    private Button idcv;
    @FXML
    private Button ajoutCmp;
    @FXML
    private Button ajoutExperience;
    @FXML
    private Button ajoutEducation;
    
    
    
    private Thread speechThread;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            Synthesizer synthesizer = null;
        try {
            synthesizer = SynthesizerSingleton.getInstance();
        } catch (AudioException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    synthesizer.speakPlainText("good "
            + " Please fill in all the requested information to complete your profile.", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterCompetence (ActionEvent event) throws SQLException {
        CompeService cs = new CompeService();
        Competences c = new Competences(Integer.parseInt(A_Cmp_id.getText()),Acompetence.getText());
        cs.ajouterCompetence(c);
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" competence ajoutée");
        alert.show();
        System.out.println("competence ajoutée");
         Synthesizer synthesizer = null;
        try {
            synthesizer = SynthesizerSingleton.getInstance();
        } catch (AudioException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    synthesizer.speakPlainText("nice"
            + " ", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterScolar(ActionEvent event) {
        ScolarService ss =new ScolarService();
        Scolarite St = new Scolarite(Integer.parseInt(A_Edu_id.getText()), Aetab.getText(),Aville.getText() , Apays.getText(), Adiplome.getText(),Date.valueOf(AdateObtention.getValue())) ;
        try {
            ss.ajouterScolar(St);
        } catch (SQLException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" Scolarité ajoutée");
        alert.show();
        System.out.println("Scolarité ajoutée");
         Synthesizer synthesizer = null;
        try {
            synthesizer = SynthesizerSingleton.getInstance();
        } catch (AudioException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    synthesizer.speakPlainText("nice"
            + " ", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterExperience(ActionEvent event) {
        ExperService EXP =new ExperService();
        Experiences exp = new Experiences(Integer.parseInt(A_Exp_id.getText()), AnomEntreprise.getText(), Aposte.getText(), Date.valueOf(AdateD.getValue()),  Date.valueOf(AdateF.getValue()));
        
        try {
            EXP.ajouterExperience(exp);
        } catch (SQLException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" Expériences ajoutée");
        alert.show();
        System.out.println("Expériences ajoutée");
         Synthesizer synthesizer = null;
        try {
            synthesizer = SynthesizerSingleton.getInstance();
        } catch (AudioException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    synthesizer.speakPlainText("nice"
            + " ", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void retourProfile(ActionEvent event) throws AudioException {

        
        try {
            // Charger la vue de la page myProfile depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/haylem/myProfile.fxml"));
            Parent myProfileView = loader.load();

            // Créer une nouvelle scène et y ajouter la vue de la page myProfile
            Scene myProfileScene = new Scene(myProfileView);

            // Récupérer la scène actuelle
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Créer une nouvelle fenêtre et y ajouter la scène
            Stage newStage = new Stage();
            newStage.setScene(myProfileScene);

            // Fermer la fenêtre courante (createProfile.fxml) après que la nouvelle fenêtre est été affichée
            newStage.setOnShown(e -> {
                currentStage.close();
            });

            // Afficher la nouvelle fenêtre
            newStage.show();

        
    } catch (IOException e) {
        e.printStackTrace();
    }
        Synthesizer synthesizer = null;
        try {
            synthesizer = SynthesizerSingleton.getInstance();
        } catch (AudioException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    synthesizer.speakPlainText("Awesome ! "
            + "Welcome to your profile", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @FXML
    private void uploadCv(ActionEvent event) {
        try {
            // Charger la vue de la page myProfile depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/haylem/ajouterCV.fxml"));
            Parent myProfileView = loader.load();

            // Créer une nouvelle scène et y ajouter la vue de la page myProfile
            Scene myProfileScene = new Scene(myProfileView);

            // Récupérer la scène actuelle
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Créer une nouvelle fenêtre et y ajouter la scène
            Stage newStage = new Stage();
            newStage.setScene(myProfileScene);

            // Fermer la fenêtre courante (createProfile.fxml) après que la nouvelle fenêtre est été affichée
            newStage.setOnShown(e -> {
                currentStage.close();
            });

            // Afficher la nouvelle fenêtre
            newStage.show();

        
    } catch (IOException e) {
    }

    }


    }
    
