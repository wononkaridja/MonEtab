package models;

import java.util.Date;

public class Personne {
    private int id;
    private String dateNaissance;
    private String ville;
    private String nom;
    private String prenom;
    private String telephone;

    public Personne() {
    }

    public Personne(int id, String dateNaissance, String ville, String nom, String prenom, String telephone) {
        this.id = id;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int obtenirAge(Date age){
        return 1;
    }
}
