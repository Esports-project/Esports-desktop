package Esprit.Views.homeScreen;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import  javafx.scene.control.Button;
import javafx.stage.Stage;

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
    private Button teamsBtn;

    @FXML
    private Button dashBoardBtn;;

    @FXML
    private Button msgBtn;

    @FXML
    private Button gameBtn;

    @FXML
    private AnchorPane reclamationPane;

    @FXML
    private AnchorPane blogPane;

    @FXML
    private AnchorPane rankingPane;

    @FXML
    private AnchorPane messagePane;

    @FXML
    private AnchorPane eventPane;

    @FXML
    private AnchorPane storePane;

    @FXML
    private AnchorPane teamsPane;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private Pane homePane;



    public void handleClicks(ActionEvent actionEvent) {
        if(actionEvent.getSource() == teamsBtn){
            teamsPane.toFront();
        }
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
        if(actionEvent.getSource() == msgBtn){
            messagePane.toFront();
        }
        if(actionEvent.getSource() == gameBtn){
            gamePane.toFront();
        }

        if (actionEvent.getSource() == dashBoardBtn){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../dashBoard/dashboard.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent recPage = FXMLLoader.load(getClass().getResource("../reclamationScreen/reclamation-view.fxml"));
            reclamationPane.getChildren().add(recPage);

            Parent rankPage = FXMLLoader.load(getClass().getResource("../rankingScreen/ranking-view.fxml"));
            rankingPane.getChildren().add(rankPage);

            Parent gamePage = FXMLLoader.load(getClass().getResource("../gameScreen/game-view.fxml"));
            gamePane.getChildren().add(gamePage);

            Parent eventPage = FXMLLoader.load(getClass().getResource("../eventScreen/event-view.fxml"));
            eventPane.getChildren().add(eventPage);

            Parent blogPage = FXMLLoader.load(getClass().getResource("../blogScreen/blog-view.fxml"));
            blogPane.getChildren().add(blogPage);

            Parent messagePage = FXMLLoader.load(getClass().getResource("../messageScreen/msg-view.fxml"));
            messagePane.getChildren().add(messagePage);

            AnchorPane storePage = FXMLLoader.load(getClass().getResource("../storeScreen/store-view.fxml"));
            AnchorPane.setTopAnchor(storePage, 10.0);
            AnchorPane.setBottomAnchor(storePage, 10.0);
            AnchorPane.setLeftAnchor(storePage, 10.0);
            AnchorPane.setRightAnchor(storePage, 10.0);
            storePane.getChildren().setAll(storePage);


            Parent teamsPage = FXMLLoader.load(getClass().getResource("../teamsScreen/teams-view.fxml"));
            teamsPane.getChildren().add(teamsPage);


            homePane.toFront();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}


