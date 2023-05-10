/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Experiences;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public interface experInterface {
    public void ajouterExperience(Experiences exp) throws SQLException;
    public List<Experiences> afficherExperience() throws SQLException;
    public void supprimerExperiences(Experiences exp) throws SQLException;
    public void modifierExperiences(Experiences exp) throws SQLException;
}
