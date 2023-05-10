/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import entities.User;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.UserSession;


/**
 *
 * @author Salim Ben Hamida
 */
public class JavaMailUtil {
    
   
    
    public static void sendMail(String recipient) throws MessagingException {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    String myAccountEmail = "mohammedkhalil.tebessi@esprit.tn";
    String password = "Nouridorra";

    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
        }
    });
    
    Message message =prepareMessage(session,myAccountEmail,recipient);
    Transport.send(message);
}
    
    
  

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) throws AddressException, MessagingException {
         
        Message message= new MimeMessage(session);
        message.setFrom(new InternetAddress("myAccountEmail"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient) );
        message.setSubject("mail de confirmation");
        message.setText("bonjour , Monsieur kalilrec votre demande a ete effectué avec succés \n Merci de choisir TN-job \n au revoir");
        return message;
    }
     

    
}
