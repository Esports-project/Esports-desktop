package Esprit.Views.storeScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.LigneCommande;
import Esprit.Entities.Produit;
import Esprit.Services.ServiceLigneCommande;
import Esprit.Services.ServiceProduit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class StoreDashboardController implements Initializable {
    @FXML
    private AnchorPane rootpane;

    public AnchorPane rootPageStore(){
        return rootpane;
    }

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
    private Button deleteBtn;

    @FXML
    private Button submit;

    @FXML
    private Button updateProd;

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

    @FXML
    private TableColumn<Produit, Integer> colId;

    @FXML
    private TableView<LigneCommande> lcTableview;

    @FXML
    private TableColumn lcColId;

    @FXML
    private TableColumn lcColIdp;

    @FXML
    private TableColumn lcColIdc;

    private String imageFile;

    @FXML
    private Button editer;

    ObservableList<Produit> listM;
    ObservableList<LigneCommande> listLc;


    public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
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

    public void updateTableLc(){
        lcColId.setCellValueFactory(new PropertyValueFactory("id"));
        lcColIdp.setCellValueFactory(new PropertyValueFactory("idProduit"));
        lcColIdc.setCellValueFactory(new PropertyValueFactory("idCommande"));
        listLc = MySqlConnect.getLigneCommande();
        lcTableview.setItems(listLc);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateProd.setVisible(false);
        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    price.setText(oldValue);
                }
            }
        });

        solde.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    solde.setText(oldValue);
                }
            }
        });

        quantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d")) {
                    quantity.setText(oldValue);
                }
            }
        });

        updateTable();
        updateTableLc();

        editer.setOnAction(e -> {
            Produit prod = tableView.getSelectionModel().getSelectedItem();
            updateProd.setVisible(true);
            submit.setVisible(false);
            ref.setText(prod.getReferance());
            quantity.setText(String.valueOf(prod.getQuantity()));
            productName.setText(prod.getNom());
            description.setText(prod.getDescription());
            price.setText(String.valueOf(prod.getPrice()));
            solde.setText(String.valueOf(prod.getSolde()));
        });

    }

    public void ajoutImage(ActionEvent e){
        FileChooser filechooser = new FileChooser();
        File f = filechooser.showOpenDialog(null);
        if(f != null){
            imageFile = f.getAbsolutePath();
        }
    }

    public  void deleteCommande(ActionEvent e){
        ServiceLigneCommande ligneCommande = new ServiceLigneCommande();
        LigneCommande lc = lcTableview.getSelectionModel().getSelectedItem();
        ligneCommande.deleteLigneCommande(lc);
        updateTableLc();
    }

    public  void deleteProduit(ActionEvent e){
        ServiceProduit serviceProduit = new ServiceProduit();
        Produit prod = tableView.getSelectionModel().getSelectedItem();
        serviceProduit.deleteProduit(prod);
        updateTable();
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

    @FXML
    public void updateProd(ActionEvent actionEvent) throws SQLException {
        ServiceProduit serviceProduit = new ServiceProduit();
        Produit prod = tableView.getSelectionModel().getSelectedItem();
        if (actionEvent.getSource() == updateProd) {
            boolean active = true;
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String nom = productName.getText();
            String des = description.getText();
            int quan = Integer.parseInt(quantity.getText());
            float prix = Float.parseFloat(price.getText());
            float discount = Float.parseFloat(solde.getText());
            String refe = ref.getText();
            prod.setQuantity(quan);
            prod.setNom(nom);
            prod.setPrice(prix);
            prod.setSolde(discount);
            prod.setReferance(refe);
            prod.setUpdatedAt(date);
            if(imageFile != null) prod.setImage(imageFile);
            prod.setActive(true);
            serviceProduit.editProduit(prod);
            tableView.setItems(listM);
            updateProd.setVisible(false);
            submit.setVisible(true);
            clearTexts();
            updateTable();
        }
    }

    void clearTexts(){
        ref.clear();
        quantity.clear();
        productName.clear();
        description.clear();
        price.clear();
        solde.clear();
    }
}
