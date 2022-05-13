package Esprit.Views.gameScreen;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

public class GameDashboardController implements Initializable {
    @FXML
    private AnchorPane rootpane;

    @FXML
    private Button editer;

    @FXML
    private Button updateGame;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TableView<Games> tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn<Games, String> colDesc;


    // This function runs on startup
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateGame.setVisible(false);
        updateTable();

        // On update button clicked, filling textfield with data to be modified
        editer.setOnAction(e -> {
            Games games = tableView.getSelectionModel().getSelectedItem();
            updateGame.setVisible(true);
            addBtn.setVisible(false);
            name.setText(games.getNom());
            description.setText(games.getDescription());
        });
    }

    public void updateTable(){
        ObservableList<Games> listM;
        colId.setCellValueFactory(new PropertyValueFactory<Games, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Games, String>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Games, String>("description"));
        listM = MySqlConnect.getGames();
        tableView.setItems(listM);
    }

    public void addGame(ActionEvent e){
        ServiceGames games = new ServiceGames();
        Games game = new Games(
                name.getText(),
                description.getText()
        );
        games.addGame(game);
        updateTable();
    }

    public void edited(){
        System.out.println("yes");
    }



    public  void deleteGame(ActionEvent e){
        ServiceGames games = new ServiceGames();
        Games game = tableView.getSelectionModel().getSelectedItem();
        games.deleteGame(game);
        updateTable();
    }

    @FXML
    public void updateGame(ActionEvent actionEvent) throws SQLException {
        ObservableList<Games> listM;
        ServiceGames serviceGames = new ServiceGames();
        Games games = tableView.getSelectionModel().getSelectedItem();
        if (actionEvent.getSource() == updateGame) {
            boolean active = true;
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String nom = name.getText();
            String des = description.getText();
            games.setNom(nom);
            games.setDescription(des);
            serviceGames.editGame(games);
            //tableView.setItems(listM);
            updateGame.setVisible(false);
            addBtn.setVisible(true);
            clearTexts();
            updateTable();
        }
    }

    void clearTexts(){
        name.clear();
        description.clear();
    }

}
