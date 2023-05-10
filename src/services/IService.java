/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entities.service;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface IService {
    public void ajouteservice(service s) throws SQLException;
    public List<service> afficherpersonne() throws SQLException;
    public void supprimer(String nomservice1) throws SQLException;
    public void modifier(String nomservm, int nbtot, String nservice) throws SQLException;
}
