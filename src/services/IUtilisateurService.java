package services;

import models.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    public boolean authentification(String identifiant , String motDePasse);

    public boolean addrCompte(String identifiant , String motDePasse);

    public boolean updateMotDePasse(String identifiant , String motDePassa);

    public boolean deleteCompte(String identifiant , String motDePasse);

    public List<Utilisateur> getAllUtilisateurs();
}
