package Esprit.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Esprit.utils.MyListener;
import Esprit.entities.Produit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ItemController {

    private Produit produit;
    private MyListener myListener;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;


    @FXML
    void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }

    public void setData(Produit produit, MyListener myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getNom());
        String pat = "C:\\Users\\Rayen BOURGUIBA\\Desktop\\Last\\src\\LevelUp\\img\\" + produit.getImage();
        Image img6 = null;
        try {
            img6 = new Image(new FileInputStream(pat));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        img.setImage(img6);
        img.setStyle("-fx-background-radius: 15;");

        //Image image = new Image(getClass().getResourceAsStream(produit.getImage()));
        //img.setImage(image);
    }
}
