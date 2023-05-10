/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import entities.Photo;
import entities.Publication;
import entities.Utilisateur;       
import java.util.ArrayList;
/**
 *
 * @author Baklouti
 */
public interface IservicesA<T,t> {
     public ArrayList<String> select_all(T t);
    public void update(T t);
    public void insert(T t);
    public void delete(T t);
}
