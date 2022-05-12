package Esprit.Views.teamsScreen;

import Esprit.Services.ServiceClassement;
import Esprit.Services.ServiceEquipes;
import Esprit.Views.rankingScreen.RankingItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController implements Initializable {

    @FXML
    private VBox teamsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceEquipes se = new ServiceEquipes();
        se.equipesList().forEach(equipe -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(equipe);
                teamsList.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
