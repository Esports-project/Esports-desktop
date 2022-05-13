package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class ServiceUser {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    private Stage mStage;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pwField;

    @FXML
    private Label messageField;

    @FXML
    private Button loginBtn;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }


    public ServiceUser() {
        cnx = MyConnection.getInstance().getConnection();
    }
    /* ----------------- Create ----------------- */
    public void addUser(User u) {
        try {
            String query = "INSERT INTO user (nom,prenom,email,date_join,password,username,banned, roles) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            pst.setString(1, u.getLastname());
            pst.setString(2, u.getName());
            pst.setString(3, u.getEmail());
            pst.setInt(4, 0);
            pst.setDate(5, date);
            pst.setString(6, u.getPassword());
            pst.setString(7, u.getUsername());
            pst.setInt(8, 0);
            pst.setInt(9, 0);
            pst.executeUpdate();
            System.out.println(u);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    /* ----------------- Read ----------------- */
    public List<User> userList() {
        List<User> myList = new ArrayList<User>();
        String query = "SELECT * from user";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setLastname(rs.getString(4));
                user.setName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setRoles(rs.getInt(11));
                user.setBanned(rs.getInt(12));
                myList.add(user);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    /* ----------------- Update ----------------- */
    public void editUser(User u) throws SQLException {
        String requete = "UPDATE user SET nom=?, prenom=? ,email=?, username=?, password=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            ste.setInt(7, u.getId());
            if(u.getLastname() != null) ste.setString(1, u.getLastname());
            if(u.getName() != null) ste.setString(2, u.getName());
            if(u.getEmail() != null) ste.setString(3, u.getEmail());
            if(u.getUsername() != null) ste.setString(5, u.getUsername());
            if(u.getPassword() != null) ste.setString(6, u.getPassword());
            ste.executeUpdate();
            System.out.println("User Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("User non Modfié !");
        }
    }
    /* ----------------- Delete ----------------- */
    public void deleteUser(User user) {
        String query = "DELETE FROM user WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, user.getId());
            ste.executeUpdate();
            System.out.println("User Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}
