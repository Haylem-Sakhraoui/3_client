/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import entities.categories;
import services.ServiceCategories;



/**
 *
 * @author Lord
 */
public class ShowCategorieFront extends Form {
    
     Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  ArrayList<categories> list=ServiceCategories.getInstance().parsecat();

        
    
  public ShowCategorieFront(Form previous) {
      f=this;
      
      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Categorie");
        //setTitle("List Categories");
  
   
    TextField searchField = new TextField("", "Search");
searchField.addDataChangeListener((i1, i2) -> {
    // Implement your search logic here 
   String text = searchField.getText();
   if(text.length()!=0)    
   {list=ServiceCategories.getInstance().parsecat2(text);

 
            C2.removeAll(); // remove all the existing components from the container
               
            addcategorie(list); // add the filtered categories to the container
         
            refreshTheme();
    } 
   else{
      list=ServiceCategories.getInstance().parsecat(); 
       C2.removeAll(); // remove all the existing components from the container
       
       addcategorie(list); // add the filtered categories to the container
       
       refreshTheme();
   }
       
});
       
       
  addcategorie(list);
       //f= new Form("Marques",BoxLayout.y());
       
        
        
        
        
     
      
//f.show(); 

addAll(C2);

   
           
        
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        getToolbar().setTitleComponent(searchField);
    
    }
    
    
    public void addcategorie(ArrayList<categories> list){
         
  for( categories c : list){
       

        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       

Label l = new Label(c.getTypec());

   C1.addAll(l);    
       

C2.addAll(C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
}
