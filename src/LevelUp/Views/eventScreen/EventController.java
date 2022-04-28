package LevelUp.Views.eventScreen;

import LevelUp.Services.ServiceEvenements;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;




public class EventController implements Initializable {

    @FXML
    private VBox eventList = null;
    @FXML
    private TextField searchText;
    
    ServiceEvenements se;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        se = new ServiceEvenements();
        
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshEvents(newValue);
        });


        refreshEvents("");

    }
    
    public void refreshEvents(String stxt)
    {
        eventList.getChildren().clear();
        (stxt==""?se.readEvenements():se.readEvenementsWithSearch(stxt)).forEach(evenement -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("event-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(evenement);
                eventList.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
            
}
