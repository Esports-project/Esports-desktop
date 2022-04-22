package Esprit.Views.userScreen;

import Esprit.Connection.MyConnection;
import Esprit.Entities.User;
import Esprit.Services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.sql.*;
import java.util.Calendar;


public class UItemController {

    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label username;

    @FXML
    private Label email;

    @FXML
    private Label password;

    @FXML
    private TextField new_firstname;

    @FXML
    private TextField new_lastname;

    @FXML
    private TextField new_username;

    @FXML
    private TextField new_email;

    @FXML
    private TextField new_password;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    private User u;

    public void deleteUser(ActionEvent e){
        ServiceUser su = new ServiceUser();
        su.deleteUser(u);
        UDashboardController rd = new UDashboardController();
    }

    public UItemController() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /* ----------------- Update ----------------- */
    public void editUser(ActionEvent e) throws SQLException {
        String requete = "UPDATE user SET nom=?, prenom=? ,email=?, username=?, password=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            ste.setInt(6, u.getId());
            if (!new_lastname.getText().isEmpty() || !new_firstname.getText().isEmpty() || !new_lastname.getText().isEmpty() || !new_email.getText().isEmpty()|| !new_username.getText().isEmpty() || !new_password.getText().isEmpty()) {
                ste.setString(1, new_lastname.getText());
                ste.setString(2, new_firstname.getText());
                ste.setString(3, new_email.getText());
                ste.setString(4, new_username.getText());
                ste.setString(5, new_password.getText());
            } else {
                System.out.println("Please fill the empty fields");
            }

            ste.executeUpdate();
            System.out.println("User Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("User non Modfié !");
        }
    }



    public void setData(User user){
        firstname.setText(user.getPrenom());
        lastname.setText(user.getNom());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        password.setText(user.getPassword());

        u = user;
    }

}
