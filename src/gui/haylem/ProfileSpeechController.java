package GUI;





import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.SynthesizerSingleton;

public class ProfileSpeechController {


    private Synthesizer synthesizer;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label speechLabel1;
    @FXML
    private Label speechLabel;
    
    private String textspeech;
    @FXML
    private Label speechLabel2;

    public void initialize() throws AudioException, IllegalArgumentException, InterruptedException {

    
        
        
                    Synthesizer synthesizer = null;
        try {
            synthesizer = SynthesizerSingleton.getInstance();
        } catch (AudioException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    synthesizer.speakPlainText("Thank you for being here."
            + " Please click to the Create Profile button to continue.", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    

    @FXML
    private void gotoprofile(ActionEvent event) {
        
        try {
       // Charger la vue de la page myProfile depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/haylem/createProfile.fxml"));
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
        
        
    }