package Esprit.Views.storeScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Produit;
import Esprit.Services.ServiceProduit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.ResourceBundle;

public class StoreDashboardController implements Initializable {
    @FXML
    private AnchorPane rootpane;

    @FXML
    private TextField productName;

    @FXML
    private TextField quantity;

    @FXML
    private TextField price;

    @FXML
    private TextArea description;

    @FXML
    private  TextField solde;

    @FXML
    private  TextField ref;

    @FXML
    private Button imageBtn;

    @FXML
    private Button submit;

    @FXML
    private TableView<Produit> tableView;

    @FXML
    private TableColumn<Produit, String> colActive;

    @FXML
    private TableColumn<Produit, String> colImage;

    @FXML
    private TableColumn<Produit, String> colNom;

    @FXML
    private TableColumn<Produit, Float> colPrice;

    @FXML
    private TableColumn<Produit, Integer> colQuantity;

    @FXML
    private TableColumn<Produit, String> colRef;

    @FXML
    private TableColumn<Produit, Float> comSolde;

    private String imageFile;

    ObservableList<Produit> listM;
    int index1 = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void updateTable() {

        colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        colActive.setCellValueFactory(new PropertyValueFactory<Produit, String>("active"));
        colImage.setCellValueFactory(new PropertyValueFactory<Produit, String>("image"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Produit, Float>("price"));
        colRef.setCellValueFactory(new PropertyValueFactory<Produit, String>("referance"));
        comSolde.setCellValueFactory(new PropertyValueFactory<Produit, Float>("solde"));
        listM = MySqlConnect.getDataProduit();
        tableView.setItems(listM);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }

    public void ajoutImage(ActionEvent e){
        FileChooser filechooser = new FileChooser();
        File f = filechooser.showOpenDialog(null);

        if(f != null){
            imageFile = f.getAbsolutePath();
        }
    }

    public void ajoutProduit(ActionEvent e){
        ServiceProduit sr = new ServiceProduit();
        boolean active = true;
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Produit pr = new Produit( productName.getText(),
                Integer.parseInt(quantity.getText()),
                Integer.parseInt(price.getText()),
                description.getText(),
                imageFile,
                Integer.parseInt(solde.getText()),
                active,
                ref.getText(),
                date);
        sr.addProduit(pr);
        tableView.setItems(listM);
        updateTable();
    }
}
