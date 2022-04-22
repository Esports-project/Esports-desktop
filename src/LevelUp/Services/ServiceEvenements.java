package Esprit.Services;



import Esprit.Connection.MyConnection;
import Esprit.Entities.Evenement;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;
public class ServiceEvenements {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public void createEvenement(Evenement E) {

        //request
        String req = "INSERT INTO `evenement`(`nom`, `organisateur`,  `description`, `image`,`date`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, E.getNom());
            st.setString(2, E.getOrganisateur());
            st.setString(3, E.getDescription());
            st.setString(4, E.getImage());
            st.setDate(5, E.getDate());

            st.executeUpdate();
            System.out.println("evenement ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }

    public void modifyEvenement(Evenement E) {
        try {

            String sql = "UPDATE evenement SET nom=?, organisateur=?, description=?, image=?, date=? WHERE id=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, E.getNom());
            st.setString(2, E.getOrganisateur());
            st.setString(3, E.getDescription());
            st.setString(4, E.getImage());
            st.setDate(5, E.getDate());
            st.setInt(6, E.getId());

            st.executeUpdate();
            System.out.println("modification avec succees !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteEvenement(int id) {
        try {

            if (id != 0) {
                String sql = "delete from evenement WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, id);
                st.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Evenement> readEvenements() {
        ArrayList<Evenement> events = new ArrayList();
        String req = "SELECT * FROM evenement";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                events.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("organisateur"), rs.getString("description"), rs.getString("image"), rs.getDate("date")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }

    public Evenement readEvenementById(int id) {

        try {
            String req = "SELECT * FROM evenement where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return (new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("organisateur"), rs.getString("description"), rs.getString("image"), rs.getDate("date")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
