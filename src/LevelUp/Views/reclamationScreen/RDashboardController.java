package Esprit.Views.reclamationScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Classement;
import Esprit.Entities.Produit;
import Esprit.Entities.Reclamation;
import Esprit.Services.ServiceClassement;
import Esprit.Views.eventScreen.ItemController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import Esprit.Services.ServiceReclamation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RDashboardController implements Initializable {

   @FXML
   private TableView<Reclamation> tableView;

   @FXML
   private TableColumn<Reclamation, Integer> colID;



    @FXML
    private TableColumn<Reclamation, String> colSubject;

    @FXML
    private TableColumn<Reclamation, String> colEmail;

    @FXML
    private TableColumn<Reclamation, String> colDescription;

    @FXML
    private TableColumn<Reclamation, Date> colDate;

    @FXML
    private TableColumn<Reclamation, Integer> colStatus;



    @FXML
    public Pane rootPane;

    ObservableList<Reclamation> listM;

    @FXML
    private TextArea replyField;

    @FXML
    private Button replyBtn;

    @FXML
    private TextField subject;

    @FXML
    private TextField email;

    @FXML
    private TextField description;

    @FXML
    private TextField status;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d")) {
                    status.setText(oldValue);
                }
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                email.setText(newSelection.getEmail());
                subject.setText(newSelection.getSubject());
                description.setText(newSelection.getDescription());
                status.setText(newSelection.getStatus().toString());
            }
        });
        updateTable();
    }


    public void deleteRec(){
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation c = tableView.getSelectionModel().getSelectedItem();
        sr.deleteReclamation(c);
        updateTable();
    }

    public void replyRec(){
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation c = tableView.getSelectionModel().getSelectedItem();

    }

    public void editRec(){
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation c = tableView.getSelectionModel().getSelectedItem();
        sr.editReclamation(new Reclamation(
                c.getId(),
                c.getUser_id(),
                subject.getText(),
                email.getText(),
                description.getText(),
                c.getDate(),
                Integer.parseInt(status.getText()),
                c.getCategory_id()
        ));
        updateTable();
    }

    public void updateTable() {
        colID.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        colSubject.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("subject"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("email"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("status"));
        listM = MySqlConnect.getDataReclamation();
        tableView.setItems(listM);
    }

}
