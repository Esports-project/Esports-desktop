package Esprit.Views.reclamationScreen;

import Esprit.Views.eventScreen.ItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Esprit.Services.ServiceReclamation;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RDashboardController implements Initializable {

    @FXML
    public VBox recList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceReclamation sr = new ServiceReclamation();
        sr.reclamationList().forEach(reclamation -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("dashboard-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                RItemController itemController = fxmlLoader.getController();
                itemController.setData(reclamation);
                recList.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
