///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package entities;



public class Competences {

//  public static void ajouterCompetence() {
//    }
  private int id_comp;
  private String nom;

  public Competences(int id_comp, String nom) {
    this.id_comp = id_comp;
    this.nom = nom;
  }

  public int getId() {
    return id_comp;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
    
  }

    @Override
    public String toString() {
        return "Compétences{" + "id_comp=" + id_comp + ", nom=" + nom + '}';
    }

}
  