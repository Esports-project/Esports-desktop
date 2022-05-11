package Esprit.Views.rankingScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Classement;
import Esprit.Entities.Reclamation;
import Esprit.Services.ServiceClassement;
import Esprit.Services.ServiceEquipes;
import Esprit.Services.ServiceEvenements;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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

public class RankingDashboardController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    @FXML
    private TextField rangArea;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colRang;

    @FXML
    private TableColumn colEId;

    @FXML
    private TableColumn colEveId;


    @FXML
    private ComboBox equipeList;

    @FXML
    private ComboBox evenementList;



    @FXML
    private Button sendBtn;

    ObservableList<Classement> listM;
    int index1 = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Generate event options
        ServiceEvenements sev = new ServiceEvenements();
        sev.readEvenements().forEach(eve -> {
            evenementList.getItems().addAll(eve.getNom());
        });

        // Generate equipe options
        ServiceEquipes se = new ServiceEquipes();
        se.equipesList().forEach(e -> {
            equipeList.getItems().addAll(e.getNom());
        });

        updateTable();
    }

    public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<Classement, Integer>("id"));
        colRang.setCellValueFactory(new PropertyValueFactory<Classement, String>("rang"));
        colEId.setCellValueFactory(new PropertyValueFactory<Classement, String>("equipe_id"));
        colEveId.setCellValueFactory(new PropertyValueFactory<Classement, String>("evenement_id"));
        listM = MySqlConnect.getDataClassement();
        tableView.setItems(listM);
    }

    public void handleClick(ActionEvent actionEvent) throws IOException {
        ServiceClassement sc = new ServiceClassement();
        ServiceEvenements sev = new ServiceEvenements();
        ServiceEquipes seq = new ServiceEquipes();

        if (actionEvent.getSource() == sendBtn) {
            String rangVal = rangArea.getText();
            String equipeVal = equipeList.getValue().toString();
            String evenementVal = evenementList.getValue().toString();
            int rang = Integer.parseInt(rangVal);
            int equipeId = seq.getIdFromName(equipeVal);
            int evenementId = sev.getIdFromName(evenementVal);

            sc.createClassement(new Classement(
                    rang,
                    equipeId,
                    evenementId
            ));

            rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("ranking-dashboard.fxml")));
        }
    }



}
