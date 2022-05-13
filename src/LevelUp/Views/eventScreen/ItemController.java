package Esprit.Views.eventScreen;

import Esprit.Entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ItemController {

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private ImageView imageView;

    public void setData(Evenement evenement) {
        InputStream stream = null;
        title.setText(evenement.getNom());
        date.setText(evenement.getDate().toString());
        try {
            stream = new FileInputStream(evenement.getImage());
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void checkWeather(ActionEvent ev) {

        try {
            Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("WeatherView.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Weather");
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
