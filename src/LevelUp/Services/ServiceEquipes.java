package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Equipes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEquipes {

    private Connection cnx;
    private PreparedStatement ste;

    public  ServiceEquipes() { cnx = MyConnection.getInstance().getConnection();
    }



    public  void ajoutEquipe(Equipes e){
        try {
            String query = "INSERT INTO equipe (nom) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(query);

            pst.setString(1, e.getNom());

            pst.executeUpdate();
            System.out.println(e);
        } catch (SQLException ex){
            System.err.println(ex.getMessage());
        }

    }

    public List<Equipes> equipesList(){
        List<Equipes> monList = new ArrayList<Equipes>();
        String query = "SELECT * from equipe";
        try{
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                Equipes equipes = new Equipes();
                equipes.setId(rs.getInt(1));
                equipes.setNom(rs.getString(2));
                monList.add(equipes);
            }

        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        return monList;
    }

    public void modifierEquipe(Equipes e){
        String query = "UPDATE equipe SET nom=? WHERE id=?";
        try{
            ste = cnx.prepareStatement(query);

            ste.setInt(2, e.getId());
            ste.setString(1, e.getNom());

            ste.executeUpdate();
            System.out.println("Equipe modifié");

        }catch (SQLException ex){
            System.err.println(ex.getMessage());


        }
    }

    public void supprimerEquipe(Equipes e){
        String query = "DELETE FROM equipe WHERE id = ?";
        try{
            ste = cnx.prepareStatement(query);
            ste.setInt(1,e.getId());
            ste.executeUpdate();
            System.out.println("Equipe deleted");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }


    }
    public  int getIdFromName(String name){
        int i = 0;
        String query = "SELECT id from equipe WHERE nom=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setString(1, name);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                i = (rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }

    public  String getNameFromID(Integer id){
        String name = "";
        String query = "SELECT nom from equipe WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                 name = (rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return name;
    }

}
