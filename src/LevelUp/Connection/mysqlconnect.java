package Esprit.Connection;

import Esprit.entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlconnect {

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

    public static ObservableList<Produit> getDatausers(){
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

}
