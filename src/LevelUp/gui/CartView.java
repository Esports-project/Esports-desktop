package Esprit.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CartView {
    private Parent view;
    public CartView() throws IOException {
        URL uiFile= new File("cart.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(uiFile);
    }
    public Parent getView(){
        return this.view;
    }
}
