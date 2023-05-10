/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.time.LocalDate;

public class Experiences {
    private int id_exp;
    private String nom_entreprise;
    private String poste;
    private Date date_debut;
    private Date date_fin;
    
    public Experiences(int id_exp, String nom_entreprise, String poste, Date date_debut, Date date_fin) {
        this.id_exp = id_exp;
        this.nom_entreprise = nom_entreprise;
        this.poste = poste;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    public int getId() {
        return id_exp;
    }
    
    public String getNomEntreprise() {
        return nom_entreprise;
    }
    
    public String getPoste() {
        return poste;
    }
    
    public Date getDateDebut() {
        return date_debut;
    }
    
    public Date getDateFin() {
        return date_fin;
    }
    
    public void setId(int id_exp) {
        this.id_exp = id_exp;
    }
    
    public void setNomEntreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public void setDateDebut(Date date_debut) {
        this.date_debut = date_debut;
    }
    
    public void setDateFin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Expériences{" + "id_exp=" + id_exp + ", nom_entreprise=" + nom_entreprise + ", poste=" + poste + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

}
    