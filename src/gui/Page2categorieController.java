/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.categorie;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.categoriesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Page2categorieController implements Initializable {

    private Label ShowArea;
    @FXML
    private Button button;
    @FXML
    private TableView<categorie> tvcategorie;
    @FXML
    private TableColumn<categorie, Integer> clid;
    @FXML
    private TableColumn<categorie, String> clnom;
    @FXML
    private TableColumn<categorie, Integer> clnbr;
    @FXML
    private TableColumn<categorie, Integer> clidserv;
    @FXML
    private TextField zonesommenb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }    

    @FXML
    private void Affichercategorie(ActionEvent event) throws SQLException {
        
         
              
              
               
             categoriesmethodes cm = new categoriesmethodes();
            List<categorie> l = cm.afficher();
            ObservableList<categorie> categories = FXCollections.observableArrayList(l);
            clid.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("id_service"));
            clnom.setCellValueFactory(new PropertyValueFactory<categorie, String> ("nom_categorie"));
            clnbr.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("nb_tot_service"));
            clidserv.setCellValueFactory(new PropertyValueFactory<categorie, Integer> ("id_categorie"));
            tvcategorie.setItems(categories);
    }

    @FXML
    private void calculersomme(MouseEvent event) throws SQLException {
     categoriesmethodes cm = new categoriesmethodes();
    int s=0;
     s=cm.calculer();
       String chaine = String.valueOf(s);
         zonesommenb.setText(chaine);
    }

    @FXML
    private void genererPDF(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\lenovo\\Desktop\\generer.pdf"));
        doc.open();
        doc.add(new Paragraph("bonjour"));
        doc.add(new Paragraph("------------"));
        doc.add(new Paragraph("application tnjob"));
        doc.close();
        Desktop.getDesktop().open(new File ("C:\\Users\\lenovo\\Desktop\\generer.pdf"));
        
    }

    @FXML
    private void mail(MouseEvent event) {
      
        System.out.print("email en cours");
         final String username = "thameurboukhris8@gmail.com";
                final String password = "tskohxfhijikjcua";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("thameurboukhris8@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("thamer.boukhris@esprit.tn"));
                    message.setSubject("Test JavaFX Email");
                    message.setText("Ceci est un email envoyé à partir d'une application JavaFX.");

                    Transport.send(message);

                    System.out.println("Email envoyé avec succès");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        

    
}
   

