/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author lenovo
 */
public class service {

    public service(String nom_service) {
        this.nom_service = nom_service;
    }

    public service() {
    }
    
    
    private int id_service;
    private String nom_service;
    private int nb_tot_freelance;

    public service(int id_service, String nom_service, int nb_tot_freelance) {
        this.id_service = id_service;
        this.nom_service = nom_service;
        this.nb_tot_freelance = nb_tot_freelance;
    }

    public service(String nom_service, int nb_tot_freelance) {
        this.nom_service = nom_service;
        this.nb_tot_freelance = nb_tot_freelance;
    }
    
    

    public int getId_service() {
        return id_service;
    }

    public String getNom_service() {
        return nom_service;
    }

    public int getNb_tot_freelance() {
        return nb_tot_freelance;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public void setNb_tot_freelance(int nb_tot_freelance) {
        this.nb_tot_freelance = nb_tot_freelance;
    }

    @Override
    public String toString() {
        return "service{" + "id_service=" + id_service + ", nom_service=" + nom_service + ", nb_tot_freelance=" + nb_tot_freelance + '}';
    }
    
    
    
    
}
