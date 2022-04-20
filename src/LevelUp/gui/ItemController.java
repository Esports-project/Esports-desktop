package Esprit.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Esprit.utils.MyListener;
import Esprit.entities.Produit;

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
        priceLable.setText("TND " + produit.getPrice());
        //Image image = new Image(getClass().getResourceAsStream(produit.getImage()));
        //img.setImage(image);
    }
}
