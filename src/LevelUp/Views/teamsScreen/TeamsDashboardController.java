package Esprit.Views.teamsScreen;


import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Equipes;
import Esprit.Entities.Reclamation;
import Esprit.Services.ServiceEquipes;
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
import java.util.Date;
import java.util.ResourceBundle;


public class TeamsDashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField nom;

    @FXML
    private Button sendBtn;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colNom;

    ObservableList<Equipes> listM;
    int index1 = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

     @Override
    public void initialize(URL location, ResourceBundle resources){
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
         }
         rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("teams-dashboard.fxml")));
     }
}
