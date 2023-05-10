/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import com.google.zxing.WriterException;
import entities.Competences;
import entities.Experiences;
import entities.Scolarite;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.management.Notification;
import javax.management.relation.Role;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import services.CompeService;
import services.ExperService;
import services.ScolarService;
import services.SynthesizerSingleton;

import utiles.QRCodeGenerator;

/**
 * FXML Controller class
 *
 * @author HAYLEM SAKHRAOUI
 */
public class MyProfileController implements Initializable {

    @FXML
    private TableView<Competences> competenceTable;
    @FXML
    private TableView<Experiences> experienceTable;
    @FXML
    private TableColumn<Experiences, String> entrepriseColumn;
    @FXML
    private TableColumn<Experiences, String> posteColumn;
    @FXML
    private TableView<Scolarite> educationTable;
    @FXML
    private TableColumn<Scolarite, String> etablissementColumn;
    @FXML
    private TableColumn<Scolarite, Date> dateObtentionColumn;
    @FXML
    private TableColumn<Competences,String> competenceColumn;
    @FXML
    private TableColumn<Experiences, Date> dateDebutColumn;
    @FXML
    private TableColumn<Experiences, Date> dateFinColumn;
    @FXML
    private TableColumn<Scolarite, String> villeColumn;
    @FXML
    private TableColumn<Scolarite, String> paysColumn;
    @FXML
    private TableColumn<Scolarite, String> diplomeColumn;
    @FXML
    private TableColumn<Competences, Integer> IDcmp;
    @FXML
    private TableColumn<Experiences, Integer> IDexp;
    @FXML
    private TableColumn<Scolarite, Integer> IDedu;
    @FXML
    private Button editProfile;
    @FXML
    private ImageView qrcode;
    @FXML
    private Label username;
    @FXML
    private Button notif;
    @FXML
    private Button stat;
    
    private ObservableList<Competences> competenceData = FXCollections.observableArrayList();
    private ObservableList<Experiences> experienceData = FXCollections.observableArrayList();
    private ObservableList<Scolarite> educationData = FXCollections.observableArrayList();
    @FXML
    private Button accueil;

    /////list of recruiters 
    
    private List<String> recruiterList = new ArrayList<>();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        notif.setOnAction(event -> {
    // Get the username of the recruiter who visited the profile
    String recruiterUsername = ""; // Replace with code to get username
    // Add the recruiter's username to the list
    recruiterList.add(recruiterUsername);
    /////////clipboard 
    
});
        
        // Set the text of the username label
//    Freelancer freelancer = getFreelancerProfile(); // Replace with code to retrieve the freelancer's profile
//    String freelancerUsername = freelancer.getUsername();
//    username.setText( freelancerUsername);
             
        //loadalldata/////////
        loadCompetenceData();
        loadExperienceData();
        loadEducationData();
        
    }
    // Define methods to load data into each table view
    private void loadCompetenceData() {
        
        IDcmp.setCellValueFactory(new PropertyValueFactory<Competences, Integer> ("id_comp"));
        competenceColumn.setCellValueFactory(new PropertyValueFactory<Competences, String> ("nom"));
        try {
            CompeService compeService = new CompeService();
            List<Competences> competenceList = compeService.afficherCompetences();
            competenceData.clear();
            competenceData.addAll(competenceList);
            competenceTable.setItems(competenceData);
        } catch (SQLException e) {
            // Handle exception here
        }
    }
    
    private void loadExperienceData() {
        IDexp.setCellValueFactory(new PropertyValueFactory<Experiences, Integer> ("id_exp"));
        entrepriseColumn.setCellValueFactory(new PropertyValueFactory<Experiences, String>("nom_entreprise"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<Experiences, String> ("poste"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<Experiences, Date> ("date_debut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<Experiences, Date> ("date_fin"));
        try {
            ExperService experService = new ExperService();
            List<Experiences> experienceList = experService.afficherExperience();
            experienceData.clear();
            experienceData.addAll(experienceList);
            experienceTable.setItems(experienceData);
        } catch (SQLException e) {
            // Handle exception here
        }
    }

    private void loadEducationData() {
        IDedu.setCellValueFactory(new PropertyValueFactory<Scolarite, Integer> ("id_etab"));
        etablissementColumn.setCellValueFactory(new PropertyValueFactory<Scolarite, String>("nom_etablissement"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<Scolarite, String> ("ville"));
        paysColumn.setCellValueFactory(new PropertyValueFactory<Scolarite, String> ("pays"));
        diplomeColumn.setCellValueFactory(new PropertyValueFactory<Scolarite, String> ("diplome"));
        dateObtentionColumn.setCellValueFactory(new PropertyValueFactory<Scolarite, Date> ("date_obtention"));
        try {
            ScolarService scolarService = new ScolarService();
            List<Scolarite> educationList = scolarService.afficherScolar();
            educationData.clear();
            educationData.addAll(educationList);
            educationTable.setItems(educationData);
        } catch (SQLException e) {
            // Handle exception here
        }
    }
        
    @FXML
    private void editProfileBtn(ActionEvent event) {
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

    @FXML
    private void generateQrcode(MouseEvent event) {
        
        String url = "https://www.freelancer-profile.com"; // replace with the URL of the freelancer's profile
    try {
        QRCodeGenerator.generateQRCodeImage(url, 450, 450, "./qr-code.png"); // generates a QR code image and saves it as qr-code.png

        // Load the QR code image as a BufferedImage
        BufferedImage bImage = ImageIO.read(new File("./qr-code.png"));

        // Convert the image to an RGB format
        BufferedImage rgbImage = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        rgbImage.createGraphics().drawImage(bImage, 0, 0, Color.WHITE, null);

        // Convert the image to a JavaFX Image
        Image qrCodeImage = SwingFXUtils.toFXImage(rgbImage, null);

        // Place the image in the clipboard
        ClipboardContent content = new ClipboardContent();
        content.putImage(qrCodeImage);
        Clipboard.getSystemClipboard().setContent(content);
        
        
        // Display the QR code image in the ImageView
        qrcode.setImage(qrCodeImage);
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText("ajoutée au clipboard");
        alert.show();
        System.out.println("ajoutée au clipboard");

    } catch (WriterException | IOException ex) {
        // handle exception here
    }
   
    }

    @FXML
    private void receiveNotif(ActionEvent event) {
        
        // Get the last recruiter's username from the list
    String lastRecruiter = recruiterList.get(recruiterList.size() - 1);
    // Display the username in a dialog box
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Last Recruiter");
    alert.setHeaderText("The last recruiter who visited your profile was:");
    alert.setContentText(lastRecruiter);
    alert.showAndWait();
       
}



    @FXML
    private void voirStat(ActionEvent event) {
    }

    @FXML
    private void accueilbutton(ActionEvent event) {
        try {
        // Charger la vue de la page Edit depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/userDashboard.fxml")); //partie integration 
        Parent editView = loader.load();
        
        // Créer une nouvelle scène et y ajouter la vue de la page Edit
        Scene editScene = new Scene(editView);
        
        // Récupérer la scène actuelle et y changer la vue par celle de la page Edit
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(editScene);
        currentStage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
