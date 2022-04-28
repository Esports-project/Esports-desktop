package Esprit.services;

import Esprit.entities.*;

import Esprit.Connection.MyConnection;

import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;


public class ServiceCommande {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public ServiceCommande() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /* ----------------- Create ----------------- */
    public void addCommande() {
        try {
            ServiceCommande serviceCommande = new ServiceCommande();
            ServiceProduit serviceProduit = new ServiceProduit();
            Commande comm = new Commande();
            Commande co = new Commande();
            comm.setStatus("In Progress");
            comm.setUser_id(0);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            comm.setDate(date);
            comm.setPrix_total(ShoppingCart.getInstance().calculateTotal());
            ServiceLigneCommande serlc = new ServiceLigneCommande();
            List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
            comm.setQuantite(entries.size());
            java.sql.Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String requete = "INSERT INTO commande (date,quantite,prix_total,user_id,status) VALUES(?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, (Date) comm.getDate());
            pst.setInt(2, comm.getQuantite());
            pst.setFloat(3, comm.getPrix_total());
            pst.setInt(4, comm.getUser_id());
            pst.setString(5, comm.getStatus());
            pst.executeUpdate();
            comm = serviceCommande.getLastCommande();
            System.out.println(comm);
            for (CartEntry cart : entries) {
                LigneCommande lc = new LigneCommande();
                lc.setIdCommande(comm.getId());
                lc.setQuantite(cart.getQuantity());
                lc.setIdProduit(cart.getProduit().getId());
                serlc.addLigneCommande(lc);
                Produit p = new Produit();
                p = serviceProduit.getProduitByRef(cart.getProduit().getReferance());
                serviceProduit.reduceQuantityComm(p,cart.getQuantity());
                ShoppingCart.getInstance().removeProduct(cart.getProduit().getReferance());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* ----------------- Read ----------------- */
    public List<Commande> commandeList() {
        List<Commande> myList = new ArrayList<Commande>();
        return myList;
    }

    public Commande getLastCommande() {
        try {
            Commande comm = new Commande();
            PreparedStatement myStmt = cnx.prepareStatement("SELECT * FROM commande ORDER BY id DESC LIMIT 1;");
            ResultSet myRes = myStmt.executeQuery();
            while (myRes.next()) {
                comm.setId(myRes.getInt("id"));
                comm.setDate(myRes.getDate("date"));
                comm.setQuantite(myRes.getInt("quantite"));
                comm.setPrix_total(myRes.getFloat("prix_total"));
                comm.setUser_id(myRes.getInt("user_id"));
                comm.setStatus(myRes.getString("status"));
                System.out.println(comm);
                return comm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
