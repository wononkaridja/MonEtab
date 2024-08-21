package services.Implementation;

import dao.Implementation.EleveDaoImplementation;
import models.Eleve;
import services.IEleveService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EleveServiceImplementation implements IEleveService {

    private final  EleveDaoImplementation eleveDaoImplementation;
    static Scanner scannerInterne = new Scanner(System.in);


    public EleveServiceImplementation(){
        this.eleveDaoImplementation = new EleveDaoImplementation();
    }

    @Override
    public  Eleve add(Eleve eleve) {
        return this.eleveDaoImplementation.ajouter(eleve );
    }

    @Override
    public void update(Eleve eleve) {
        String matricule = formUpdate(scannerInterne);
        eleve = this.eleveDaoImplementation.verifierEleve(matricule);

        if(eleve != null){
            sousMEnuModifEleve();
            int choixModif = Integer.parseInt(scannerInterne.nextLine());

            switch(choixModif){
                case 1:
                    System.out.print("MATRICULE: ");
                    eleve.setMatricule(scannerInterne.nextLine());
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 2:
                    System.out.print("Nom: ");
                    eleve.setNom(scannerInterne.nextLine());
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 3:
                    System.out.print("Prenom: ");
                    eleve.setPrenom(scannerInterne.nextLine());
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 4:
                    do {
                        System.out.print("DATE DE NAISSANCE: ");
                        eleve.setDateNaissance(scannerInterne.nextLine());
                    }while (DateConverter.convertStringToDate(eleve.getDateNaissance()) == null);
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 5:
                    System.out.print("VILLE: ");
                    eleve.setDateNaissance(scannerInterne.nextLine());
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 6:
                    System.out.print("TELEPHONE: ");
                    eleve.setDateNaissance(scannerInterne.nextLine());
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 7:
                    System.out.print("CLASSE: ");
                    eleve.setDateNaissance(scannerInterne.nextLine());
                    this.eleveDaoImplementation.modifier(eleve);
                    getOne(eleve.getId());
                    break;
                case 8:
                    break;
                case 0:
                    break;
                default:
                    System.out.print("Choix invalid !😒");

            }

        }else{
            System.out.println("Elève non trouvé !");
        }
    }

    @Override
    public boolean delete(int identifiant) {
        return this.eleveDaoImplementation.supprimer(identifiant);
    }

    @Override
    public List<Eleve> getAll() {
        return this.eleveDaoImplementation.listeEleve();
    }

    @Override
    public void getOne(int identifiant) {
        this.eleveDaoImplementation.obtenirEleve(identifiant);
    }

    public static void printListEleve(List<Eleve> eleves){
        System.out.println("Listes des eleves : ");
        for (Eleve eleve : eleves) {
            System.out.println("""
                        MATRICULE : %s
                        NOM : %s
                        PRENOM : %s
                        CLASSE : %s
                        VILLE : %s
                        TELEPHONE : %S
                        DATE DE NAISSANCE : %s
                        """ .formatted(
                            eleve.getMatricule(),
                            eleve.getNom(),
                            eleve.getPrenom(),
                            eleve.getClasse(),
                            eleve.getVille(),
                            eleve.getTelephone(),
                            eleve.getDateNaissance()
                    )
            );
        }
    }

    public static void afficheMenuEleve(){
        System.out.print(
                """
            ******************************************************
                    GESTION DES ELEVES
            ******************************************************
            
            Menu:
            
                1 : Ajouter un élève 
                2 : supprimer un élève
                3 : Modifier les informations de l'élève
                4 : Lister les élèves 
                5 : Retour 
                0 : Accueil 

            Votre choix:
            """
        );
    }

    static void sousMEnuModifEleve(){
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

    static Eleve formAdd(Scanner scanner , Eleve eleve){
        System.out.print("Matricule: ");
        eleve.setMatricule(scanner.nextLine());
        /**************** */

        System.out.print("Nom: ");
        eleve.setNom(scanner.nextLine());
        /**************** */

        System.out.print("Prénom: ");
        eleve.setPrenom(scanner.nextLine());
        /**************** */

        do {
            System.out.print("Date de naissance: ");
            eleve.setDateNaissance(scanner.nextLine());
        }while (DateConverter.convertStringToDate(eleve.getDateNaissance()) == null);
        /**************** */
        System.out.print("Ville: ");
        eleve.setVille(scanner.nextLine());

        /**************** */
        System.out.print("Telephone: ");
        eleve.setTelephone(scanner.nextLine());

        /**************** */
        System.out.print("Classe: ");
        eleve.setClasse(scanner.nextLine());

        return eleve;
    }

    static int formDelete(Scanner scanner){
        System.out.print("id de l'élève à supprimer: ");
        int id = scanner.nextInt();

        return id;
    }

    static String formUpdate(Scanner scanner ){
        System.out.print("Matricule de l'élève à modifier: ");
        String matricule = scanner.nextLine();
        return matricule;
    }

    public static   int  gestionEleve(Scanner scanner , Eleve eleve){
        int newChoix = 0 , trusly  = 0;
        boolean supprimer = false , modifier = false;
        EleveServiceImplementation eleveServiceImplementation = new EleveServiceImplementation();

        do{
            afficheMenuEleve();
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    do{
                        Eleve eleve0 = formAdd(scanner , eleve);
                        Eleve eleve1 = eleveServiceImplementation.add(eleve0);
                        System.out.println(eleve1);
                        System.out.println(eleve1.getId());

                        eleveServiceImplementation.getOne(eleve1.getId());
                        System.out.print("(1) Pour ajouter un nouvel Eleve | (0) Pour revenir au menu precedent :");
                        newChoix = Integer.parseInt(scanner.nextLine());
                        if(newChoix == 0)
                            trusly = 0;
                    }while(newChoix == 1);
                    break;
                case 2:
                    int id = formDelete(scanner);
                    supprimer = eleveServiceImplementation.delete(id);
                    if(supprimer){
                        newChoix = Objects.equals(scanner.nextLine(), "") ? 0 : Integer.parseInt(scanner.nextLine());

                        if(newChoix == 0){
                            trusly = 0;
                        }else{
                            System.out.println("Code invallid : 🥱");
                            trusly = 1;
                        }
                    }else{
                        trusly = 1;
                    }
                    break;
                case 3:
                    do{
                        eleveServiceImplementation.update(eleve);
                        System.out.print("(0) Pour revenir au menu precedent :");
                        newChoix = Integer.parseInt(scanner.nextLine());
                        if (newChoix != 0) {
                            trusly = 1;
                            System.out.println("Choix invallid : Aurevoir");
                        }
                    }while(newChoix == 1);
                    break;
                case 4:
                    printListEleve(eleveServiceImplementation.getAll());
                    do{
                        System.out.print("(0) pour retourné au Menu precedent ):");
                        newChoix = Integer.parseInt(scanner.nextLine());
                        if(newChoix == 0) {
                            trusly = 0;
                        }else {
                            System.out.println("Code invallid : 🥱");
                            trusly = 1;
                        }
                    }while (trusly == 1);
                    break;
                case 5:
                    trusly = 2;
                    break;
                case 0:
                    System.out.println("******************************************************");
                    System.out.println("Merci d'avoir utilisé l'application ETAB.");
                    System.out.println("******************************************************");
                    trusly = 1;
                    break;
                default:
                    System.out.println("Choix invalide 😒");
                    trusly = 1;
            }
        }while(trusly == 0);
        return trusly;
    }

}
