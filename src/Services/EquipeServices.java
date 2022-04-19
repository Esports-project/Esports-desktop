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
import Services.IServiceEquipe;

/**
 *
 * @author dorra
 */
public class EquipeServices implements IServiceEquipe {
    
    
     //var
    MyDB instance = MyDB.getInstance();
    Connection cnx = instance.getCnx();
public void createEquipe(Equipe E ) {

        //request
        String req = "INSERT INTO `equipe`(`nom`) VALUES (?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);           
            st.setString(1, E.getNom());
            st.executeUpdate();
            System.out.println("Equipe ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        
        

    }



    public void modifyEquipe(Equipe E) {
        try {

                String sql = "UPDATE equipe SET nom=? WHERE id=?";

                PreparedStatement st = cnx.prepareStatement(sql);
                st.setString(1, E.getNom());
                st.setInt(2, E.getId());

                st.executeUpdate();
                System.out.println("Modification avec succees !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteEquipe(int id) {
        try {

            if (id != 0) {
                String sql = "delete from equipe WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, id);
                st.executeUpdate();
                System.out.println("Deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Equipe> readEquipe() {
        ArrayList<Equipe> equipes = new ArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM equipe";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                equipes.add(new Equipe(rs.getInt("id"), rs.getString("nom")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return equipes;
    }
    
    public Equipe readEquipeById(int id) {

        try {
            String req = "SELECT * FROM equipe where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return (new Equipe(rs.getInt("id"), rs.getString("nom")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    
    

    
}
