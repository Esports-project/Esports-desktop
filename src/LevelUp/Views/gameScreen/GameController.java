package Esprit.Views.gameScreen;

import Esprit.Services.ServiceGames;
import Esprit.Services.ServiceReview;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private VBox gameList;

    @FXML
    private AnchorPane rootPane;

    // This function runs on startup
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceGames sg = new ServiceGames();
        // Generates item card for each game
        sg.gameList().forEach(games -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(games);
                gameList.getChildren().add(anchorPane);

            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
