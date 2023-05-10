/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Baklouti
 */
public class userclient {
 static private int id=50;  
 static private String type="admin";
 public userclient(int id){
this.id=id;
}
 
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        userclient.id = id;
    }
    
    public static void setType(String type) {
        userclient.type = type;
    }
    
    public static String getType() {
        return type;
    }
    
}
