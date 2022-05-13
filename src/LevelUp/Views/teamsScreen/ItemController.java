package Esprit.Views.teamsScreen;

import Esprit.Entities.Equipes;
import Esprit.Services.ServiceGames;
import Esprit.Utils.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ItemController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label name;

    private Equipes equipes;

    public void setData(Equipes eq){
        name.setText(eq.getNom());
        equipes = eq;
    }




}
