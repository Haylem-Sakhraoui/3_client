/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Competences;
import entities.Scolarite;
import entities.Experiences;
import java.awt.color.ColorSpace;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import javax.speech.AudioException;
import javax.speech.synthesis.Synthesizer;
import services.CompeService;
import services.ExperService;
import services.ScolarService;
import services.SynthesizerSingleton;

/**
 * FXML Controller class
 *
 * @author HAYLEM SAKHRAOUI
 */
public class EditProfileController implements Initializable {

    @FXML
    private Button modifierCmp;
    @FXML
    private Button modifierEducation;
    @FXML
    private Button modifierExperience;
    @FXML
    private TextField editCmp;
    @FXML
    private TextField nomentreprise;
    @FXML
    private TextField nomEtab;
    @FXML
    private TextField poste;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField ville;
    @FXML
    private TextField pays;
    @FXML
    private TextField diplome;
    @FXML
    private DatePicker dateO;
    @FXML
    private TextField id_cmp;
    @FXML
    private TextField id_scol;
    @FXML
    private TextField id_exp;
    @FXML
    private TextField cvTextField;
    @FXML
    private Button uploadButton;
    @FXML
    private Button annuler;
    @FXML
    private Button suppCmp;
    @FXML
    private Button suppExp;
    @FXML
    private Button suppEdu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void modifierCompetence(ActionEvent event) throws SQLException {
        CompeService cs = new CompeService();
        Competences c = new Competences(Integer.parseInt(id_cmp.getText()),editCmp.getText());
        cs.modifierCompetence(c);
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" competence modifiée");
        alert.show();
        System.out.println("competence modifiée");
        
        ////////
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
    private void modifierScolar(ActionEvent event) {
        ScolarService Sc = new ScolarService();
        Scolarite s =new Scolarite(Integer.parseInt(id_scol.getText()), nomEtab.getText(), ville.getText(), pays.getText(), diplome.getText(),Date.valueOf(dateO.getValue()));
        try {
            Sc.modifierScolar(s);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" education modifiée");
        alert.show();
        System.out.println("education modifiée"); 
        /////////////
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
    private void modifierExperience(ActionEvent event) {
        ExperService Exp =new ExperService();
        Experiences exp =new Experiences(Integer.parseInt(id_exp.getText()),nomentreprise.getText(), poste.getText(), Date.valueOf(dateD.getValue()),Date.valueOf(dateF.getValue()));
        
        try {
            Exp.modifierExperiences(exp);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" experience modifiée");
        alert.show();
        System.out.println("education modifiée"); 
        //////////////
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
    private void uploadCV(ActionEvent event) {
                try {
       // Charger la vue de la page myProfile depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editerCV.fxml"));
            Parent myProfileView = loader.load();

            // Créer une nouvelle scène et y ajouter la vue de la page edtit
            Scene editerCVScene = new Scene(myProfileView);

            // Récupérer la scène actuelle
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Créer une nouvelle fenêtre et y ajouter la scène
            Stage newStage = new Stage();
            newStage.setScene(editerCVScene);

            // Fermer la fenêtre courante (createProfile.fxml) après que la nouvelle fenêtre est été affichée
            newStage.setOnShown(e -> {
                currentStage.close();
            });

            // Afficher la nouvelle fenêtre
            newStage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    @FXML
    private void returnProfile(ActionEvent event) {
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
    }

    @FXML
    private void supprimerCompetence(ActionEvent event) {
        
        CompeService cs = new CompeService();
        Competences c = new Competences(Integer.parseInt(id_cmp.getText()),editCmp.getText());
        try {
            cs.supprimerCompetence(c);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" competence supprimée");
        alert.show();
        System.out.println("competence supprimée");
        ///////////
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
    private void supprimerExperience(ActionEvent event) {
        ExperService Exp =new ExperService();
        Experiences exp =new Experiences(Integer.parseInt(id_exp.getText()),nomentreprise.getText(), poste.getText(), Date.valueOf(dateD.getValue()),Date.valueOf(dateF.getValue()));
        
        try {
            Exp.supprimerExperiences(exp);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" experience supprimée");
        alert.show();
        System.out.println("education supprimée"); 
        /////////////
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
    private void supprimerScolar(ActionEvent event) {
        ScolarService Sc = new ScolarService();
        Scolarite s =new Scolarite(Integer.parseInt(id_scol.getText()), nomEtab.getText(), ville.getText(), pays.getText(), diplome.getText(),Date.valueOf(dateO.getValue()));
        try {
            Sc.supprimerScolar(s);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" education supprimée");
        alert.show();
        System.out.println("education supprimée"); 
        ///////////
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
    
}
