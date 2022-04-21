package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Reclamation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceReclamation {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public ServiceReclamation() {
        cnx = MyConnection.getInstance().getConnection();
    }



    /* ----------------- Create ----------------- */
    public void addReclamation(Reclamation r) {
        try {
            String query = "INSERT INTO reclamation (user_id,sujet,email,description,date,status,category_id) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            pst.setInt(1, 0);
            pst.setString(2, r.getSubject());
            pst.setString(3, r.getEmail());
            pst.setString(4, r.getDescription());
            pst.setDate(5, date);
            pst.setInt(6,0);
            pst.setInt(7,r.getCategory_id());

            pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Reclamation> reclamationList() {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String query = "SELECT * from reclamation";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setId(rs.getInt(1));
                reclamation.setUser_id(rs.getInt(2));
                reclamation.setSubject(rs.getString(3));
                reclamation.setEmail(rs.getString(4));
                reclamation.setDescription(rs.getString(5));
                reclamation.setDate(rs.getDate(6));
                reclamation.setStatus(rs.getInt(7));
                reclamation.setCategory_id(rs.getInt(8));
                myList.add(reclamation);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    /* ----------------- Update ----------------- */
    public void editReclamation(Reclamation r) throws SQLException {
        String requete = "UPDATE reclamation SET user_id=?, sujet=? ,email=? ,description=?, status=?, category_id=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            ste.setInt(7, r.getId());
            if(r.getUser_id() != null) ste.setInt(1, r.getUser_id());
            if(r.getSubject() != null) ste.setString(2, r.getSubject());
            if(r.getEmail() != null) ste.setString(3, r.getEmail());
            if(r.getDescription() != null) ste.setString(4, r.getDescription());
            if(r.getStatus() != null) ste.setInt(5, r.getStatus());
            if(r.getCategory_id() != null) ste.setInt(6, r.getCategory_id());

            ste.executeUpdate();
            System.out.println("Reclamation Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Reclamation non Modfié !");
        }
    }


    /* ----------------- Delete ----------------- */
    public void deleteReclamation(Reclamation reclamation) {
        String query = "DELETE FROM reclamation WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, reclamation.getId());
            ste.executeUpdate();
            System.out.println("Reclamation Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
