/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.idriss;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import entities.Reclamation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.serviceReclamation;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Page2Controller implements Initializable {

    @FXML
    private TableColumn<Reclamation,Integer> colid;
    @FXML
    private TableColumn<Reclamation, Timestamp> coldate;
    @FXML
    private TableColumn<Reclamation, String> coldesc;
    @FXML
    private TableView<Reclamation> table;
    private TableView<Reclamation> Tableview;
    @FXML
    private TextField id_modif;
    @FXML
    private TextField nvdate_modif;
    @FXML
    private TextField nvdesc_modif;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TableColumn<Reclamation, Integer> colidu;
    @FXML
    private TextField filterfield;
final ObservableList options = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
colid.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
        
        coldesc.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
      
        coldate.setCellValueFactory(new PropertyValueFactory<Reclamation,Timestamp>("date"));

        ObservableList<Reclamation> listU = FXCollections.observableArrayList();
serviceReclamation ps=new serviceReclamation();

        try {
            ps.afficherReclamation().forEach(r->{listU.add(r);});
        } catch (SQLException ex) {
            Logger.getLogger(Page2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(listU);





    }    
     public ObservableList<Reclamation> getReclamation(List<Reclamation> l){
        ObservableList<Reclamation> data1 = FXCollections.observableArrayList();
        for (int i =0; i<=l.size()-1; i++){
            data1.add(l.get(i));
        }
        return data1;
    }

    @FXML
    private void afficher(ActionEvent event) throws SQLException {
       
        serviceReclamation sr=new serviceReclamation();
      
     colid.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
 coldate.setCellValueFactory(new PropertyValueFactory<Reclamation, Timestamp>("date"));
 coldesc.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
 table.setItems(getReclamation(sr.afficherReclamation()));
        
      
    }

    @FXML
    private void HandleMouseAction(MouseEvent event) {
         Reclamation r = (Reclamation) table.getSelectionModel().getSelectedItem();
    id_modif.setText("" +r.getId());
    nvdate_modif.setText("" +r.getDate());
        nvdesc_modif.setText("" +r.getDescription());
       
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
              
        serviceReclamation sr=new serviceReclamation();
        
           int id_R_modif = Integer.parseInt(id_modif.getText()) ;  
           
      Reclamation r=new Reclamation(id_R_modif,Date.valueOf(nvdate_modif.getText()),nvdesc_modif.getText());
       

        sr.modifierReclamation(r);
        
          notif1("TN-JOB","reclamation modifiée");
        System.out.println("reclamation ajoutée");
     
       
         
       
       
      // Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Information Dialog");
           // alert.setHeaderText(null);
           // alert.setContentText("Sponsor modifier avec succès!");
              //   JOptionPane.showMessageDialog(null,"reclamation modifier avec succès!");
    }

    @FXML
    private void delete_btn(ActionEvent event) throws SQLException {
       serviceReclamation sr=new serviceReclamation();
               

        int id = Integer.parseInt(id_modif.getText());
        sr.supprimerReclamation(id);
        
         notif2("TN-JOB","reclamation supprimée");
        System.out.println("reclamation ajoutée");
       
        // System.out.print("demande supprimer ");
        // JOptionPane.showMessageDialog(null,"reclamation Supprimer");
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
          String critere = filterfield.getText();
        //  int critere1 = Integer.parseInt(filterField.getText());
       
    serviceReclamation so = new serviceReclamation();
    List<Reclamation> reclamations = so.rechercherUsers(critere);
    options.clear();
    options.addAll(reclamations);
    table.setItems(options);
    }
    
        public void notif1(String title, String text){
   Image img = new Image("/gui/idriss/tnjob.png");
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
            .graphic(new ImageView(img))
            .hideAfter(Duration.seconds(15))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.show();
}
        
           public void notif2(String title, String text){
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
    private void save(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    ObservableList<Reclamation> dataList4 = table.getItems();
    // create a file chooser dialog
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Data");
    File file = fileChooser.showSaveDialog(stage);
                //file.canRead()
    if (file != null) {
        try (BufferedWriter outWriter = new BufferedWriter(new FileWriter(file))) {
            for (Reclamation reclamations: dataList4) {
                outWriter.write(reclamations.toString());
                outWriter.newLine();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Successful");
            alert.setHeaderText(null);
            alert.setContentText("Data saved to file: " + file.getAbsolutePath());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while saving the data: " + e.getMessage());
            alert.showAndWait();
        }
    }
    }
    
    
}
