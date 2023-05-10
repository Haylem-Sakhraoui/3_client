/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import gui.AddComController;
import gui.EditPublicationController;
import Services.ServicesPubliaction;
import Services.servicePhoto;
import entities.Photo;
import entities.Publication;
import entities.userclient;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import gui.EditPublicationController;
import javafx.scene.control.DatePicker;
import org.controlsfx.control.Notifications;
import Services.ServiceBadWords;
import Services.ServiceCommentair;
import entities.Commentair;
import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.Label;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import Services.ServicesTags;
import entities.Tag;
/**
 * FXML Controller class
 *
 * @author Baklouti
 */
/**************************************************************************************************************************** */
public class FrontEnd_PublicationController implements Initializable {

    @FXML
    private TextArea ta_postFE;
    @FXML
    private TextField tf_ph;
    @FXML
    private Button btn_postFE;
    @FXML
    private ListView<ListView> lv_postFE;
    @FXML
    private Button shouf;
    @FXML
    private Button btn_search;
    @FXML
    private TextField tf_search;
    
    
    
    
/*****************************************************************************************************************************/  
  

    
/**************************************************************************************************************************** */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceBadWords.loadConfigs();
           ServicesPubliaction sp=new ServicesPubliaction();
        sp.deselectionner();
    }    
/*****************************************************************************************************************************/  
 public void changeTextCellEvent(TableColumn.CellEditEvent eddited_cell, ListView l)
{
    Publication pub_editable = (Publication) l.getSelectionModel().getSelectedItem();
    if(eddited_cell.getNewValue().toString().isEmpty() == false)
    {
    pub_editable.setText(eddited_cell.getNewValue().toString());
    ServicesPubliaction spub = new ServicesPubliaction();
    spub.update(pub_editable);
 

    ServicesTags STAG = new ServicesTags();
    STAG.delete_relation(pub_editable);
    STAG.add_relation(pub_editable);
  
    

}
    notif("Text","Text changé avec succée !");
    display();
}
 /*****************************************************************************************************************************/
 public ArrayList<Publication> Search_by_tag()
    {
        ServicesTags STAG = new ServicesTags();
        ArrayList<Publication> listPub = new ArrayList<>();
        if(tf_search.getText().isEmpty() == false)
        {
           ArrayList<String> tags = new ArrayList<>();
           tags = STAG.Check_hashtags(tf_search.getText());
           for(int i = 0; i<=tags.size()-1;i++)
           {
               Tag t = new Tag();
               t.setName(tags.get(i));
               int id_tag = STAG.fetchId(t);
              if(id_tag != -1)
               {
                  ArrayList<Integer> id_pubs = new ArrayList<>();
                  id_pubs = STAG.get_pubs_from_tag(id_tag);
                  ServicesPubliaction spub = new ServicesPubliaction();
                  for(int j = 0 ; j<=id_pubs.size()-1;j++)
                  {
                      Publication p = new Publication();
                      p = spub.fetch_pub(id_pubs.get(j));
                      listPub.add(p);
                      
                  }
               }
           }
        }
        for (int i = 0; i<=listPub.size()-1;i++)
        {
            int id = listPub.get(i).getId();
            for(int j =i+1;j<=listPub.size()-1;j++)
            {
                if(id==listPub.get(j).getId())
                {
                    listPub.remove(j);
                    break;
                }
            }
        }
        return listPub;
    }
/**********************************************************************************************************************************/
public String test() throws IOException
        {
                   FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("*.png","Image Files");
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("*.jpeg","*.png");
        FileChooser.ExtensionFilter extFilter4 = new FileChooser.ExtensionFilter("*.jpg","*.png");
        FileChooser.ExtensionFilter extFilter5 = new FileChooser.ExtensionFilter("*.gif","*.png");
        fileChooser.getExtensionFilters().addAll(extFilter);
        fileChooser.getExtensionFilters().addAll(extFilter2);
        fileChooser.getExtensionFilters().addAll(extFilter3);
        fileChooser.getExtensionFilters().addAll(extFilter4);
        fileChooser.getExtensionFilters().addAll(extFilter5);
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            System.out.println(selectedFile.getPath());
            return selectedFile.toURI().toURL().toString();
        } 
        return null;
        }




 public void notif(String title, String text)
{
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
    .hideAfter(javafx.util.Duration.seconds(5))
    .position(Pos.TOP_CENTER);
    notificationBuilder.show();
}
/*****************************************************************************************************************************/
   

 @FXML
    private void Add() throws IOException {
        if (ta_postFE.getText().isEmpty() == false) {
            ServicesPubliaction ps = new ServicesPubliaction();
            Publication p = new Publication();
            Photo ph = new Photo();
            servicePhoto sph = new servicePhoto();
            p.setText(ta_postFE.getText());
            ps.insert(p);
            notif("Publication", "Publication Ajoutée avec succée !");
            if (tf_ph.getText().isEmpty() == false) {

                ph.setId_pub(p.getId());
                ph.setUrl(tf_ph.getText());
                System.out.println(ph.toString());
                sph.insert(ph);
                // System.out.println("test");
            } else if (tf_ph.getText().isEmpty() == true) {
                if (notif_choix() == 0) {
                    ph.setId_pub(p.getId());
                    ph.setUrl(test());
                    System.out.println(ph.toString());
                    sph.insert(ph);
                }
            }

            ServicesTags STAG = new ServicesTags();
            STAG.add_relation(p);

            display();
            ta_postFE.clear();
            tf_ph.clear();
        }
        notif("Publication", "Photo Ajoutée avec succée !");
    }

              
/************************************************************************************************************************/         
/************************************************************************************************************************/
 

