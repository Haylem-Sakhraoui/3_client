/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author amine
 */
public class Demande {
    int id_demande;
    int id_recruteur;
    String nomRecruteur;
    String description ;
    int experience ;
    Double remuneration ;
    Date expiration ;
    int telephone;

    public Demande(int id_demande,int id_recruteur,  String nomRecruteur, String description, int experience, Double remuneration, Date expiration, int telephone) {
        this.id_demande = id_demande;
        this.id_recruteur=id_recruteur;
        this.nomRecruteur = nomRecruteur;
        this.description = description;
        this.experience = experience;
        this.remuneration = remuneration;
        this.expiration = expiration;
        this.telephone = telephone;
    }

    public Demande(int id_recruteur, String nomRecruteur, String description, int experience, Double remuneration, Date expiration, int telephone) {
        this.id_recruteur = id_recruteur;
        this.nomRecruteur = nomRecruteur;
        this.description = description;
        this.experience = experience;
        this.remuneration = remuneration;
        this.expiration = expiration;
        this.telephone = telephone;
    }

    public int getId_recruteur() {
        return id_recruteur;
    }

    public void setId_recruteur(int id_recruteur) {
        this.id_recruteur = id_recruteur;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

   
   

    public String getDescription() {
        return description;
    }

    public int getExperience() {
        return experience;
    }

    public Double getRemuneration() {
        return remuneration;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setRemuneration(Double remuneration) {
        this.remuneration = remuneration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getNomRecruteur() {
        return nomRecruteur;
    }

    public void setNomRecruteur(String nomRecruteur) {
        this.nomRecruteur = nomRecruteur;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", nomRecruteur=" + nomRecruteur + ", description=" + description + ", experience=" + experience + ", remuneration=" + remuneration + ", expiration=" + expiration + ", telephone=" + telephone + '}';
    }

   
}
