/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import utiles.MyDB;
import entities.Publication;
import entities.Utilisateur;
/**
 *
 * @author Baklouti
 */
public class Photo {
      private int id;
    private int id_pub;
    private String url;

    public Photo() {
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }
    
    public Photo(int id, int id_pub, String url) {
        this.id = id;
        this.id_pub = id_pub;
        this.url = url;
    }

    public Photo(int id) {
        this.id = id;
    }

    public Photo(int id_pub, String url) {
        this.id_pub = id_pub;
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Photo{" + "id=" + id + ", id_pub=" + id_pub + ", url=" + url + '}';
    }
}
