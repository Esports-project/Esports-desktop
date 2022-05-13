package Esprit.Views.userScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Reclamation;
import Esprit.Entities.User;
import Esprit.Services.ServiceReclamation;
import Esprit.Services.ServiceUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class UserDashboardController implements Initializable {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colUsername;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colNom;

    @FXML
    private TableColumn colPrenom;

    @FXML
    private TableColumn colRole;

    @FXML
    private TableColumn colBanned;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField role;

    private User user;

    ObservableList<User> listM;
    int index1 = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    updateTable();

    }

    public void updateTable() {

        colId.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<User, Integer>("roles"));
        colBanned.setCellValueFactory(new PropertyValueFactory<User, Integer>("banned"));

        listM = MySqlConnect.getDataUser();
        tableView.setItems(listM);
    }

    public void editUser(ActionEvent e){
        ServiceUser sr = new ServiceUser();
        User user = tableView.getSelectionModel().getSelectedItem();
        /*sr.editUser(new User(
                user.getId(),
                user.getRoles(),
                lastname.getText(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getBanned(),
        ));*/
        updateTable();
    }

    public  void deleteUser(ActionEvent e){
        ServiceUser se = new ServiceUser();
        User u = tableView.getSelectionModel().getSelectedItem();
        se.deleteUser(u);
        updateTable();
    }



}
