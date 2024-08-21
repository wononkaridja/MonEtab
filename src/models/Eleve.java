package models;

public class Eleve extends Personne{
    private String classe;
    private String matricule;

    public Eleve() {
    }

    public Eleve(int id, String dateNaissance, String ville, String nom, String prenom, String telephone, String classe, String matricule) {
        super(id, dateNaissance, ville, nom, prenom, telephone);
        this.classe = classe;
        this.matricule = matricule;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }



}
