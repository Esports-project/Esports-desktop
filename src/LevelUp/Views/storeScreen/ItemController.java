package Esprit.Views.storeScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Esprit.Utils.MyListener;
import Esprit.Entities.Produit;

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
        priceLable.setText(produit.getPrice()+"$");
        nameLabel.setText(produit.getNom());
        String pat = produit.getImage();
        Image img6 = null;
        try {
            img6 = new Image(new FileInputStream(pat));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        img.setImage(img6);
        img.setStyle("-fx-background-radius: 15;");

    }

}