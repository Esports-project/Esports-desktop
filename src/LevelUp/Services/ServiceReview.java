package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Evenement;
import Esprit.Entities.Reclamation;
import Esprit.Entities.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceReview {

    private Connection cnx;

    private PreparedStatement ste;

    public ServiceReview() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addReview(Review r){
        try {
            String query = "INSERT INTO review (jeux_id_id,note,avis) VALUES(?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);


            pst.setInt(1, r.getGameId());
            pst.setInt(2, r.getScore());
            pst.setString(3, r.getAvis());

            pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Review> reviewList() {
        List<Review> myList = new ArrayList<Review>();
        String query = "SELECT * from review";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setGameId(rs.getInt(2));
                review.setScore(rs.getInt(3));
                review.setAvis(rs.getString(4));
                myList.add(review);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public List<Integer> readReviewById(int id) {
        List<Integer> list = new ArrayList<Integer>();
        try {
            String req = "SELECT * FROM review WHERE jeux_id_id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {

                list.add(rs.getInt("note"))  ;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }



    /* ----------------- Update ----------------- */
    public void editReview(Review r)  {
        String requete = "UPDATE review SET jeux_id_id=?, note=? ,avis=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);

            ste.setInt(4, r.getId());
            ste.setInt(1, r.getGameId());
            ste.setInt(2, r.getScore());
            ste.setString(3, r.getAvis());

            ste.executeUpdate();
            System.out.println("Review Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Review non Modfié !");
        }
    }


    /* ----------------- Delete ----------------- */
    public void deleteReview(Review r) {
        String query = "DELETE FROM r WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, r.getId());
            ste.executeUpdate();
            System.out.println("Review Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
