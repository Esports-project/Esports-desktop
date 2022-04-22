package Esprit.Services;


import Esprit.Connection.MyConnection;
import Esprit.Entities.Classement;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class ServiceClassement {
    private Connection cnx;

    public void createClassement(Classement C) {

        //request
        String req = "INSERT INTO `classement` (`rang`, `equipe_id`, `evenement_id`) VALUES (?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, C.getRang());
            st.setInt(2, C.getEquipe_id());
            st.setInt(3, C.getEvenement_id());

            st.executeUpdate();
            System.out.println("evenement ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }

    public void modifyClassement(Classement C) {
        try {

            String sql = "UPDATE classement SET rang=?, equipe_id=?, evenement_id=? WHERE id=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, C.getRang());
            st.setInt(2, C.getEquipe_id());
            st.setInt(3, C.getEvenement_id());
            st.setInt(4, C.getId());

            st.executeUpdate();
            System.out.println("modification avec succees !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteClassement(int id) {
        try {

            if (id != 0) {
                String sql = "delete from classement WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, id);
                st.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Classement> readClassements() {
        ArrayList<Classement> classements = new ArrayList();
        String req = "SELECT * FROM classement";
        try {
            Statement st ;
            st = MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                classements.add(new Classement(rs.getInt("id"), rs.getInt("rang"), rs.getInt("equipe_id"), rs.getInt("evenement_id")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return classements;
    }

    public Classement readClassementById(int id) {

        try {
            String req = "SELECT * FROM classement where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return (new Classement(rs.getInt("id"), rs.getInt("rang"), rs.getInt("equipe_id"), rs.getInt("evenement_id")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
