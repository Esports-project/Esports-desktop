package Esprit.Views.rankingScreen;

import Esprit.Services.ServiceClassement;
import Esprit.Services.ServiceReclamation;
import Esprit.Views.reclamationScreen.RItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RankingDashboardController implements Initializable {

    @FXML
    private TextField rang;

    @FXML
    private ComboBox equipe;

    @FXML
    private ComboBox evenement;

    @FXML
    private VBox classmentList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceClassement sr = new ServiceClassement();
        sr.readClassements().forEach(classement -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ranking-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                RankingItemController itemController = fxmlLoader.getController();
                itemController.setData(classement);
                classmentList.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
