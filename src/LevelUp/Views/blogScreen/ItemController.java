package Esprit.Views.blogScreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label description;

    @FXML
    private Label date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
