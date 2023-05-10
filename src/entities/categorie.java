/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author lenovo
 */
public class categorie {
    
    private int id_service;
    private int id_categorie;
    private String nom_categorie;
    private int nb_tot_service;

    public categorie(int id_service, int id_categorie, String nom_categorie, int nb_tot_service) {
        this.id_service = id_service;
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.nb_tot_service = nb_tot_service;
    }

    public categorie(int id_service, String nom_categorie) {
        this.id_service = id_service;
        this.nom_categorie = nom_categorie;
    }

    public categorie(int id_service, int id_categorie, String nom_categorie) {
        this.id_service = id_service;
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }

    public categorie() {
    }
    

    public categorie(int id_service, String nom_categorie, int nb_tot_service) {
        this.id_service = id_service;
        this.nom_categorie = nom_categorie;
        this.nb_tot_service = nb_tot_service;
    }

    public categorie(String nom_categorie, int nb_tot_service) {
        this.nom_categorie = nom_categorie;
        this.nb_tot_service = nb_tot_service;
    }
    
    

    public int getId_service() {
        return id_service;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public int getNb_tot_service() {
        return nb_tot_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setNb_tot_service(int nb_tot_service) {
        this.nb_tot_service = nb_tot_service;
    }

    @Override
    public String toString() {
        return "categories{" + "id_service=" + id_service + ", id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + ", nb_tot_service=" + nb_tot_service + '}';
    }
    
    
    
    
}
