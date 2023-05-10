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
import entities.Competence;
import services.ServiceCompetence;
/**
 *
 * @author Lord
 */
public class showCompetence extends Form {
    
       ArrayList<Competence> list= ServiceCompetence.getInstance().parseservice();
      Form f;

  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
    
    public showCompetence(Form previous){
      
  
    f=this;

      Toolbar.setGlobalToolbar(true);
      
       addcompetence(list);
       
       addAll(C2);

  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
      public void addcompetence(ArrayList<Competence> list){
         
  for( Competence c : list){
       

        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       

Label l = new Label("Nom: "+c.getNom());


   C1.addAll(l);    
       

C2.addAll(C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
  
    }
}
