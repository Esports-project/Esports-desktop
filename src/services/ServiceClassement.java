/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Classement;
import util.MaConnexion;
import interfaces.IServiceClassement;

/**
 *
 * @author admin
 */
public class ServiceClassement implements IServiceClassement {

    //var
    MaConnexion instance = MaConnexion.getInstance();
    Connection cnx = instance.getCnx();

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

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM classement";
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
