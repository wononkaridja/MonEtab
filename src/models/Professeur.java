package models;

public class Professeur extends Personne {
    private boolean vacant;
    private String matiereEnseigner;
    private String prochainCour;
    private String sujetProchaineReunion;


    public Professeur() {
    }

    public Professeur(int id, String dateNaissance, String ville, String nom, String prenom, String telephone, boolean vacant, String matiereEnseigner, String prochainCour, String sujetProchaineReunion) {
        super(id, dateNaissance, ville, nom, prenom, telephone);
        this.vacant = vacant;
        this.matiereEnseigner = matiereEnseigner;
        this.prochainCour = prochainCour;
        this.sujetProchaineReunion = sujetProchaineReunion;
    }



    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(String vacan) {

        if (vacan == "oui") {
            this.vacant = true;
        } else {
            this.vacant = false;
        }
    }

    public String getMatiereEnseigner() {
        return matiereEnseigner;
    }

    public void setMatiereEnseigner(String matiereEnseigner) {
        this.matiereEnseigner = matiereEnseigner;
    }

    public String getProchainCour() {
        return prochainCour;
    }

    public void setProchainCour(String prochainCour) {
        this.prochainCour = prochainCour;
    }

    public String getSujetProchaineReunion() {
        return sujetProchaineReunion;
    }

    public void setSujetProchaineReunion(String sujetProchaineReunion) {
        this.sujetProchaineReunion = sujetProchaineReunion;
    }

}
