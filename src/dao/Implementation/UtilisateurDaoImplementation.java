package dao.Implementation;

import dao.IUtilisateurDAO;
import dao.SingletonDataBase;
import models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoImplementation implements IUtilisateurDAO {

    public  boolean authentification(String identifiant , String motDePasse ){

        PreparedStatement Pstmt = null;
        ResultSet rs = null;
        boolean connexion = false;

        try{
            String query = "SELECT  * FROM utilisateur WHERE utilisateur.pseudo = ? AND utilisateur.motDePasse = ?";
            Pstmt = SingletonDataBase.getInstance().prepareStatement(query);

            Pstmt.setString(1, identifiant);
            Pstmt.setString(2, motDePasse);

            rs = Pstmt.executeQuery();

            while (rs.next()) {
                connexion = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  connexion;

    }

    @Override
    public boolean ajouterCompte(String identifiant, String motDePasse)  {

        String query = "INSERT INTO utilisateur (pseudo,motDePasse) VALUES (?,?)";
        boolean ajouteCompte = false;

        try {

            PreparedStatement Pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            Pstmt.setString(1, identifiant);
            Pstmt.setString(2, motDePasse);
            Pstmt.executeUpdate();

            ResultSet rs = Pstmt.getGeneratedKeys();

            if(rs.next()){
                ajouteCompte = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return ajouteCompte;
    }

    @Override
    public boolean modifierMotDePasse(String identifiant, String motDePassa) {

        boolean modifierMotDePasse = false;
        String query = "SELECT * FROM utilisateur WHERE pseudo = ?";
        String queryUpdate = "UPDATE utilisateur SET motDePasse = ? , pseudo = ? WHERE id_Utilisateur = ?";

        try{
            PreparedStatement Pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            Pstmt.setString(1, identifiant);

            ResultSet rs = Pstmt.executeQuery();

            if (rs.next()) {

                PreparedStatement updatePstmt = SingletonDataBase.getInstance().prepareStatement(queryUpdate);
                updatePstmt.setString(1, motDePassa);
                updatePstmt.setString(2, identifiant);
                updatePstmt.setInt(3, rs.getInt("id_Utilisateur"));

                updatePstmt.executeUpdate();

                modifierMotDePasse =  true;

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return modifierMotDePasse;
    }

    @Override
    public boolean supprimerCompte(String identifiant, String motDePasse) {

        boolean supprimerCompte = false;
        String query = "SELECT * FROM utilisateur WHERE pseudo = ? and utilisateur.motDePasse = ?";
        String queryDelete = "DELETE FROM utilisateur WHERE pseudo = ? and utilisateur.motDePasse = ?";

        try{
            PreparedStatement Pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            Pstmt.setString(1, identifiant);
            Pstmt.setString(2, motDePasse);
            Pstmt.executeUpdate();

            ResultSet rs = Pstmt.getGeneratedKeys();

            if (rs.next()) {
                PreparedStatement deletePstmt = SingletonDataBase.getInstance().prepareStatement(queryDelete);
                deletePstmt.setString(1, identifiant);
                deletePstmt.setString(2, motDePasse);
                deletePstmt.executeUpdate();

                supprimerCompte = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return supprimerCompte;
    }


    @Override
    public List<Utilisateur> listerUtilisateurs() {

        String query = "SELECT * FROM utilisateur";
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        try{

            PreparedStatement Pstmt = SingletonDataBase.getInstance().prepareStatement(query);
            ResultSet rs = Pstmt.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getInt("id_Utilisateur"));
                utilisateur.setPseudo(rs.getString("pseudo"));
                utilisateur.setMotDePasse(rs.getString("motDePasse"));
                utilisateur.setDateCreation(rs.getDate("dateCreation"));
                utilisateurs.add(utilisateur);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return utilisateurs;
    }

}
