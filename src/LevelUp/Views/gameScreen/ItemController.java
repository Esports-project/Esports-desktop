package Esprit.Views.gameScreen;


import Esprit.Entities.Games;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;


public class ItemController {



    @FXML
    private Label title;

    @FXML
    private Label description;;

    @FXML
    private Rating rating;

    public void setData(Games games) {
        title.setText(games.getNom());
        description.setText(games.getDescription());
    }
}
