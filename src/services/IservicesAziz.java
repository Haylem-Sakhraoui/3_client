/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.util.List;
import static javafx.scene.input.KeyCode.P;

/**
 *
 * @author Baklouti
 * * @param <P>
 */
public interface IservicesAziz <P>{
     public void envoyer(P p);
    public void modifier(P p);
    public void supprimer(P p);
    public List<P> afficher();
}
