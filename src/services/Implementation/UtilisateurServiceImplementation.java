package services.Implementation;

import dao.Implementation.UtilisateurDaoImplementation;
import models.Utilisateur;
import services.IUtilisateurService;

import java.util.List;
import java.util.Scanner;

public class UtilisateurServiceImplementation implements IUtilisateurService {

    private final UtilisateurDaoImplementation utilisateurDaoImplementation;

    public UtilisateurServiceImplementation() {
        this.utilisateurDaoImplementation = new UtilisateurDaoImplementation();
    }

    @Override
    public  boolean authentification(String identifiant , String motDePasse ){
        return this.utilisateurDaoImplementation.authentification(identifiant , motDePasse);
    }

    @Override
    public boolean addrCompte(String identifiant, String motDePasse) {
        return false;
    }

    @Override
    public boolean updateMotDePasse(String identifiant, String motDePassa) {
        return false;
    }

    @Override
    public boolean deleteCompte(String identifiant, String motDePasse) {
        return false;
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return List.of();
    }

    public static Utilisateur MenuConnexion(Scanner scanner){
        System.out.println(
                """
            ******************************************************
                    BIENVENU DANS L’APPLICATION ETAB v1.3
            ******************************************************
            
                                   CONNEXION
            """
        );

        System.out.print("Identifiant : ");
        String Identifiant = scanner.nextLine();
        System.out.print("Password : ");
        String Password = scanner.nextLine();

        return new Utilisateur(Identifiant , Password);
    }

    public static void gestionUtilisateur(){}

    public void afficheMenuUtilisateur(){
        System.out.println(
                """
            ******************************************************
                    GESTION DES UTILISATEURS
            ******************************************************
            
            Menu:
            
                1 : Ajouter un utilisateur 
                2 : supprimer un utilisateur
                3 : Modifier les informations de l'utilisateur
                4 : Lister les utilisateurs 
                5 : Retour 
                0 : Accueil 

            Votre choix:
            
            """
        );
    }
}
