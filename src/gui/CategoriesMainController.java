/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CategoriesMainController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    @FXML
    private Button button;
    Stage stage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }   
    
    
    
    
    @FXML
    private void ajoutercategorie(MouseEvent event) {
        Loadpage("/gui/page1categorie");
        
        
    }
    @FXML
    private void Services(MouseEvent event ) throws IOException {
       Stage secondStage = new Stage();
       Parent root= null;
        
        root = FXMLLoader.load(getClass().getResource("/gui/mainfxml.fxml"));
        Scene scene = new Scene(root, 1000, 580);
        secondStage.setScene(scene);
        secondStage.show();
    }
    
    @FXML
    private void afficherlistecategories(MouseEvent event) {
        Loadpage("/gui/page2categorie");
    }
    
    @FXML
    private void supprimercategorie(MouseEvent event) {
        Loadpage("/gui/page3categorie");
    }

    
     @FXML
    private void modifiercategorie(MouseEvent event) {
        Loadpage("/gui/page4categorie");
        
    }
    
     

    
    private void Loadpage(String page){
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void home(MouseEvent event) {
        Loadpage("/gui/home");
    }

    @FXML
    private void pardefaut(MouseEvent event) {
    }

   

   
   

   

    
    

    

    
    
}
