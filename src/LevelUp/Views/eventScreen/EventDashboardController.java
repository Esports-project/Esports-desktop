package Esprit.Views.eventScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Evenement;
import Esprit.Entities.Games;
import Esprit.Entities.Produit;
import Esprit.Entities.Reclamation;
import Esprit.Services.ServiceEvenements;
import Esprit.Services.ServiceGames;
import Esprit.Services.ServiceProduit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.Calendar;
import java.util.ResourceBundle;

public class EventDashboardController implements Initializable {

    @FXML
    private TableView<Evenement> tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private Button updateEvent;

    @FXML
    private Button editer;

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
    private Button ajout;

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
    int index1 = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    updateTable();
        updateEvent.setVisible(false);

        editer.setOnAction(e -> {
            Evenement event = tableView.getSelectionModel().getSelectedItem();
            updateEvent.setVisible(true);
            ajout.setVisible(false);
            nom.setText(event.getNom());
            description.setText(event.getDescription());
            organisateur.setText(event.getOrganisateur());
            datePicker.setValue(event.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        });

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

    @FXML
    public void updateProd(ActionEvent actionEvent) throws SQLException {
        ServiceEvenements serviceEvenements = new ServiceEvenements();
        Evenement evenement = tableView.getSelectionModel().getSelectedItem();
        if (actionEvent.getSource() == updateEvent) {
            String name = nom.getText();
            String des = description.getText();
            String org = organisateur.getText();
            java.util.Date date = java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            evenement.setNom(name);
            evenement.setDescription(des);
            evenement.setOrganisateur(org);
            evenement.setDate(sqlDate);
            if(imageFile != null) evenement.setImage(imageFile);
            serviceEvenements.modifyEvenement(evenement);
            tableView.setItems(listM);
            updateEvent.setVisible(false);
            ajout.setVisible(true);
            clearTexts();
            updateTable();
        }
    }

    void clearTexts(){
        nom.clear();
        description.clear();
        organisateur.clear();
    }



}
