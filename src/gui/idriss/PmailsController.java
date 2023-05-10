/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import services.serviceReclamation;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.mail.Transport;
import javax.mail.*;
import javax.swing.JOptionPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PmailsController implements Initializable {

    private TextField email;
    @FXML
    private TextField email2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void mailing(TextField t){
       /* String username = "idrissesprit@gmail.com";
        String password = "dgoqjjinbtcbnzeq";
        //String password = "gknyjhlywynrnrbd";

        // Paramètres du serveur SMTP de Gmail
        String host = "smtp.gmail.com";
        int port = 587;

        // Propriétés pour activer la connexion TLS
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
   
       props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Créer une session avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Créer un message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
          //  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alex63amgg@gmail.com"));
          String usermail = t.getText();
          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usermail));
            message.setSubject("Réclamation");
            message.setText("Merci pour votre réclamation.");

            // Envoyer le message
            Transport.send(message);

            // Afficher un message de confirmation
            System.out.println("Email envoyé avec succès.");
            JOptionPane.showMessageDialog (null, "message  sent");
        } catch (MessagingException e) {
            // En cas d'erreur, afficher un message d'erreur
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            JOptionPane.showMessageDialog (null, e);
            JOptionPane.showMessageDialog (null, "message not sent");
       
        // mail yetbaath ikolek yosletna el reclamation}*/
      
       final String username = "idrissesprit@gmail.com";
        final String password = "dgoqjjinbtcbnzeq";

        // Paramètres du serveur SMTP de Gmail
        String host = "smtp.gmail.com";
        int port = 587;

        // Propriétés pour activer la connexion TLS
        Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.starttls.enable", "true");

        // Créer une session avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Créer un message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alex63amgg@gmail.com"));
          String usermail = email2.getText();
          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usermail));
            message.setSubject("Reclamation");
            message.setText("Reclamation bien reçue !");

            // Envoyer le message
            Transport.send(message);

            // Afficher un message de confirmation
            System.out.println("Email envoyé avec succès.");
            JOptionPane.showMessageDialog (null, "message  sent");
        } catch (MessagingException e) {
            // En cas d'erreur, afficher un message d'erreur
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            JOptionPane.showMessageDialog (null, e);
            JOptionPane.showMessageDialog (null, "message not sent");
       
        // mail yetbaath ikolek yosletna el reclamation
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Drone ajoutée!");
        alert.show();
       
        System.out.println("drone ajoutée");
    }
       
    }
    private void mail(MouseEvent event) {
        mailing(email);
    }



   @FXML
    private void salur(ActionEvent event) {
        mailing(email);
    }
    
}
