package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Category;
import Esprit.Entities.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceCategory {

    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public ServiceCategory() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public  int getIdFromName(String name){
        int i = 0;
        String query = "SELECT id from categories WHERE name=?";
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

    /* ----------------- Create ----------------- */
    public void addCategory(Category c) {
        try {
            String query = "INSERT INTO category (name) VALUES(?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, c.getName());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Category> categoryList() {
        List<Category> myList = new ArrayList<Category>();
        String query = "SELECT * from categories";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                myList.add(category);
            }
            System.out.println(myList);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    /* ----------------- Update ----------------- */
    public void editCategory(Category c) throws SQLException {
        String query = "UPDATE category SET name=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);

            ste.setInt(2, c.getId());
            ste.setString(1, c.getName());

            ste.executeUpdate();
            System.out.println("Message Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Category non Modfié !");
        }
    }


    /* ----------------- Delete ----------------- */
    public void deleteCategory(Category c) {
        String query = "DELETE FROM category WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, c.getId());
            ste.executeUpdate();
            System.out.println("Category Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
