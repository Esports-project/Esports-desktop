package Esprit.Services;

import Esprit.Entities.*;

import Esprit.Connection.MyConnection;

import java.sql.*;
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
    public void addCommande(Commande c) {

    }

    /* ----------------- Read ----------------- */
    public List<Commande> commandeList() {
        List<Commande> myList = new ArrayList<Commande>();
        return myList;
    }

}
