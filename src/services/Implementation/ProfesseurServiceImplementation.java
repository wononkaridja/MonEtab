package services.Implementation;

import models.Professeur;
import services.IEducation;
import services.IProfesseurService;

import java.util.List;

public class ProfesseurServiceImplementation implements IProfesseurService , IEducation {

    @Override
    public Professeur add(Professeur professeur) {
        return null;
    }

    @Override
    public void update(Professeur professeur) {

    }

    @Override
    public boolean delete(int identifiant) {
        return false;
    }

    @Override
    public List<Professeur> getAll() {
        return List.of();
    }

    @Override
    public void getOne(int identifiant) {

    }

    public static void gestionProfesseur(){}

    public static void afficheMenuProfesseur(){
        System.out.println(
                """
            ******************************************************
                    GESTION DES PROFESSEURS
            ******************************************************
            
            Menu:
            
                1 : Ajouter un professeur 
                2 : supprimer un models.Professeur
                3 : Modifier les informations du professeur
                4 : Lister les professeurs 
                5 : Retour 
                0 : Accueil 

            Votre choix:
            
            """
        );
    }

    static void sousMEnuModifProfesseur(){
        System.out.println("\t\t1 : Modifier MATRICULE");
        System.out.println("\t\t2 : Modifier NOM");
        System.out.println("\t\t3 : Modifier PRENOM");
        System.out.println("\t\t4 : Modifier DATE DE NAISSANCE");
        System.out.println("\t\t5 : Modifier VILLE");
        System.out.println("\t\t6 : Modifier TELEPHONE");
        System.out.println("\t\t7 : Modifier CLASSE");
        System.out.println("\t\t8 : Retour\n");
        System.out.println("\t\t0 : Accueil\n");
    }

    @Override
    public String enseigner(String matiere) {
        return "Enseigne la matiere : " + matiere;
    }

    @Override
    public String preparerCour(String cour) {
        return "Prepare le contenu d'un cours sur le sujet " + cour;
    }

    @Override
    public String assisterReunion(String sujet) {
        return "Doit assister à une reunion sur " + sujet;
    }

}
