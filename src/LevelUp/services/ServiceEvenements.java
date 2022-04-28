package LevelUp.Services;



import LevelUp.Connection.MyConnection;
import LevelUp.Entities.Evenement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
public class ServiceEvenements {
    private Connection cnx;
    private PreparedStatement ste;

    public ServiceEvenements() {
        cnx = MyConnection.getInstance().getConnection();
    }
    public void createEvenement(Evenement E) {
        try {
            String req = "INSERT INTO `evenement`(`nom`, `organisateur`,  `description`, `image`,`date`) VALUES (?,?,?,?,?)";
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
    
    public Map<String,Integer> readStatsPerMonth() {
        Map<String,Integer> events = new HashMap<String, Integer>();
        String req = "SELECT DATE_FORMAT(date, '%Y/%m'), COUNT(*) FROM evenement GROUP BY DATE_FORMAT(date, '%Y/%m')";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                events.put(rs.getString(1), rs.getInt(2));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }

    public void modifyEvenement(Evenement e) {
        String sql = "UPDATE evenement SET nom=?, organisateur=?, description=?, image=?, date=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, e.getNom());
            ste.setString(2, e.getOrganisateur());
            ste.setString(3, e.getDescription());
            ste.setString(4, e.getImage());
            ste.setDate(5, e.getDate());
            ste.setInt(6, e.getId());

            ste.executeUpdate();
            System.out.println("modification avec succees !");
            System.out.println(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEvenement(Evenement e) {
        try {

            if (e.getId() != 0) {
                String sql = "delete from evenement WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, e.getId());
                st.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Evenement> readEvenementsWithSearch(String stxt) {
        ArrayList<Evenement> events = new ArrayList();
        stxt = "%" + stxt + "%";
        String req = "SELECT * FROM evenement WHERE nom LIKE ? OR organisateur LIKE ? OR description LIKE ?;";
        System.out.println(stxt);
        try {
            PreparedStatement st;

            st= cnx.prepareStatement(req);
            st.setString(1, stxt);
            st.setString(2, stxt);
            st.setString(3, stxt);
            System.out.println("prepared statement : " + st);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                events.add(
                        new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6)));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }
    
    public List<Evenement> readEvenements() {
        ArrayList<Evenement> events = new ArrayList();
        String req = "SELECT * FROM evenement";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                events.add(
                        new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6)));

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
