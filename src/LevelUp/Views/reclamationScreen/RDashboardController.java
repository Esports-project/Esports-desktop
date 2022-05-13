package Esprit.Views.reclamationScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Produit;
import Esprit.Entities.Reclamation;
import Esprit.Views.eventScreen.ItemController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
