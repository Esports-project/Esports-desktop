package Esprit.Views.blogScreen;

import Esprit.Entities.Blog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Label title;

    @FXML
    private Label description;

    @FXML
    private Label date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Blog b){
        InputStream stream = null;
        title.setText(b.getTitre());
        description.setText(b.getContenu());
        date.setText(b.getPost_date().toString());
        try {
            stream = new FileInputStream(b.getImage());
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
