package Esprit;

import Esprit.Services.ServiceUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    FXMLLoader myLoader = null; Scene myScene = null; Stage prevStage = null;
    @Override
    public void start(Stage stage) throws IOException {
        try {
            myLoader = new FXMLLoader();
            myLoader = new FXMLLoader(HelloApplication.class.getResource("Views/loginScreen/hello-view.fxml"));
            Parent root = myLoader.load();
            ServiceUser login = myLoader.getController();
            login.setStage(stage);
            myScene = new Scene(root);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Hello!");
            stage.setScene(myScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}