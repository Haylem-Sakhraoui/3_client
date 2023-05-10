/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.CV;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.speech.AudioException;
import javax.speech.synthesis.Synthesizer;
import services.CVService;
import services.SynthesizerSingleton;

/**
 * FXML Controller class
 *
 * @author HAYLEM SAKHRAOUI
 */
public class editerCVController implements Initializable {

     @FXML
    private TextField filenameTextField;
    @FXML
    private Button browseButton;
    @FXML
    private Label selectedFileLabel;
    @FXML
    private Button uploadButton;
    @FXML
    private Button RETURN;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose CV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filenameTextField.setText(selectedFile.getName());
            selectedFileLabel.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void handleUploadButtonAction(ActionEvent event) {
        
        String filename = filenameTextField.getText(); 
 
        CVService cvs = new CVService();
        
    
        File file = new File(selectedFileLabel.getText());
        try {
            FileInputStream fis = new FileInputStream(file);
            CV cv = new CV();
            cv.setFilename(filename);
            cv.setFiletype("application/pdf");
            cv.setFilesize((int) file.length());
            cv.setData(fis);
        try {
            cvs.addCV(cv);
        } catch (SQLException ex) {
            Logger.getLogger(ajouterCVController.class.getName()).log(Level.SEVERE, null, ex);
        }
            selectedFileLabel.setText("CV uploaded successfully!");
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText(" cv ajoutée");
        alert.show();
        System.out.println("cv ajoutée");
        //////////
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
    private void pageEdit(ActionEvent event) {
        try {
       // Charger la vue de la page myProfile depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/haylem/editProfile.fxml"));
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
