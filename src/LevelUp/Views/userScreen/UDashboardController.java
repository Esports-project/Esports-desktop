package Esprit.Views.userScreen;

import Esprit.Services.ServiceUser;
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

public class UDashboardController implements Initializable {

    @FXML
    public VBox userList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceUser sr = new ServiceUser();
        sr.userList().forEach(user -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("dashboard-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UItemController itemController = fxmlLoader.getController();
                itemController.setData(user);
                userList.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
