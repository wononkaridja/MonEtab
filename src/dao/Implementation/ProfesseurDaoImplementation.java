package dao.Implementation;

import dao.IProfesseurDAO;
import dao.SingletonDataBase;
import models.Professeur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDaoImplementation implements IProfesseurDAO {


    @Override
    public Professeur ajouter(Professeur professeur) {


        String query = "INSERT INTO Personne () values(? , ? , ? , ? , ? , ?)";
        try {
            PreparedStatement Pstmt = SingletonDataBase.getInstance().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            Pstmt.setString(1, null);
            Pstmt.setString(2, professeur.getNom());
            Pstmt.setString(3, professeur.getPrenom());
            Pstmt.setString(4, professeur.getVille());
            Pstmt.setString(5, professeur.getTelephone());
            Pstmt.setString(6, professeur.getDateNaissance());

            Pstmt.executeUpdate();

            // Récupération du dernier ID inséré
            ResultSet rs = Pstmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if (rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            professeur.setId(lastInsertedId);

            String query1 = "INSERT INTO professeur values(? , ? , ? , ? , ? , ?)";
            PreparedStatement Pstmt1 = SingletonDataBase.getInstance().prepareStatement(query1);

            Pstmt1.setString(1, null);
            Pstmt1.setBoolean(2, professeur.isVacant());
            Pstmt1.setString(3, professeur.getMatiereEnseigner());
            Pstmt1.setString(4, professeur.getProchainCour());
            Pstmt1.setString(5, professeur.getSujetProchaineReunion());
            Pstmt1.setInt(6, lastInsertedId);

            Pstmt1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeur;

    }

    @Override
    public void modifier(Professeur professeur) {
        String queryUpdateProfesseur = "UPDATE professeur SET vacant = ? , matiereEnseigne = ? , prochainCour = ? , sujetProchaineReunion = ? WHERE id_Personne = ?";
        String queryUpdatePersonne = "UPDATE Personne SET nom = ? , prenom = ? , ville = ? , telephone = ? , dateNaissance = ?  WHERE id_Personne = ?";
        try {
            PreparedStatement pstmtProfesseur = SingletonDataBase.getInstance().prepareStatement(queryUpdateProfesseur);
            PreparedStatement pstmtPersonne = SingletonDataBase.getInstance().prepareStatement(queryUpdatePersonne);

            // Définir les nouveaux paramètres pour le professeur
            pstmtProfesseur.setBoolean(1, professeur.isVacant());
            pstmtProfesseur.setString(2, professeur.getMatiereEnseigner());
            pstmtProfesseur.setString(2, professeur.getProchainCour());
            pstmtProfesseur.setString(2, professeur.getSujetProchaineReunion());
            pstmtProfesseur.setInt(3, professeur.getId());

            pstmtPersonne.setString(1, professeur.getNom());
            pstmtPersonne.setString(2, professeur.getPrenom());
            pstmtPersonne.setString(3, professeur.getVille());
            pstmtPersonne.setString(4, professeur.getTelephone());
            pstmtPersonne.setString(5, professeur.getDateNaissance());
            pstmtPersonne.setInt(6, professeur.getId());

            // Exécuter la requête
            int rowsAffectedProfesseur = pstmtProfesseur.executeUpdate();
            int rowsAffectedPersonne = pstmtPersonne.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffectedProfesseur > 0 && rowsAffectedPersonne > 0) {
                System.out.println("Le professeur a été modifié avec succès.");
            } else {
                System.out.println("Échec de la modification de du professeur.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean supprimer(int identifiant) {

        String deleteProfesseurQuery = "DELETE FROM professeur WHERE id_Personne = ?";
        String deletePersonneQuery = "DELETE FROM Personne WHERE id_Personne = ?";

        boolean estSupprimer = false;

        try {
            // Exécution de la requête DELETE
            PreparedStatement deleteStmtProfesseur = SingletonDataBase.getInstance().prepareStatement(deleteProfesseurQuery);
            PreparedStatement deleteStmtPersonne = SingletonDataBase.getInstance().prepareStatement(deletePersonneQuery);

            deleteStmtProfesseur.setInt(1, identifiant);
            deleteStmtPersonne.setInt(1, identifiant);

            int rowsAffectedProfesseur = deleteStmtProfesseur.executeUpdate();
            int rowsAffectedPersonne = deleteStmtPersonne.executeUpdate();

            if (rowsAffectedProfesseur > 0 && rowsAffectedPersonne > 0) {
                System.out.println("Le professeur avec l'identifiant " + identifiant + " a été supprimé.");
                estSupprimer = true;
            } else {
                System.out.println("Aucun professeur trouvé avec l'  " + identifiant + ". Suppression non effectuée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estSupprimer;
    }

    @Override
    public List<Professeur> listeProfesseur() {

        List<Professeur> professeurs = new ArrayList<>();

        String query = "SELECT DISTINCT * FROM professeur,Personne WHERE professeur.id_Personne=Personne.id_Personne";

        try {
            PreparedStatement pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                professeurs.add(
                        new Professeur(
                                rs.getInt("id_Professeur"),
                                rs.getString("dateNaissance"),
                                rs.getString("ville"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("telephone"),
                                rs.getBoolean("vacant"),
                                rs.getString("matiereEnseigner"),
                                rs.getString("prochainCour"),
                                rs.getString("sujetProchaineReunion")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professeurs;
    }

    @Override
    public void obtenirProfesseur(int identifiant) {
        String query = "SELECT pr.vacant, pr.matiereEnseigne, pr.prochainCour , pr.sujetProchaineReunion ,  p.nom, p.prenom, p.ville, p.telephone, p.dateNaissance " +
                "FROM professeur pr " +
                "JOIN Personne p ON pr.id_Personne = p.id_Personne " +
                "WHERE e.id_Personne = ?";

        try {
            PreparedStatement pstmt1 = SingletonDataBase.getInstance().prepareStatement(query);
            pstmt1.setInt(1, identifiant);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                System.out.printf("""
                        Les informations du professeur que vous venez d'ajouter :
                            VACANT : %s
                            NOM : %s
                            PRENOM : %s
                            VILLE : %s
                            TELEPHONE : %s
                            DATE DE NAISSANCE : %s
                            MATIERE ENSEIGNER : %s
                            PROCHAIN COUR : %s
                            SUJET PROCHAINE COUR : %s
                        """,
                        rs.getBoolean("vacant"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("ville"),
                        rs.getString("telephone"),
                        rs.getDate("dateNaissance"), // Si c'est un champ de type DATE
                        rs.getString("matiereEnseigne"),
                        rs.getString("prochainCour"),
                        rs.getString("sujetProchaineReunion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
