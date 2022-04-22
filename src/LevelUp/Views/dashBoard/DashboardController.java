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
    private AnchorPane userPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent x = FXMLLoader.load(getClass().getResource("../userScreen/user-dashboard.fxml"));
            userPane.getChildren().add(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
