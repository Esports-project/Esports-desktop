/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Equipe;
import Entities.Jeux;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import Services.IServiceJeux;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dorra
 */
public class JeuxServices implements IServiceJeux {
    
    
     //var
    Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void createJeux(Jeux J) {

        //request
        try {
            String req = "INSERT INTO `jeux`(`nom`, `description`) VALUES (?,?)";
            PreparedStatement st = cnx.prepareStatement(req); 
            System.out.println(st.toString());
            System.out.println(J.getNom());
            System.out.println(J.getDescription());
            st.setString(1, J.getNom());
            st.setString(2, J.getDescription());
            st.executeUpdate();
            System.out.println("Jeux ajoutée avec succes.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifyJeux(Jeux J , int id) {
        try {

                String sql = "UPDATE Jeux SET nom=?,description=? WHERE id=?";

                PreparedStatement st = cnx.prepareStatement(sql);
                st.setString(1, J.getNom());
                st.setString(2, J.getDescription());
                st.setInt(3,id);



                st.executeUpdate();
                System.out.println("Modification avec succees !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteJeux(int id) {
        try {

            if (id != 0) {
                String sql = "delete from Jeux WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, id);
                st.executeUpdate();
                System.out.println("Deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Jeux> readJeux() {
       
         ObservableList<Jeux> Jeuxs = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM Jeux";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Jeuxs.add(new Jeux(rs.getInt("id"), rs.getString("nom"),rs.getString("description")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Jeuxs;
    }
    
    public Jeux readJeuxById(int id) {

        try {
            String req = "SELECT * FROM Jeux where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return (new Jeux(rs.getInt("id"), rs.getString("nom"),rs.getString("description")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public void modifyJeux(Jeux J) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Jeux getById(int aInt) throws SQLException {
    Jeux jeu = new Jeux();

        String req = "select * from jeux where id="+aInt;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
          
            jeu = new Jeux (
                     rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("description")
                     
                                
            );
           
        }
        return jeu;
      
    }


    
}
