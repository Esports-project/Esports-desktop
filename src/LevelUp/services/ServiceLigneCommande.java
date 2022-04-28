package Esprit.services;

import Esprit.entities.*;
import Esprit.Connection.MyConnection;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ServiceLigneCommande {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public ServiceLigneCommande() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /* ----------------- Create ----------------- */
    public void addLigneCommande(LigneCommande lc) {
        try {
            String requete = "INSERT INTO ligne_commande (produit_id,quantite,commande_id) VALUES(?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setInt(1, lc.getIdProduit());
            pst.setInt(2, lc.getQuantite());
            pst.setInt(3, lc.getIdCommande());

            pst.executeUpdate();
            System.out.println(lc);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<LigneCommande> LigneCommandeList() {
        List<LigneCommande> myList = new ArrayList<LigneCommande>();
        String requete = "SELECT * from ligne_commande";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                LigneCommande ligne = new LigneCommande();
                ligne.setId(rs.getInt(1));
                ligne.setIdProduit(rs.getInt(2));
                ligne.setQuantite(rs.getInt(3));
                ligne.setIdCommande(rs.getInt(4));

                myList.add(ligne);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    /* ----------------- Update ----------------- */
    public void editLigneCommande(LigneCommande lc) throws SQLException {
        String requete = "UPDATE ligne_commande SET produit_id =?,quantite=? ,commande_id =? WHERE id=?";
        try {
            ste= cnx.prepareStatement(requete);
            ste.setInt(4, lc.getId());
            ste.setInt(1, lc.getIdProduit());
            ste.setInt(2, lc.getQuantite());
            ste.setInt(3, lc.getIdCommande());

            ste.executeUpdate();
            System.out.println("Ligne Commande Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Ligne Commande non Modfié !");
        }
    }

    /* ----------------- Delete ----------------- */
    public void deleteLigneCommande(LigneCommande lc) {
        String requete = "DELETE FROM ligne_commande WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            ste.setInt(1, lc.getId());
            ste.executeUpdate();
            System.out.println("Ligne Commande Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
