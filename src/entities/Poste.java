/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author amine
 */
public class Poste {
    int id_poste,id_candidat;
    int experience ;
    String description ;

    public Poste(int id_poste, int experience, String description) {
        this.id_poste = id_poste;
        this.experience = experience;
        this.description = description;
    }

    public int getId_candidat() {
        return id_candidat;
    }

    public void setId_candidat(int id_candidat) {
        this.id_candidat = id_candidat;
    }

    public Poste(int id_poste, int experience, String description,int id_candidat) {
        this.id_poste = id_poste;
        this.id_candidat = id_candidat;
        this.experience = experience;
        this.description = description;
    }

    public int getId_poste() {
        return id_poste;
    }

    public void setId_poste(int id_poste) {
        this.id_poste = id_poste;
    }

    

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Poste{" + "id_poste=" + id_poste + ", experience=" + experience + ", description=" + description + '}';
    }

    
}
