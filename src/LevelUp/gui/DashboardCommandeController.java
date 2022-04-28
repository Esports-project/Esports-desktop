package Esprit.gui;

import Esprit.Connection.mysqlconnect;
import Esprit.entities.Commande;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardCommandeController {

    @FXML
    private ImageView GotTocart;

    @FXML
    private Button PanierBoutton;

    @FXML
    private BorderPane Store;

    @FXML
    private Button accessProd;

    @FXML
    private Button accessProduits;

    @FXML
    private TableColumn<Commande, Date> colDate;

    @FXML
    private TableColumn<Commande, Integer> colId;

    @FXML
    private TableColumn<?, ?> colListeProds;

    @FXML
    private TableColumn<Commande, Integer> colNbrProd;

    @FXML
    private TableColumn<Commande, Float> colPrix;

    @FXML
    private TableColumn<Commande, Integer> colUser;

    @FXML
    private Button reload;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Commande> tabViewProd;

    ObservableList<Commande> listC;
    int index1 = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    void UpdateTable(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<Commande, Date>("date"));
        colNbrProd.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Commande,Float>("prix_total"));
        colUser.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("user_id"));

        listC = mysqlconnect.getDataCommandes();
        tabViewProd.setItems(listC);
    }

    @FXML
    void accessProd(ActionEvent event) throws IOException {
        rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("DashboardProduit.fxml")));
    }

    @FXML
    void accessProduits(ActionEvent event) throws IOException {
        rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("Store.fxml")));
    }

    @FXML
    void loadSecond(ActionEvent event) {

    }

}
