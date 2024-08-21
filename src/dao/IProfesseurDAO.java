package dao;

import models.Professeur;

import java.util.List;

public interface IProfesseurDAO {

    public Professeur ajouter(Professeur professeur);
    public void modifier(Professeur professeur  );
    public boolean supprimer(int identifiant);
    public List<Professeur> listeProfesseur();
    public void obtenirProfesseur(int identifiant);

}
