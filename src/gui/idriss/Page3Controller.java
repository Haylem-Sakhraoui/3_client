/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.idriss;

import entities.Commentaire;
import entities.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import services.serviceCommentaire;
import services.serviceReclamation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Page3Controller implements Initializable {

    private TextField zoneidsupprime;
    @FXML
    private TextField idcrec;
    @FXML
    private Button btnajout;
    @FXML
    private DatePicker darec;
    @FXML
    private TextArea derec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        // TODO
    }    

    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
             if(validateDatePicker(darec)) {
      
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
       alert.setHeaderText(null);
      
        alert.setContentText("Please fill data");
        
        alert.show();
        
    }else{
            
             serviceCommentaire sc=new serviceCommentaire();
              Commentaire c=new Commentaire(Date.valueOf(darec.getValue()),derec.getText(),Integer.parseInt(idcrec.getText()));
            sc.ajouterCommentaire(c);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("commentaire successfully created!");
        alert.show();
       
        System.out.println("commentaire ajoutée");
        }}
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
}
