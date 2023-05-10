/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentair;
import Services.ServiceBadWords;
import Services.ServiceCommentair;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import entities.Publication;
import Services.ServicesPubliaction;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
/**
 * FXML Controller class
 *
 * @author Baklouti
 */
/******************************************************************************************************/
public class AddComController implements Initializable {

    @FXML
    private Button xbtn;
    @FXML
    private Button home;
    @FXML
    private TextArea sujcom;
    @FXML
    private Button com;
    @FXML
    private TextField publicationZone;
/******************************************************************************************************/
    /**
     * Initializes the controller class.
     */
/******************************************************************************************************/    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //Bad Words
        ServiceBadWords.loadConfigs();
        Publication p=new Publication();
        ServicesPubliaction sp=new ServicesPubliaction();
        p=sp.getSelect();
        publicationZone.setText(p.getText());
        publicationZone.setEditable(false);

    }    
/******************************************************************************************************/    
   @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
    }
/******************************************************************************************************/
    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeCom.fxml"));
        Parent root = loader.load(); 
        home.getScene().setRoot(root);
    }
/******************************************************************************************************/
    @FXML
    private void AddCom(ActionEvent event) {
         if(sujcom.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty fields");
            a.showAndWait();
        }
         else{
              ArrayList<String> badWordsList = ServiceBadWords.badWordsFound(sujcom.getText());
              if (badWordsList.size()>0){
           Notifications notificationBuilder = Notifications.create()
            .title("No way !").text("Inappropriate word(s) has been detected, please rewrite it").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
              else{
        ServiceCommentair scom = new ServiceCommentair();
           Publication p=new Publication();
        ServicesPubliaction sp=new ServicesPubliaction();
        p=sp.getSelect();
        scom.envoyer(new Commentair(4,"JM",p.getId(),sujcom.getText()));
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your comment has been added !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
           sujcom.clear();
    }}

/******************************************************************************************************/    
}
