/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Scolarite;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public interface scolarInterface {
   public void ajouterScolar(Scolarite Sc) throws SQLException;
    public List<Scolarite> afficherScolar() throws SQLException;
    public void supprimerScolar(Scolarite Sc) throws SQLException;
    public void modifierScolar(Scolarite Sc) throws SQLException;
}
