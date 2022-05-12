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
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class GameDashboardController implements Initializable {
    @FXML
    private AnchorPane rootpane;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateTable();
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

}
