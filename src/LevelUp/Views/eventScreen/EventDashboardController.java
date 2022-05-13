package Esprit.Views.eventScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Evenement;
import Esprit.Entities.Games;
import Esprit.Entities.Reclamation;
import Esprit.Services.ServiceEvenements;
import Esprit.Services.ServiceGames;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.sql.Date;

import java.util.ResourceBundle;

public class EventDashboardController implements Initializable {

    @FXML
    private TableView<Evenement> tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colNom;

    @FXML
    private TableColumn colOrg;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colImage;

    @FXML
    private TableColumn colDate;

    @FXML
    private TextField nom;

    @FXML
    private TextField organisateur;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button filepicker;

    private String imageFile;

    private Evenement ev;

    ObservableList<Evenement> listM;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    updateTable();

    }

    public void updateTable() {

        colId.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("Id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        colOrg.setCellValueFactory(new PropertyValueFactory<Evenement, String>("organisateur"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        colImage.setCellValueFactory(new PropertyValueFactory<Evenement, String>("image"));
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, java.util.Date>("date"));

        listM = MySqlConnect.getDataEvenement();
        tableView.setItems(listM);
    }

    public void ajoutImage(ActionEvent e){
        FileChooser filechooser = new FileChooser();
        File f = filechooser.showOpenDialog(null);

        if(f != null){
            imageFile = f.getAbsolutePath();
        }
    }

    public void ajoutEvenement(ActionEvent e) throws ParseException {
        ServiceEvenements evenements = new ServiceEvenements();
        java.util.Date date =
                java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Evenement evenement = new Evenement(
                nom.getText(),
                organisateur.getText(),
                description.getText(),
                imageFile,
                sqlDate
        );
        evenements.createEvenement(evenement);
        updateTable();
    }

    public  void deleteEvent(ActionEvent e){
        ServiceEvenements se = new ServiceEvenements();
        Evenement ev = tableView.getSelectionModel().getSelectedItem();
        se.deleteEvenement(ev);
        updateTable();
    }



}
