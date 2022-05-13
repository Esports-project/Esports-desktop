package Esprit.Views.teamsScreen;


import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Classement;
import Esprit.Entities.Equipes;
import Esprit.Entities.Games;
import Esprit.Entities.Reclamation;
import Esprit.Services.ServiceClassement;
import Esprit.Services.ServiceEquipes;
import Esprit.Services.ServiceGames;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;


public class TeamsDashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button updateTeam;
    @FXML
    private Button editer;


    @FXML
    private TextField nom;

    @FXML
    private Button sendBtn;



    @FXML
    private TableView<Equipes> tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colNom;

    ObservableList<Equipes> listM;


    // this function runs on startup
     @Override
    public void initialize(URL location, ResourceBundle resources){
    updateTable();
    updateTeam.setVisible(false);
         editer.setOnAction(e -> {
             Equipes equipes = tableView.getSelectionModel().getSelectedItem();
             updateTeam.setVisible(true);
             sendBtn.setVisible(false);
             nom.setText(equipes.getNom());
         });
     }

    public  void deleteTeam(ActionEvent e){
        ServiceEquipes se = new ServiceEquipes();
        Equipes c = tableView.getSelectionModel().getSelectedItem();
        se.supprimerEquipe(c);
        updateTable();
    }
    public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Equipes, String>("nom"));
        listM = MySqlConnect.getDataEquipes();
        tableView.setItems(listM);
    }

     public void handleCLick(ActionEvent actionEvent) throws IOException {
        ServiceEquipes seq = new ServiceEquipes();
         if(actionEvent.getSource() == sendBtn){
             seq.ajoutEquipe(new Equipes(
                     nom.getText()
             ));
             updateTable();
         }

     }

    @FXML
    public void updateTeams(ActionEvent actionEvent) throws SQLException {
        ObservableList<Games> listM;
        ServiceEquipes serviceEquipes = new ServiceEquipes();
        Equipes equipes = tableView.getSelectionModel().getSelectedItem();
        if (actionEvent.getSource() == updateTeam) {
            String name = nom.getText();
            equipes.setNom(name);
            serviceEquipes.modifierEquipe(equipes);
            //tableView.setItems(listM);
            updateTeam.setVisible(false);
            sendBtn.setVisible(true);
            clearTexts();
            updateTable();
        }
    }

    void clearTexts(){
        nom.clear();
    }
}
