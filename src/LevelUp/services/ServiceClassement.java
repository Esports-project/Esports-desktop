package LevelUp.Services;


import LevelUp.Connection.MyConnection;
import LevelUp.Entities.Classement;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import javafx.util.Pair;

public class ServiceClassement {
    private Connection cnx;
    private PreparedStatement ste;


    public ServiceClassement() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void createClassement(Classement C) {

        //request
        String req = "INSERT INTO `classement` (`rang`, `equipe_id`, `evenement_id`) VALUES (?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, C.getRang());
            st.setInt(2, C.getEquipe_id());
            st.setInt(3, C.getEvenement_id());

            st.executeUpdate();
            System.out.println("classement ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }

    public void modifyClassement(Classement C) {
        try {

            String sql = "UPDATE classement SET rang=?, equipe_id=?, evenement_id=? WHERE id=?";
            ste = cnx.prepareStatement(sql);


            ste.setInt(1, C.getRang());
            ste.setInt(2, C.getEquipe_id());
            ste.setInt(3, C.getEvenement_id());
            ste.setInt(4, C.getId());

            ste.executeUpdate();
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
        List<Classement> classements = new ArrayList<Classement>();
        String req = "SELECT * FROM classement";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                classements.add(new Classement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return classements;
    }
    
    public List<Pair<Integer,String>> readEquipeCombos() {
        List<Pair<Integer,String>> equipes = new ArrayList<Pair<Integer,String>>();
        String req = "SELECT id,nom FROM equipe";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                equipes.add(new Pair<Integer,String>(rs.getInt(1),rs.getString(2)));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return equipes;
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
