/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
 
import Entities.review;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dorra
 */
public class reviewServices implements IServiceReview  {
    
    //var
    Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void createReview(review R) {

        //request
        try {
            String req = "INSERT INTO `review`(`note`, `avis`, `jeux_id_id`) VALUES (?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req); 
            System.out.println(st.toString());
            System.out.println(R.getNote());
            System.out.println(R.getAvis());
            System.out.println(R.getJeux_id_id());
            st.setDouble(1, R.getNote());
            st.setString(2, R.getAvis());
            st.setInt(3, R.getJeux_id_id());
            st.executeUpdate();
            System.out.println("Avis ajoutée avec succes.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public ObservableList<review> readReview() {
       
         ObservableList<review> reviews = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM review";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                reviews.add(new review(rs.getInt("id"),rs.getInt("jeux_id_id"),rs.getDouble("note"),rs.getString("avis")));
 
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reviews;
    }

 public ObservableList<review> getreviews(int aInt) throws SQLException
     {
 ObservableList<review> categories = FXCollections.observableArrayList();

       // List<Categorie> categories = new ArrayList<>();
        String req = "select * from review where jeux_id_id="+aInt;
        Statement st = cnx.createStatement();
        //ensemble de resultat
        ResultSet rst = st.executeQuery(req);

        while (rst.next()) {
            review c;
            c = new review (
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getInt("jeux_id_id"),
                    rst.getInt("note"),
                    rst.getString("avis")

            );
            categories.add(c);
        }
        return categories;
     }

    
}
