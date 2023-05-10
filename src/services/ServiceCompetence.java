/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Competence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Lord
 */
public class ServiceCompetence {
    public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceCompetence instance=null;
     public ArrayList<Competence> tasks;
      Competence competence;

      
     
    public ServiceCompetence() {
        req=new ConnectionRequest();
    }
    
     public static ServiceCompetence getInstance() {
        if(instance==null)
             instance=new ServiceCompetence();
             
        return instance;
    }
     
     
     public ArrayList<Competence> parseservice(){
      ArrayList<Competence> result = new ArrayList<>();
         String url = Statics.BASE_URL+"competenceMobile/getcomp";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj: listofMaps){
                   Competence m = new Competence(); 
                   float id = Float.parseFloat(obj.get("idComp").toString());
                   String nom= obj.get("nom").toString();
                  
                   System.out.println(nom);
                     m.setId((int) id);
                     m.setNom(nom);
                 
                   
                  result.add(m);
               }
           }
          catch(Exception ex){
              ex.printStackTrace();
           }
               
            
        }
  });
       
         NetworkManager.getInstance().addToQueueAndWait(req);
         return result;
  }
     
}
