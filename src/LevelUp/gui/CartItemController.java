package Esprit.gui;

import Esprit.entities.CartEntry;
import Esprit.entities.Produit;
import Esprit.entities.ShoppingCart;
import Esprit.utils.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CartItemController {

    private CartEntry cart;
    private MyListener myListener;


    @FXML
    private Button ButtonMinusCart;

    @FXML
    private Button ButtonPlusCart;

    @FXML
    private ImageView imgCart;

    @FXML
    private Label nameLabelCart;

    @FXML
    private Label priceItemCart;

    @FXML
    private Label priceLableCart;

    @FXML
    private Label quantityItemPrice;

    @FXML
    private Button removeItemCart;

    @FXML
    void click(MouseEvent event) {

    }

    @FXML
    void removeItemCart(ActionEvent event) {

    }

    public void setData(CartEntry cart, MyListener myListener) {
        this.cart = cart;
        this.myListener = myListener;
        nameLabelCart.setText(cart.getProduit().getNom());
        priceLableCart.setText(cart.getProduit().getPrice()+"$");
        String pat = "C:\\Users\\Rayen BOURGUIBA\\Desktop\\Esports-desktop-Rayen\\src\\LevelUp\\img\\" + cart.getProduit().getImage();
        Image img6 = null;
        try {
            img6 = new Image(new FileInputStream(pat));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        imgCart.setImage(img6);
        imgCart.setStyle("-fx-background-radius: 15;");
        quantityItemPrice.setText(String.valueOf(cart.getQuantity()));
        priceItemCart.setText(String.valueOf(cart.getQuantity()*cart.getProduit().getPrice()));
        ButtonMinusCart.setUserData(cart.getProduit());
        ButtonMinusCart.setOnAction(e->{
            if (cart.getProduit().getQuantity()>0 && cart.getQuantity()<=cart.getProduit().getQuantity() && cart.getQuantity()>1){
                int num = cart.getQuantity() - 1;
            cart.setQuantity(num);
            quantityItemPrice.setText(String.valueOf(cart.getQuantity()));
            priceItemCart.setText(String.valueOf(cart.getQuantity()*cart.getProduit().getPrice()));}
        });
        ButtonPlusCart.setUserData(cart.getProduit());
        ButtonPlusCart.setOnAction(e->{
            if (cart.getProduit().getQuantity()>0 && cart.getQuantity()<cart.getProduit().getQuantity() && cart.getQuantity()>=1){
            int num = cart.getQuantity() + 1;
            cart.setQuantity(num);
            quantityItemPrice.setText(String.valueOf(cart.getQuantity()));
            priceItemCart.setText(String.valueOf(cart.getQuantity()*cart.getProduit().getPrice()));
            }
        });

    }
}
