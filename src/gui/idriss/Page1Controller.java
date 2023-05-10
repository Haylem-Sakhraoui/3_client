/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.idriss;

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

/*import javax.mail.Message;
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
import javax.swing.JOptionPane;*/


import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author User
 */
public class Page1Controller implements Initializable {

    public static final String ACCOUNT_SID = "AC00aec34c479eac1a92228c238b613295";
    public static final String AUTH_TOKEN = "9eeb38a4bb12e019af092562460917ab";
    public static final String TWILIO_NUMBER = "+15074195292";
    
    @FXML
    private DatePicker zonedate;
    @FXML
    private TextArea listeaff;
    @FXML
    private TextField textfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
        // int id = 0;
     //   LocalDate localDate = zonedate.getValue();
       // Date date = java.sql.Date.valueOf(localDate);
        // String s= listeaff.getText();
        //Date a=new Date(2022-11-10) ;
        if(listeaff.getText().isEmpty())  {
        // afficher un message d'erreur indiquant qu'un champ est vide
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setTitle("Erreur");
        alert1.setHeaderText(null);
        alert1.setContentText("Veuillez remplir la description!");
        alert1.showAndWait();}
        
       
        else if(validateDatePicker(zonedate)) {
      
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
       alert.setHeaderText(null);
      
        alert.setContentText("veuillez remplir la date");
        
        alert.show();
        
    }else{
            System.out.println("done");
             serviceReclamation sr=new serviceReclamation();
              Reclamation r=new Reclamation(Date.valueOf(zonedate.getValue()),listeaff.getText());
              
            System.out.println(r);
           // Alert alert=new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Success");
        //alert.setContentText("reclamation successfully created!");
        //alert.show();
       notif("TN-JOB","reclamation ajoutée");
        System.out.println("reclamation ajoutée");
        }}
    /*public void mailing(TextField t){
        String username = "idrissesprit@gmail.com";
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
       
        // mail yetbaath ikolek yosletna el reclamation
    }}*/
    public boolean validateDatePicker(DatePicker date)
    {
          if(date.getEditor().getText().isEmpty())
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir la date ");
            alert.showAndWait();
            return true;
         }
           return false;
        }

   /* @FXML
   /* private void mail(MouseEvent event) {
        mailing(email);
    }*/

  /*  @FXML
  /*  private void salur(ActionEvent event) {
        mailing(email);
    }*/
    public void notif(String title, String text){
   Image img = new Image("/gui/idriss/tnjob.png");
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
            .graphic(new ImageView(img))
            .hideAfter(Duration.seconds(15))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.show();
}

    @FXML
    private void sendSMS(ActionEvent event) {
        String toPhoneNumber = textfield.getText();
  /*  if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
        statusLabel.setText("Please enter a phone number.");
        return;
    }*/
   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
   
    String messageText = "Bonjour, votre réclamation est bien reçue. Merci de votre confiance !" ;
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();
    
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText(" Message envoyé avec succès");
            alert.show();
    }

    @FXML
    private void mail(MouseEvent event) {
    }

}
