/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Commentaire {
      
    int id;
    Date date_c;
    String description_c;
    int reclamation_id;

    public Commentaire(int id, Date date_c, String description_c, int reclamation_id) {
        this.id = id;
        this.date_c = date_c;
        this.description_c = description_c;
        this.reclamation_id = reclamation_id;
    }

    public Commentaire(Date date_c, String description_c, int reclamation_id) {
         this.date_c = date_c;
        this.description_c = description_c;
        this.reclamation_id = reclamation_id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }



    public Commentaire(int id_c, Date date_c, String description_c) {
        this.id= id;
        this.date_c = date_c;
        this.description_c = description_c;
    }

    
   
   

    public Commentaire(Date date_c, String description_c) {
        this.date_c = date_c;
        this.description_c = description_c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_c() {
        return date_c;
    }

    public void setDate_c(Date date_c) {
        this.date_c = date_c;
    }

    public String getDescription_c() {
        return description_c;
    }

    public void setDescription_c(String description_c) {
        this.description_c = description_c;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", date_c=" + date_c + ", description_c=" + description_c + ", reclamation_id=" + reclamation_id + '}';
    }
    
    
}
