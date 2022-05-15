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
import javafx.scene.text.Text;
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
            AnchorPane.setTopAnchor(recPage, 10.0);
            AnchorPane.setBottomAnchor(recPage, 10.0);
            AnchorPane.setLeftAnchor(recPage, 10.0);
            AnchorPane.setRightAnchor(recPage, 10.0);
            reclamationPane.getChildren().add(recPage);

            Parent rankPage = FXMLLoader.load(getClass().getResource("../rankingScreen/ranking-view.fxml"));
            AnchorPane.setTopAnchor(rankPage, 10.0);
            AnchorPane.setBottomAnchor(rankPage, 10.0);
            AnchorPane.setLeftAnchor(rankPage, 10.0);
            AnchorPane.setRightAnchor(rankPage, 10.0);
            rankingPane.getChildren().add(rankPage);

            Parent gamePage = FXMLLoader.load(getClass().getResource("../gameScreen/game-view.fxml"));
            AnchorPane.setTopAnchor(gamePage, 10.0);
            AnchorPane.setBottomAnchor(gamePage, 10.0);
            AnchorPane.setLeftAnchor(gamePage, 10.0);
            AnchorPane.setRightAnchor(gamePage, 10.0);
            gamePane.getChildren().add(gamePage);

            Parent eventPage = FXMLLoader.load(getClass().getResource("../eventScreen/event-view.fxml"));
            AnchorPane.setTopAnchor(eventPage, 10.0);
            AnchorPane.setBottomAnchor(eventPage, 10.0);
            AnchorPane.setLeftAnchor(eventPage, 10.0);
            AnchorPane.setRightAnchor(eventPage, 10.0);
            eventPane.getChildren().add(eventPage);

            Parent blogPage = FXMLLoader.load(getClass().getResource("../blogScreen/blog-view.fxml"));
            AnchorPane.setTopAnchor(blogPage, 10.0);
            AnchorPane.setBottomAnchor(blogPage, 10.0);
            AnchorPane.setLeftAnchor(blogPage, 10.0);
            AnchorPane.setRightAnchor(blogPage, 10.0);
            blogPane.getChildren().add(blogPage);

            Parent messagePage = FXMLLoader.load(getClass().getResource("../messageScreen/msg-view.fxml"));
            AnchorPane.setTopAnchor(messagePage, 10.0);
            AnchorPane.setBottomAnchor(messagePage, 10.0);
            AnchorPane.setLeftAnchor(messagePage, 10.0);
            AnchorPane.setRightAnchor(messagePage, 10.0);
            messagePane.getChildren().add(messagePage);

            AnchorPane storePage = FXMLLoader.load(getClass().getResource("../storeScreen/store-view.fxml"));
            AnchorPane.setTopAnchor(storePage, 10.0);
            AnchorPane.setBottomAnchor(storePage, 10.0);
            AnchorPane.setLeftAnchor(storePage, 10.0);
            AnchorPane.setRightAnchor(storePage, 10.0);
            storePane.getChildren().setAll(storePage);


            Parent teamsPage = FXMLLoader.load(getClass().getResource("../teamsScreen/teams-view.fxml"));
            AnchorPane.setTopAnchor(teamsPage, 10.0);
            AnchorPane.setBottomAnchor(teamsPage, 10.0);
            AnchorPane.setLeftAnchor(teamsPage, 10.0);
            AnchorPane.setRightAnchor(teamsPage, 10.0);
            teamsPane.getChildren().add(teamsPage);


            homePane.toFront();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}


