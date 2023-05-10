/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Poste;
import entities.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CandidatService;
import services.UserSession;
import utiles.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutpostController implements Initializable {

    @FXML
    private TextField tf_exp_post;
    @FXML
    private TextArea ta_desc_post;
    @FXML
    private Button btn_postuler;
    @FXML
    private Button btn_gener_pdf;
    UserSession session;
    User u ;
    @FXML
    private ComboBox<Integer> cb_id_demande;
     int selectedValue=0;
          /**
             * Initializes the controller class.
             */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = getCurrentUser();
        
        
        
                    // Create an empty observable list to hold the ids
            ObservableList<Integer> ids = FXCollections.observableArrayList();
            Connection connexion;
                connexion = MyDB.getInstance().getConnexion();
                Statement stm = null;
        try {
            stm = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutpostController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
;
            // Execute a database query to retrieve the ids from the demande table
            ResultSet rs=null;
        try {
            rs = stm.executeQuery("SELECT id_demande  FROM demande");
        } catch (SQLException ex) {
            Logger.getLogger(AjoutpostController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Loop through the result set and add the ids to the observable list
            while (rs.next()) {
                ids.add(rs.getInt("id_demande"));   
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
                 
        }

            // Set the items of the ComboBox to the ids in the observable list
            cb_id_demande.setItems(ids);
    }

    public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

    @FXML
    private void ajout_post(ActionEvent event) throws SQLException {
        
        if (cb_id_demande.getSelectionModel().getSelectedItem()==null){selectedValue=0;}else{selectedValue = cb_id_demande.getSelectionModel().getSelectedItem();}
        
        if ( selectedValue==0 || tf_exp_post.getText().isEmpty() || ta_desc_post.getText().isEmpty()) {

            // afficher un message d'erreur indiquant qu'un champ est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        } else if (!(tf_exp_post.getText().matches("\\d+"))) {
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("champ non numérique");
            alert3.setContentText("le champ Experience  doit etre de type numérique");
            alert3.showAndWait();

        } else {
            
            CandidatService cs = new CandidatService();
            Poste p;

            
            int exp = Integer.parseInt(tf_exp_post.getText());
            String desc = ta_desc_post.getText();

            p = new Poste(0, exp, desc, u.getId());
            cs.ajouterPoste(p, selectedValue);
            System.out.print("poste ajoutee");
        }
    }

    @FXML
    private void generer_pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
    Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("D:\\Program Files\\Java\\Projects\\PidevPersonne\\src\\Pdfposte.pdf"));
        doc.open();
        Image img = Image.getInstance("D:\\Program Files\\Java\\Projects\\PidevPersonne\\src\\gui\\images\\logo.jpg");

        doc.add(img);
        doc.add(new Paragraph("bonjour MR."+u.getUsername()) );
        doc.add(new Paragraph("__"));
        doc.add(new Paragraph("application tnjob"));
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        doc.add(new Paragraph(" votre poste a ete bien deposé a la date : "+today+"  a l heure : "+time));
        doc.close();
        Desktop.getDesktop().open(new File("D:\\Program Files\\Java\\Projects\\PidevPersonne\\src\\Pdfposte.pdf"));
        
    }



}
