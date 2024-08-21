

import models.Eleve;
import models.Utilisateur;
import services.Implementation.EleveServiceImplementation;
import services.Implementation.ProfesseurServiceImplementation;
import services.Implementation.UtilisateurServiceImplementation;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static boolean auth = false;
    private static  UtilisateurServiceImplementation utilisateurServiceImplementation = new UtilisateurServiceImplementation();


    public static void main(String[] args) {
        // Capture du temps de départ en nanosecondes
        long startTime = System.nanoTime();

        Eleve eleve = new Eleve();

        do {
            Utilisateur utilisateur = UtilisateurServiceImplementation.MenuConnexion(scanner);
            auth = utilisateurServiceImplementation.authentification(utilisateur.getPseudo(), utilisateur.getMotDePasse());
            if (auth){
                int  trusly  = 2;
                do{
                    menuPrincipal();
                    int choix = Integer.parseInt(scanner.nextLine());

                    switch (choix) {
                        case 1:
                            trusly = EleveServiceImplementation.gestionEleve(scanner, eleve);
                            break;
                        case 2:
                            ProfesseurServiceImplementation.gestionProfesseur();
                            trusly = 1;
                            break;
                        case 3:
                            UtilisateurServiceImplementation.gestionUtilisateur();
                            trusly = 1;
                            break;
                        case 0:
                            System.out.println("Aurevoir !!");
                            trusly = 1;
                            break;
                        default:
                            System.out.println("Choix invallid");
                            break;
                    }
                }while(trusly == 2);
            }else{
                System.out.println("Connexion echouée : Les informations sont incorrect !!");
            }

        }while (auth == false);

        // Capture du temps de fin en nanosecondes
        long endTime = System.nanoTime();

        // Calcul de la durée en secondes
        long durationInSeconds = (endTime - startTime) / 1_000_000_000;

        // Affichage de la durée en secondes
        System.out.println("Durée d'exécution du programme : " + durationInSeconds + " secondes");

    }

    static void menuPrincipal(){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();

        System.out.print(""" 
        ******************************************************
                BIENVENU DANS L’APPLICATION ETAB v1.3
        ******************************************************
        
        MENU:
        
        
            1 : Gestions élèves
            2 : Gestions Professeur
            3 : Gestions Utilisateur
            0 : Quitter


        Date du Système : %s

        Votre choix:
        """.formatted(sdf.format(date)));
    }

}
