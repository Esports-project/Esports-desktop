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
import java.util.Objects;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private StackPane pane;

    @FXML
    private Button reclamationBtn;

    @FXML
    private Button eventBtn;

    @FXML
    private Button teamsBtn;

    @FXML
    private Button rankingBtn;

    @FXML
    private Button msgBtn;

    @FXML
    private Button productBtn;

    @FXML
    private Button gameBtn;

    @FXML
    private Button blogBtn;





    @FXML
    private AnchorPane recPane;

    @FXML
    private AnchorPane eventPane;

    @FXML
    private AnchorPane rankingPane;

    @FXML
    private AnchorPane teamsPane;

    @FXML
    private AnchorPane msgPane;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private AnchorPane productPane;;

    @FXML
    private AnchorPane blogPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane reclamationpage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../reclamationScreen/reclamation-dashboard.fxml")));
            AnchorPane.setTopAnchor(reclamationpage, 10.0);
            AnchorPane.setBottomAnchor(reclamationpage, 10.0);
            AnchorPane.setLeftAnchor(reclamationpage, 10.0);
            AnchorPane.setRightAnchor(reclamationpage, 10.0);
            recPane.getChildren().setAll((AnchorPane)reclamationpage);

            AnchorPane eventpage = FXMLLoader.load(getClass().getResource("../eventScreen/event-dashboard.fxml"));
            AnchorPane.setTopAnchor(eventpage, 10.0);
            AnchorPane.setBottomAnchor(eventpage, 10.0);
            AnchorPane.setLeftAnchor(eventpage, 10.0);
            AnchorPane.setRightAnchor(eventpage, 10.0);
            eventPane.getChildren().setAll(eventpage);

            AnchorPane rankingpage = FXMLLoader.load(getClass().getResource("../rankingScreen/ranking-dashboard.fxml"));
            AnchorPane.setTopAnchor(rankingpage, 10.0);
            AnchorPane.setBottomAnchor(rankingpage, 10.0);
            AnchorPane.setLeftAnchor(rankingpage, 10.0);
            AnchorPane.setRightAnchor(rankingpage, 10.0);
            rankingPane.getChildren().setAll(rankingpage);

            AnchorPane blogpage = FXMLLoader.load(getClass().getResource("../blogScreen/blog-dashboard.fxml"));
            AnchorPane.setTopAnchor(blogpage, 10.0);
            AnchorPane.setBottomAnchor(blogpage, 10.0);
            AnchorPane.setLeftAnchor(blogpage, 10.0);
            AnchorPane.setRightAnchor(blogpage, 10.0);
            blogPane.getChildren().setAll(blogpage);

            AnchorPane teamspage = FXMLLoader.load(getClass().getResource("../teamsScreen/teams-dashboard.fxml"));
            AnchorPane.setTopAnchor(teamspage, 10.0);
            AnchorPane.setBottomAnchor(teamspage, 10.0);
            AnchorPane.setLeftAnchor(teamspage, 10.0);
            AnchorPane.setRightAnchor(teamspage, 10.0);
            teamsPane.getChildren().setAll(teamspage);

            AnchorPane msgpage = FXMLLoader.load(getClass().getResource("../messageScreen/msg-view-admin.fxml"));
            AnchorPane.setTopAnchor(msgpage, 10.0);
            AnchorPane.setBottomAnchor(msgpage, 10.0);
            AnchorPane.setLeftAnchor(msgpage, 10.0);
            AnchorPane.setRightAnchor(msgpage, 10.0);
            msgPane.getChildren().setAll(msgpage);

            AnchorPane prpage = FXMLLoader.load(getClass().getResource("../storeScreen/store-dashboard.fxml"));
            AnchorPane.setTopAnchor(prpage, 10.0);
            AnchorPane.setBottomAnchor(prpage, 10.0);
            AnchorPane.setLeftAnchor(prpage, 10.0);
            AnchorPane.setRightAnchor(prpage, 10.0);
            productPane.getChildren().setAll(prpage);

            AnchorPane gamepage = FXMLLoader.load(getClass().getResource("../gameScreen/game-dashboard.fxml"));
            AnchorPane.setTopAnchor(gamepage, 10.0);
            AnchorPane.setBottomAnchor(gamepage, 10.0);
            AnchorPane.setLeftAnchor(gamepage, 10.0);
            AnchorPane.setRightAnchor(gamepage, 10.0);
            gamePane.getChildren().setAll(gamepage);

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
        if(e.getSource() == teamsBtn){
            teamsPane.toFront();
        }
        if(e.getSource() == msgBtn){
            msgPane.toFront();
        }
        if(e.getSource() == productBtn){
            productPane.toFront();
        }
        if(e.getSource() == gameBtn){
            gamePane.toFront();
        }
        if(e.getSource() == blogBtn){
            blogPane.toFront();
        }
    }
}
