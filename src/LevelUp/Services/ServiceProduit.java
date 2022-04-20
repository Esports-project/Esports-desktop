package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Produit;

import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class ServiceProduit {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public ServiceProduit() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /* ----------------- Create ----------------- */
    public void addProduit(Produit p) {
        try {
            String requete = "INSERT INTO produit (nom,quantity,price,description,image,solde,active,referance,updated_at) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            pst.setString(1, p.getNom());
            pst.setInt(2, p.getQuantity());
            pst.setFloat(3, p.getPrice());
            pst.setString(4, p.getDescription());
            pst.setString(5, p.getImage());
            pst.setFloat(6, p.getSolde());
            pst.setBoolean(7, p.isActive());
            pst.setString(8, p.getReferance());
            pst.setDate(9, date);

            pst.executeUpdate();
            System.out.println(p);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Produit> produitList() {
        List<Produit> myList = new ArrayList<Produit>();
        String requete = "SELECT * from Produit";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
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

                myList.add(prod);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    /* ----------------- Update ----------------- */
    public void editProduit(Produit p) throws SQLException {
        String requete = "UPDATE produit SET nom=?,quantity=? ,price=? ,description=?, image=?, solde=?, active=?,referance=?,updated_at=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            ste.setInt(10, p.getId());
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getQuantity());
            ste.setFloat(3, p.getPrice());
            ste.setString(4, p.getDescription());
            ste.setString(5, p.getImage());
            ste.setFloat(6, p.getSolde());
            ste.setBoolean(7, p.isActive());
            ste.setString(8, p.getReferance());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            ste.setDate(9, date);

            ste.executeUpdate();
            System.out.println("Produit Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Produit non Modfié !");
        }
    }


    /* ----------------- Delete ----------------- */
    public void deleteProduit(Produit prod) {
        String requete = "DELETE FROM produit WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            ste.setInt(1, prod.getId());
            ste.executeUpdate();
            System.out.println("Product Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
