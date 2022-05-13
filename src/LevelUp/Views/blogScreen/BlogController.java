package Esprit.Views.blogScreen;

import Esprit.Services.ServiceBlog;
import Esprit.Services.ServiceEvenements;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BlogController implements Initializable {

    @FXML
    private VBox blogList;

    @FXML
    private AnchorPane rootPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceBlog se = new ServiceBlog();

        se.readBlogs().forEach(blog -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("blog-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(blog);
                blogList.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
