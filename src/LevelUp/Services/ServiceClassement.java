package Esprit.Services;


import Esprit.Connection.MyConnection;
import Esprit.Entities.Classement;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class ServiceClassement {
    private Connection cnx;
    private PreparedStatement ste;


    public ServiceClassement() {
        cnx = MyConnection.getInstance().getConnection();
    }



    public void createClassement(Classement c) {

        //request

        try {
            String req = "INSERT INTO classement (rang, equipe_id, evenement_id) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1, c.getRang());
            pst.setInt(2, c.getEquipe_id());
            pst.setInt(3, c.getEvenement_id());

            pst.executeUpdate();
            System.out.println("evenement ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }

    public void modifyClassement(Classement c) {
        try {
            System.out.println("Trying");

            String sql = "UPDATE classement SET rang=?, equipe_id=?, evenement_id=? WHERE id=?";
            ste = cnx.prepareStatement(sql);


            ste.setInt(1, c.getRang());
            ste.setInt(2, c.getEquipe_id());
            ste.setInt(3, c.getEvenement_id());
            ste.setInt(4, c.getId());

            ste.executeUpdate();
            System.out.println("modification avec succees !");

        } catch (SQLException ex) {
            System.out.println(c);
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
