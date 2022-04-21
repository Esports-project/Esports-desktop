package Esprit.gui;

import Esprit.entities.Produit;
import Esprit.services.ServiceProduit;
import Esprit.entities.ShoppingCart;
import Esprit.utils.MyListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StoreController implements Initializable{

    @FXML
    private Button addToCart;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private ImageView fruitImg;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private Label quantityProd;

    @FXML
    private ScrollPane scroll;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView GotTocart;

    private List<Produit> produits = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    ServiceProduit serviceProduit = new ServiceProduit();

    private List<Produit> getData() {
        List<Produit> produits = new ArrayList<>();
        produits= serviceProduit.produitList();
        return produits;
    }

    private void setChosenFruit(Produit produit) {
        fruitNameLable.setText(produit.getNom());
        fruitPriceLabel.setText("TND " + produit.getPrice());
        quantityProd.setText(String.valueOf(produit.getQuantity()));
        String pat = "C:\\Users\\Rayen BOURGUIBA\\Desktop\\Last\\src\\LevelUp\\img\\" + produit.getImage();
        Image img6 = null;
        try {
            img6 = new Image(new FileInputStream(pat));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        fruitImg.setImage(img6);
        fruitImg.setStyle("-fx-background-radius: 15;");
        chosenFruitCard.setStyle("-fx-background-radius: 30;");
        Button addToCart = new Button("Add To Cart");
        addToCart.setUserData(produit.getReferance());
        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent  actionEvent) {
                Node sourceComponent = (Node)actionEvent.getSource();
                String productRef = (String)sourceComponent.getUserData();
                ShoppingCart shoppingCart = ShoppingCart.getInstance();
                shoppingCart.addProduct(productRef);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produits.addAll(getData());
        if (produits.size() > 0) {
            setChosenFruit(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenFruit(produit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadSecond(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("cart.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
