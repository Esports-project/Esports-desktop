package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Blog;
import Esprit.Entities.Evenement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceBlog {

    private Connection cnx;
    private PreparedStatement ste;

    public ServiceBlog() {
        cnx = MyConnection.getInstance().getConnection();
    }


    public void createBlog(Blog b) {
        try {
            String req = "INSERT INTO blog (titre, contenu,  image, post_date) VALUES (?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, b.getTitre());
            st.setString(2, b.getContenu());
            st.setString(3, b.getImage());
            st.setDate(4, b.getPost_date());

            st.executeUpdate();
            System.out.println("blog ajoutée avec succes.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void modifyBlog(Blog b) {
        String sql = "UPDATE blog SET titre=?, contenu=?, image=?, post_date=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, b.getTitre());
            ste.setString(2, b.getContenu());
            ste.setString(3, b.getImage());
            ste.setDate(4, b.getPost_date());
            ste.setInt(5, b.getId());

            ste.executeUpdate();
            System.out.println("Blog avec succees !");
            System.out.println(b);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteBlog(Blog b) {
        try {

            if (b.getId() != 0) {
                String sql = "delete from blog WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, b.getId());
                st.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Blog> readBlogs() {
        ArrayList<Blog> blog = new ArrayList();
        String req = "SELECT * FROM blog";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                blog.add(
                        new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return blog;
    }

}
