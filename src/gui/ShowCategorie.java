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
public class ShowCategorie extends Form {
    
     Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  ArrayList<categories> list=ServiceCategories.getInstance().parsecat();
    Button btnAdd=new Button("Add Categorie");
        
    
  public ShowCategorie(Form previous) {
      f=this;
      
      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Categorie");
        //setTitle("List Categories");
    btnAdd.addActionListener(e -> new AddCategorie(this).show());
       
      C2.add(btnAdd);
    TextField searchField = new TextField("", "Search");
searchField.addDataChangeListener((i1, i2) -> {
    // Implement your search logic here 
   String text = searchField.getText();
   if(text.length()!=0)    
   {list=ServiceCategories.getInstance().parsecat2(text);

 
            C2.removeAll(); // remove all the existing components from the container
               C2.add(btnAdd);
            addcategorie(list); // add the filtered categories to the container
         
            refreshTheme();
    } 
   else{
      list=ServiceCategories.getInstance().parsecat(); 
       C2.removeAll(); // remove all the existing components from the container
       C2.add(btnAdd);
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
       
Button b = new Button("remove");
Button mm = new Button("update");
Label l = new Label(c.getTypec());
b.addActionListener((evt) ->{
     ServiceCategories.getInstance().deletecat(c.getId());
      C2.removeComponent(l); // remove the label from the container
    C2.removeComponent(C1); // remove the button container from the container
    f.refreshTheme(); // refresh the form's theme
});
mm.addActionListener((evt) ->{

     new updatecategorie(this,c).show();
     
     
});
   C1.addAll(l,b,mm);    
       

C2.addAll(C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
}
