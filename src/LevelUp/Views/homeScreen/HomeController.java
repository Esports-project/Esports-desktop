package Esprit.Views.homeScreen;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import  javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button homeBtn;

    @FXML
    private Button blogBtn;

    @FXML
    private Button eventsBtn;

    @FXML
    private Button storeBtn;

    @FXML
    private Button rankingBtn;

    @FXML
    private Button reclamationBtn;

    @FXML
    private Button dashBoardBtn;

    @FXML
    private AnchorPane reclamationPane;

    @FXML
    private AnchorPane blogPane;

    @FXML
    private AnchorPane rankingPane;

    @FXML
    private AnchorPane eventPane;

    @FXML
    private AnchorPane storePane;

    @FXML
    private Pane homePane;


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == blogBtn) {
            blogPane.toFront();
        }
        if (actionEvent.getSource() == eventsBtn) {
            eventPane.toFront();
        }
        if (actionEvent.getSource() == rankingBtn) {
            rankingPane.toFront();
        }
        if (actionEvent.getSource() == storeBtn) {
            storePane.toFront();
        }
        if (actionEvent.getSource() == reclamationBtn) {
            reclamationPane.toFront();
        }
        if (actionEvent.getSource() == homeBtn){
            homePane.toFront();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent recPage = FXMLLoader.load(getClass().getResource("../reclamationScreen/reclamation-view.fxml"));
            reclamationPane.getChildren().add(recPage);

            Parent rankPage = FXMLLoader.load(getClass().getResource("../rankingScreen/ranking-view.fxml"));
            rankingPane.getChildren().add(rankPage);

            Parent eventPage = FXMLLoader.load(getClass().getResource("../eventScreen/event-view.fxml"));
            eventPane.getChildren().add(eventPage);

            Parent blogPage = FXMLLoader.load(getClass().getResource("../blogScreen/blog-view.fxml"));
            blogPane.getChildren().add(blogPage);

            Parent storePage = FXMLLoader.load(getClass().getResource("../storeScreen/store-view.fxml"));
            storePane.getChildren().add(storePage);

            homePane.toFront();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}


