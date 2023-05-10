/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.Date;

/**
 *
 * @author User
 */
public class Reclamation {
    int id;
    Date date;
    String description;
    

    public Reclamation() {
    }

    public Reclamation(Date date, String description) {
        this.date=date;
        this.description=description;
       
       
    }

    public Reclamation(int id, Date date, String description) {
       this.id = id;
        this.date = date;
        this.description = description;
  
    }

    public Reclamation(int aInt, Date date, String string, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", date=" + date + ", description=" + description +  '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
  

  


   
}
