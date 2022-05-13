package Esprit.Views.storeScreen;

import Esprit.Entities.CartEntry;
import Esprit.Entities.Produit;
import Esprit.Entities.ShoppingCart;
import Esprit.Services.ServiceCommande;
import Esprit.Utils.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;
import Esprit.Views.storeScreen.*;

public class CartController {

    private MyListener myListener;
    private CartEntry cartEntry;

    @FXML
    private Button PasserCommande;

    @FXML
    private Button StoreButton;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private Label quantityProd;

    @FXML
    private AnchorPane rootPannel;

    @FXML
    private ScrollPane scrollCart;

    @FXML
    private void loadSecond(ActionEvent actionEvent) throws IOException {
        AnchorPane storePage = FXMLLoader.load(getClass().getResource("store-view.fxml"));
        AnchorPane.setTopAnchor(storePage, 10.0);
        AnchorPane.setBottomAnchor(storePage, 10.0);
        AnchorPane.setLeftAnchor(storePage, 10.0);
        AnchorPane.setRightAnchor(storePage, 10.0);
        rootPannel.getChildren().setAll(storePage);
    }

    private void setChosenFruit(ShoppingCart cart) {
        fruitNameLable.setText(String.valueOf(cart.calculateTotal()));
    }


    @FXML
    public void initialize() {
        try {
            List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
            if (entries.size() > 0) {
                setChosenFruit(ShoppingCart.getInstance());
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Produit produit) {
                        setChosenFruit(ShoppingCart.getInstance());
                    }
                };
            }

            grid.getChildren().clear();
            int column = 0;
            int row = 1;
            if (entries.isEmpty()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("emptyCart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EmptyCartController itemController = fxmlLoader.getController();
                grid.add(anchorPane, 1, 1);
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } else {
                int i=0;
                for (CartEntry cartEntry:entries) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("cartItem.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    CartItemController itemController = fxmlLoader.getController();
                    itemController.setData(entries.get(i),myListener);

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
                    i++;
                }
            }} catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void passerCommande(ActionEvent event) throws IOException {
        ServiceCommande serviceCommande = new ServiceCommande();
        serviceCommande.addCommande();

        AnchorPane storePage = FXMLLoader.load(getClass().getResource("store-view.fxml"));
        AnchorPane.setTopAnchor(storePage, 10.0);
        AnchorPane.setBottomAnchor(storePage, 10.0);
        AnchorPane.setLeftAnchor(storePage, 10.0);
        AnchorPane.setRightAnchor(storePage, 10.0);
        rootPannel.getChildren().setAll(storePage);
    }
}