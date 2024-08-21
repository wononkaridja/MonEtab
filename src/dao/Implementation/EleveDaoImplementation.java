package dao.Implementation;

import dao.IEleveDAO;
import dao.SingletonDataBase;
import models.Eleve;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EleveDaoImplementation implements IEleveDAO {

    @Override
    public Eleve ajouter(Eleve eleve) {

        String query = "INSERT INTO Personne () values(? , ? , ? , ? , ? , ?)";
        try {
            PreparedStatement Pstmt = SingletonDataBase.getInstance().prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            Pstmt.setString(1, null);
            Pstmt.setString(2, eleve.getNom());
            Pstmt.setString(3, eleve.getPrenom());
            Pstmt.setString(4, eleve.getVille());
            Pstmt.setString(5, eleve.getTelephone());
            Pstmt.setString(6, eleve.getDateNaissance());

            Pstmt.executeUpdate();

            // Récupération du dernier ID inséré
            ResultSet rs = Pstmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if (rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            eleve.setId(lastInsertedId);

            String query1 = "INSERT INTO Eleve values(? , ? , ? , ? )";
            PreparedStatement Pstmt1 = SingletonDataBase.getInstance().prepareStatement(query1);

            Pstmt1.setString(1, null);
            Pstmt1.setString(2, eleve.getMatricule());
            Pstmt1.setString(3, eleve.getClasse());
            Pstmt1.setInt(4, lastInsertedId);

            Pstmt1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eleve;
    }

    public Eleve verifierEleve(String matricule) {
        Eleve eleve = null;

        String query = "SELECT DISTINCT * FROM Eleve , Personne WHERE Eleve.matricule = ? AND Eleve.id_Personne=Personne.id_Personne";
        try {
            PreparedStatement pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            pstmt.setString(1, matricule);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                eleve = new Eleve(
                        rs.getInt("id_Personne"),
                        rs.getString("dateNaissance"),
                        rs.getString("ville"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("classe"),
                        rs.getString("matricule"));

            } else {
                System.out.println("Elève non trouvé !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eleve;

    }

    @Override
    public boolean supprimer(int identifiant) {

        String deleteEleveQuery = "DELETE FROM Eleve WHERE id_Personne = ?";
        String deletePersonneQuery = "DELETE FROM Personne WHERE id_Personne = ?";

        boolean estSupprimer = false;

        try {
            // Exécution de la requête DELETE
            PreparedStatement deleteStmtEleve = SingletonDataBase.getInstance().prepareStatement(deleteEleveQuery);
            PreparedStatement deleteStmtPersonne = SingletonDataBase.getInstance()
                    .prepareStatement(deletePersonneQuery);
            deleteStmtEleve.setInt(1, identifiant);
            deleteStmtPersonne.setInt(1, identifiant);

            int rowsAffectedEleve = deleteStmtEleve.executeUpdate();
            int rowsAffectedPersonne = deleteStmtPersonne.executeUpdate();

            if (rowsAffectedEleve > 0 && rowsAffectedPersonne > 0) {
                System.out.println("L'élève avec l'identifiant " + identifiant + " a été supprimé.");
                estSupprimer = true;
            } else {
                System.out.println("Aucun élève trouvé avec l'  " + identifiant + ". Suppression non effectuée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estSupprimer;
    }

    @Override
    public List<Eleve> listeEleve() {
        List<Eleve> eleves = new ArrayList<>();

        String query = "SELECT DISTINCT * FROM Eleve,Personne WHERE Eleve.id_Personne=Personne.id_Personne";

        try {
            PreparedStatement pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                eleves.add(
                        new Eleve(
                                rs.getInt("id_Eleve"),
                                rs.getString("dateNaissance"),
                                rs.getString("ville"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("telephone"),
                                rs.getString("classe"),
                                rs.getString("matricule")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eleves;
    }

    @Override
    public void obtenirEleve(int identifiant) {

        String query = "SELECT e.matricule, e.classe, p.nom, p.prenom, p.ville, p.telephone, p.dateNaissance " +
                "FROM Eleve e " +
                "JOIN Personne p ON e.id_Personne = p.id_Personne " +
                "WHERE e.id_Personne = ?";

        try (PreparedStatement pstmt1 = SingletonDataBase.getInstance().prepareStatement(query)) {
            pstmt1.setInt(1, identifiant);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                System.out.printf("""
                        Les informations de l'élève que vous venez d'ajouter :
                            MATRICULE : %s
                            NOM : %s
                            PRENOM : %s
                            VILLE : %s
                            TELEPHONE : %s
                            DATE DE NAISSANCE : %s
                            CLASSE : %s
                        """,
                        rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("ville"),
                        rs.getString("telephone"),
                        rs.getDate("dateNaissance"), // Si c'est un champ de type DATE
                        rs.getString("classe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifier(Eleve eleve) {
        String queryUpdateEleve = "UPDATE Eleve SET matricule = ? , classe = ? WHERE id_Personne = ?";
        String queryUpdatePersonne = "UPDATE Personne SET nom = ? , prenom = ? , ville = ? , telephone = ? , dateNaissance = ?  WHERE id_Personne = ?";
        try {
            PreparedStatement pstmtEleve = SingletonDataBase.getInstance().prepareStatement(queryUpdateEleve);
            PreparedStatement pstmtPersonne = SingletonDataBase.getInstance().prepareStatement(queryUpdatePersonne);

            // Définir les nouveaux paramètres pour l'élève
            pstmtEleve.setString(1, eleve.getMatricule());
            pstmtEleve.setString(2, eleve.getClasse());
            pstmtEleve.setInt(3, eleve.getId());

            pstmtPersonne.setString(1, eleve.getNom());
            pstmtPersonne.setString(2, eleve.getPrenom());
            pstmtPersonne.setString(3, eleve.getVille());
            pstmtPersonne.setString(4, eleve.getTelephone());
            pstmtPersonne.setString(5, eleve.getDateNaissance());
            pstmtPersonne.setInt(6, eleve.getId());

            // Exécuter la requête
            int rowsAffectedEleve = pstmtEleve.executeUpdate();
            int rowsAffectedPersonne = pstmtPersonne.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffectedEleve > 0 && rowsAffectedPersonne > 0) {
                System.out.println("L'élève a été modifié avec succès.");
            } else {
                System.out.println("Échec de la modification de l'élève.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
