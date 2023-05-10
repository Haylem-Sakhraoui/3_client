
package entities;


import java.time.LocalDate;
import java.util.Date;


public class Scolarite {
    private int id_etab;
    private String nom_etablissement;
    private String ville;
    private String pays;
    private String diplome;
    private Date date_obtention;
//    private Date date_obtention;

    public Scolarite (int id_etab, String nom_etablissement, String ville, String pays, String diplome, Date date_obtention) {
        this.id_etab = id_etab;
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.pays = pays;
        this.diplome = diplome;
        this.date_obtention = date_obtention;
    }

    public int getId_etab() {
        return id_etab;
    }

    public String getNom_etablissement() {
        return nom_etablissement;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    public String getDiplome() {
        return diplome;
    }

    public Date getDateObtention() {
        return date_obtention;
    
    }

    @Override
    public String toString() {
        return "Scolarité{" + "id_etab=" + id_etab + ", nom_etablissement=" + nom_etablissement + ", ville=" + ville + ", pays=" + pays + ", diplome=" + diplome + ", date_obtention=" + date_obtention + '}';
    }
}
 