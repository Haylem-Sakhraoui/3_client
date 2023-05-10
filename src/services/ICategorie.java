/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entities.categorie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface ICategorie {
    public void ajouteservice(categorie c) throws SQLException;
    public List<categorie> affichercategorie() throws SQLException;
    public void supprimer(String nomcategorie) throws SQLException;
    public void modifier(String nomcat, int nbtots, String nomcategorie) throws SQLException;
}
