package Esprit.Views.eventScreen;

import Esprit.Entities.Evenement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemController {

    @FXML
    private Label title;

    @FXML
    private Label date;

    public void setData(Evenement evenement) {
        title.setText(evenement.getNom());
        date.setText(evenement.getDate().toString());
    }
}
