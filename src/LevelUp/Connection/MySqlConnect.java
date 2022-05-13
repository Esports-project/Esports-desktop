package Esprit.Connection;

import Esprit.Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/levelup","root","");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<User> getDataUser(){
        Connection conn = ConnectDb();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(10));
                user.setEmail(rs.getString(6));
                user.setLastname(rs.getString(4));
                user.setName(rs.getString(5));
                user.setRoles(rs.getInt(13));
                user.setBanned(rs.getInt(12));

                list.add(user);
            }
        } catch (Exception e) {
        }

        System.out.println(list);
        return list;
    }

    public static ObservableList<Produit> getDataProduit(){
        Connection conn = ConnectDb();
        ObservableList<Produit> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit prod = new Produit();
                prod.setId(rs.getInt(1));
                prod.setNom(rs.getString(2));
                prod.setQuantity(rs.getInt(3));
                prod.setPrice(rs.getFloat(4));
                prod.setDescription(rs.getString(5));
                prod.setImage(rs.getString(6));
                prod.setSolde(rs.getFloat(7));
                prod.setActive(rs.getBoolean(8));
                prod.setReferance(rs.getString(9));
                prod.setUpdatedAt(rs.getDate(10));

                list.add(prod);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Reclamation> getDataReclamation(){
        Connection conn = ConnectDb();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reclamation");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt(1));
                rec.setUser_id(rs.getInt(2));
                rec.setSubject(rs.getString(3));
                rec.setEmail(rs.getString(4));
                rec.setDescription(rs.getString(5));
                rec.setDate(rs.getDate(6));
                rec.setStatus(rs.getInt(7));
                rec.setCategory_id(rs.getInt(8));

                list.add(rec);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Evenement> getDataEvenement(){
        Connection conn = ConnectDb();
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from evenement");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evenement eve = new Evenement();
                eve.setId(rs.getInt(1));
                eve.setNom(rs.getString(2));
                eve.setOrganisateur(rs.getString(3));
                eve.setDescription(rs.getString(4));
                eve.setImage(rs.getString(5));
                eve.setDate(rs.getDate(6));

                list.add(eve);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Classement> getDataClassement(){
        Connection conn = ConnectDb();
        ObservableList<Classement> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from classement");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Classement cls = new Classement();
                cls.setId(rs.getInt(1));
                cls.setRang(rs.getInt(2));
                cls.setEquipe_id(rs.getInt(3));
                cls.setEvenement_id(rs.getInt(4));
                list.add(cls);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Equipes> getDataEquipes(){
        Connection conn = ConnectDb();
        ObservableList<Equipes> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from equipe");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Equipes eqe = new Equipes();
                eqe.setId(rs.getInt(1));
                eqe.setNom(rs.getString(2));

                list.add(eqe);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<LigneCommande> getLigneCommande(){
        Connection conn = ConnectDb();
        ObservableList<LigneCommande> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from ligne_commande");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LigneCommande lc = new LigneCommande();
                lc.setId(rs.getInt(1));
                lc.setIdProduit(rs.getInt(2));
                lc.setQuantite(rs.getInt(3));
                lc.setIdCommande(rs.getInt(4));
                list.add(lc);
                System.out.println(lc);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Games> getGames(){
        Connection conn = ConnectDb();
        ObservableList<Games> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from jeux");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Games g = new Games();
                g.setId(rs.getInt(1));
                g.setNom(rs.getString(2));
                g.setDescription(rs.getString(3));

                list.add(g);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Messages> getMessages(){
        Connection conn = ConnectDb();
        ObservableList<Messages> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from message");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Messages m = new Messages();
                m.setId(rs.getInt(1));
                m.setMessage(rs.getString(4));
                list.add(m);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Blog> getBlogs(){
        Connection conn = ConnectDb();
        ObservableList<Blog> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from blog");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog g = new Blog();
                g.setId(rs.getInt(1));
                g.setTitre(rs.getString(2));
                g.setContenu(rs.getString(3));
                g.setImage(rs.getString(4));
                g.setPost_date(rs.getDate(5));



                list.add(g);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Commande> getDataCommandes(){
        Connection conn = ConnectDb();
        ObservableList<Commande> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from commande");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commande comm = new Commande();
                comm.setId(rs.getInt(1));
                comm.setDate(rs.getDate(2));
                comm.setQuantite(rs.getInt(3));
                comm.setPrix_total(rs.getFloat(4));
                comm.setUser_id(rs.getInt(5));
                comm.setStatus(rs.getString(6));
                list.add(comm);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