public void display_ph(ArrayList<String> links) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PhotoDisplay.fxml"));
        Parent photo_view = loader.load();
        
        Scene photo_view_scene = new Scene(photo_view);
        
        PhotoDisplayController con = loader.getController();
        con.list_pics = links;
        
        if (!links.isEmpty()) {
            con.load_pic_index(links, 0);
        }
        
        Stage stage = new Stage();
        stage.setScene(photo_view_scene);
        stage.setResizable(false);
        stage.setTitle("Photo");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    } catch(Exception e) {
        System.out.println(e.toString());
    }
}


 /***********************************************************************************************/   

public int notif_choix()
{
        int dialogButton = 0;
        dialogButton = JOptionPane.showConfirmDialog (null, "You didn't add a link, do you wanna upload an Image ?","Image", dialogButton);
        System.out.println(dialogButton);
        if (dialogButton == 0) {
    return 0;
} else {
    return 1;
}
}  

/************************************************************************************************/

        public void display()
    {
        ServicesPubliaction ps= new ServicesPubliaction();
        Publication p = new Publication();
        lv_postFE.setItems(SwitchPubs(ps.select_all(p)));
    }
 

 
/*************************************************************************************************************************************/
    public ObservableList<ListView> SwitchPubs(ArrayList<Publication> pubs)
        {
         ObservableList<ListView> listPub = FXCollections.observableArrayList();
         ServicesPubliaction sp = new ServicesPubliaction();
         
         for(int i = 0;i<=pubs.size()-1;i++)
    {
        Publication pub = new Publication(pubs.get(i).getId(),pubs.get(i).getUser_id(),pubs.get(i).getNb_react(),pubs.get(i).getText(),pubs.get(i).getDate());
        ServicesPubliaction spub = new ServicesPubliaction();
        servicePhoto sph = new servicePhoto();
        
        ListView l = new ListView();
        Button btn = new Button();
        Button supp = new Button();
        Button btn_ph = new Button();
        Button btn_edit = new Button();
        Button btn_com = new Button();
        Text t = new Text();
        
        t.setWrappingWidth(200);
        t.setText("'' "+pub.getText()+" ''");
        

        btn.setText("Like");
        btn_com.setText("Comments ");
      
   
      
        supp.setText("Supprimer");
        btn_ph.setText("See pics !");
        btn_edit.setText("Edit");
        
                double h =(pub.getText().length())*10 + btn.getHeight() + btn_ph.getHeight() +15;
        
            
            
                
           btn_edit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
               try{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("EditPublication.fxml"));
         Parent EditWindow = loader.load();
         
            Scene photo_view_scene = new Scene(EditWindow);
            
            EditPublicationController con = loader.getController();
            
            
            Stage stage = new Stage();
            stage.setScene(photo_view_scene);
          //  stage.setResizable(false);
            stage.setTitle("Edit");
           // stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            con.set_p(pub);
           
            con.Display();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
            }
        }
        );
                
                
                
         btn_ph.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                ArrayList array= new ArrayList<String>();
                String link = (String) sph.get_LinksByIdPub(pub.getId()).get(0);
                array = sph.get_LinksByIdPub(pub.getId());
                System.out.println(link);
                display_ph(array);
            }
        });
        
        supp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
               spub.drop_track(pub);
                spub.delete(pub);
                    
                display();
                notif("Publication","Suppression avec succée !");
            }
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                
                spub.like_post(pub,userclient.getId());
                display();
                notif("Publication","Like à jour !");

            
            }
        });
        
        btn_com.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                 {
               try{
                   ServicesPubliaction sp=new ServicesPubliaction();
                   sp.selectionner(pub.getId());
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("AddCom.fxml"));
         Parent EditWindow = loader.load();
            Scene photo_view_scene = new Scene(EditWindow);
            AddComController con = loader.getController();   
            Stage stage = new Stage();
            stage.setScene(photo_view_scene);
            stage.setResizable(false);
            stage.setTitle("Commentaire");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
          
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
            }
            }
        });
        
       System.out.println(pub.getText().length());
        l.setPrefSize(100, 160);
      // l.getItems().add(spub.getUsername(pubs.get(i).getUser_id())+" a dit :");
        
        l.getItems().add(t);
        l.getItems().add("Like: "+pubs.get(i).getNb_react());
        l.getItems().add("Date: "+pubs.get(i).getDate());
        l.getItems().add(btn);
        l.getItems().add(btn_com);
        l.getItems().add(btn_ph);
        ServiceCommentair sc=new ServiceCommentair();
        List<Commentair> list=sc.afficherCommPub(pub.getId());
        
        
        if(userclient.getType() == "admin"  || userclient.getId() == pubs.get(i).getUser_id())
        {
        l.getItems().add(supp);
        l.getItems().add(btn_edit);
        }
        listPub.add(l);
        Label l2=new Label();
        l2.setText("Les Commentaires De la Publication : ");
        l.getItems().add(l2);
        l2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        for (Commentair com: list)
        {
            Label la=new Label();
            la.setText(com.getSuj_com());
            l.getItems().add(la);
        }
    } 

            return listPub;
        }

    @FXML
    private void chouf(ActionEvent event) {
    ServicesPubliaction ps= new ServicesPubliaction();
        Publication p = new Publication();
        lv_postFE.setItems(SwitchPubs(ps.select_all(p)));
    }


     @FXML
    public void Display_BySearch()
    {
        ServicesPubliaction ps= new ServicesPubliaction();
        Publication p = new Publication();
        ObservableList<ListView> OLV = SwitchPubs(Search_by_tag());
        lv_postFE.setItems(OLV);
    }
    

    
}