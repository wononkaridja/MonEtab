package dao;


import models.Utilisateur;

import java.util.List;

public interface IUtilisateurDAO {
    public boolean authentification(String identifiant , String motDePasse);

    public boolean ajouterCompte(String identifiant , String motDePasse);

    public boolean modifierMotDePasse(String identifiant , String motDePassa);

    public boolean supprimerCompte(String identifiant , String motDePasse);

    public List<Utilisateur> listerUtilisateurs();
}
