package Esprit.Views.dashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private StackPane pane;

    @FXML
    private Button reclamationBtn;

    @FXML
    private AnchorPane recPane;

    @FXML
    private AnchorPane eventPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent reclamationpage = FXMLLoader.load(getClass().getResource("../reclamationScreen/reclamation-dashboard.fxml"));
            recPane.getChildren().add(reclamationpage);

            Parent eventpage = FXMLLoader.load(getClass().getResource("../eventScreen/event-dashboard.fxml"));
            eventPane.getChildren().add(eventpage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
