package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.User;
import Esprit.HelloApplication;
import Esprit.Views.homeScreen.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private PasswordField passwordField;

    @FXML
    private Label messageField;

    @FXML
    private Button loginBtn;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }

    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        validateLogin();
    }

    public void validateLogin() {
        MyConnection connectNow = new MyConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user WHERE email='" + email.getText() + "' AND password='" + passwordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    messageField.setText("Congratulations!");
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Views/homeScreen/home-view.fxml"));
                    Parent root;
                    root = loader.load();
                    HomeController dashboard = loader.getController();
                    dashboard.setStage(mStage);

                    mStage.setTitle("second scene");
                    mStage.setScene(new Scene(root));
                    mStage.show();
                } else {
                    messageField.setText("Invalid login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    public ServiceUser() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /* ----------------- Create ----------------- */
    public void addUser(User u) {
        try {
            String query = "INSERT INTO user (nom,prenom,email,phone,date_join,password,username,banned, roles) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getEmail());
            pst.setInt(4, 0);
            pst.setDate(5, date);
            pst.setString(6, u.getPassword());
            pst.setString(7, u.getUsername());
            pst.setInt(8, 0);
            pst.setString(9, u.getRoles());

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
                user.setDepartement_id(rs.getInt(2));
                user.setEquipe_id(rs.getInt(3));
                user.setNom(rs.getString(4));
                user.setPrenom(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPhone(rs.getInt(7));
                user.setDate_join(rs.getDate(8));
                user.setUsername(rs.getString(9));
                user.setPassword(rs.getString(10));
                user.setRoles(rs.getString(11));
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
        String requete = "UPDATE user SET nom=?, prenom=? ,email=? ,phone=?, username=?, password=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            ste.setInt(7, u.getId());
            if(u.getNom() != null) ste.setString(1, u.getNom());
            if(u.getPrenom() != null) ste.setString(2, u.getPrenom());
            if(u.getEmail() != null) ste.setString(3, u.getEmail());
            if(u.getPhone() != null) ste.setInt(4, u.getPhone());
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
