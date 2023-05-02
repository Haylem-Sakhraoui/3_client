/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import entities.categories;
import java.util.ArrayList;
import services.ServiceCategories;


/**
 *
 * @author Lord
 */
public class updatecategorie extends Form  {
    
     public updatecategorie(Form previous,categories c) {
        setTitle("Update Categorie");
        setLayout(BoxLayout.y());
        
        TextField tfName=new TextField(c.getTypec(),"Categorie name");
        
       
      
       Button btnValider = new Button("Update Categorie");
      
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               int x =0;
               if (tfName.getText().length() == 0)
        {
            Dialog.show("Error","Categorie Name not valid","OK",null);
            x=1;
        }
               ArrayList<categories> list=ServiceCategories.getInstance().parsecat();
             for(categories cat : list){
                    if(tfName.getText().equals(cat.getTypec()))
                    {
                        Dialog.show("Error","Categorie Name already exists","OK",null);
                        x=1;
                    }
                }   
     
            if(x==0)
            {
               c.setTypec(tfName.getText());
               if(ServiceCategories.getInstance().modifiercat(c)){
                   Dialog.show("Success","Categorie Updated","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
            }
            }
        
    
});
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
 new ShowCategorie(this).show();
});
addAll(tfName,btnValider);
        
        
        
    }
    
}
