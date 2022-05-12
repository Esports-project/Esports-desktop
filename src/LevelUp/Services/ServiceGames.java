package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Games;
import Esprit.Entities.Reclamation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceGames {

    private Connection cnx;
    private PreparedStatement ste;


    public ServiceGames() {
        cnx = MyConnection.getInstance().getConnection();
    }



    /* ----------------- Create ----------------- */
    public void addGame(Games g) {
        try {
            String query = "INSERT INTO jeux (nom,description) VALUES(?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);

            pst.setString(1, g.getNom());
            pst.setString(2, g.getDescription());


            pst.executeUpdate();
            System.out.println(g);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Games> gameList() {
        List<Games> myList = new ArrayList<Games>();
        String query = "SELECT * from jeux";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Games games = new Games();
                games.setId(rs.getInt(1));
                games.setNom(rs.getString(2));
                games.setDescription(rs.getString(3));

                myList.add(games);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    /* ----------------- Update ----------------- */
    public void editGame(Games g)  {
        String requete = "UPDATE jeux SET nom=?, description=?  WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);

            ste.setInt(3, g.getId());
            ste.setString(1, g.getNom());
            ste.setString(2, g.getDescription());

            ste.executeUpdate();
            System.out.println("Game Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Game non Modfié !");
        }
    }


    /* ----------------- Delete ----------------- */
    public void deleteGame(Games g) {
        String query = "DELETE FROM jeux WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, g.getId());
            ste.executeUpdate();
            System.out.println("Game Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
