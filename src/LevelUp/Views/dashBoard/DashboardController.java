package Esprit.Views.dashBoard;

import javafx.event.ActionEvent;
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
    private Button eventBtn;

    @FXML
    private Button rankingBtn;



    @FXML
    private AnchorPane recPane;

    @FXML
    private AnchorPane eventPane;

    @FXML
    private AnchorPane rankingPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent reclamationpage = FXMLLoader.load(getClass().getResource("../reclamationScreen/reclamation-dashboard.fxml"));
            recPane.getChildren().add(reclamationpage);

            Parent eventpage = FXMLLoader.load(getClass().getResource("../eventScreen/event-dashboard.fxml"));
            eventPane.getChildren().add(eventpage);

            Parent rankingpage = FXMLLoader.load(getClass().getResource("../rankingScreen/ranking-dashboard.fxml"));
            rankingPane.getChildren().add(rankingpage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleClicks(ActionEvent e){
        if(e.getSource() == eventBtn){
            eventPane.toFront();
        }
        if(e.getSource() == reclamationBtn){
            recPane.toFront();
        }
        if(e.getSource() == rankingBtn ){
            rankingPane.toFront();
        }
    }
}
