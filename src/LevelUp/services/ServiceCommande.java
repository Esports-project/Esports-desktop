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
    public void addCommande(CartEntry cart) {
        try {
            Commande c=new Commande();
            ServiceCommande servCom = new ServiceCommande();
            ServiceLigneCommande servLc = new ServiceLigneCommande();
            java.sql.Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            c.setDate(date1);
            c.setStatus("In Progress");
            c.setPrix_total(ShoppingCart.getInstance().calculateTotal());
            c.setQuantite(ShoppingCart.getInstance().calculateNumber());
            c.setUser_id(1);
            List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
            for(int i=0; i<entries.size(); i++){
                LigneCommande lc = new LigneCommande();
                lc.setIdProduit(entries.get(i).getProduit().getId());
                lc.setQuantite(entries.get(i).getQuantity());
                lc.setIdCommande(c.getId());
                servLc.addLigneCommande(lc);
            }
            String requete = "INSERT INTO commande (date1,quantite,prix_total,user_id,status) VALUES(?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, date1);
            pst.setInt(2, c.getQuantite());
            pst.setFloat(3, c.getPrix_total());
            pst.setInt(4, c.getUser_id());
            pst.setString(5, c.getStatus());
            pst.executeUpdate();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        /* ----------------- Read ----------------- */
    public List<Commande> commandeList() {
        List<Commande> myList = new ArrayList<Commande>();
        return myList;
    }

}
