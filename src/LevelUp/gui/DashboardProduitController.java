package Esprit.gui;

import Esprit.Connection.mysqlconnect;
import Esprit.entities.Produit;
import Esprit.services.ServiceCommande;
import Esprit.services.ServiceProduit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardProduitController implements Initializable {

    @FXML
    private ImageView GotTocart;

    @FXML
    private Button accessProd;

    @FXML
    private Button reload;

    @FXML
    private Button PanierBoutton;

    @FXML
    private BorderPane Store;

    @FXML
    private Button accessCommande;

    @FXML
    private TableView<Produit> tabViewProd;

    @FXML
    private Button addProduitButton;

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
    private Button editer;

    @FXML
    private Button deleteProduit;

    @FXML
    private Button editProduit;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField testSolde;

    @FXML
    private TextField tetPrice;

    @FXML
    private RadioButton textActive;

    @FXML
    private TextArea textDesc;

    @FXML
    private TextField textImg;

    @FXML
    private RadioButton textInactive;

    @FXML
    private TextField textNom;

    @FXML
    private TextField textQuantity;

    @FXML
    private TextField textRef;

    private Produit prod;

    private int index;


    @FXML
    void loadSecond(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editer.setVisible(false);
        UpdateTable();
        editProduit.setOnAction(e -> {
            Produit prod = tabViewProd.getSelectionModel().getSelectedItem();
            editer.setVisible(true);
            addProduitButton.setVisible(false);
            textRef.setText(prod.getReferance());
            textImg.setText(prod.getImage());
            textQuantity.setText(String.valueOf(prod.getQuantity()));
            textNom.setText(prod.getNom());
            textDesc.setText(prod.getDescription());
            tetPrice.setText(String.valueOf(prod.getPrice()));
            testSolde.setText(String.valueOf(prod.getSolde()));
            textActive.setSelected(true);
        });
    }

    public void deleteProd(ActionEvent e) {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from produit where referance = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, textRef.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void AddProd(ActionEvent actionEvent) {
        ServiceProduit sr = new ServiceProduit();
        boolean active = true;
        if (actionEvent.getSource() == addProduitButton) {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String nom = textNom.getText();
            String des = textDesc.getText();
            int quantity = Integer.parseInt(textQuantity.getText());
            float price = Float.parseFloat(tetPrice.getText());
            String img = textImg.getText();
            float solde = Float.parseFloat(testSolde.getText());
            String ref = textRef.getText();
            //if (!(textNom.getText().trim().isEmpty()) && !(textDesc.getText().trim().isEmpty()) && !(textQuantity.getText().trim().isEmpty()) && !(tetPrice.getText().trim().isEmpty()) && !(textImg.getText().trim().isEmpty()) && !(testSolde.getText().trim().isEmpty()) && !(textRef.getText().trim().isEmpty()) && isFullname(nom) && isNumber(String.valueOf(quantity)) && isFullname(des) && isFloat(String.valueOf(price)) && isFloat(String.valueOf(solde)) && solde >0 && solde <99 && price >0){
            sr.addProduit(new Produit(
                    nom,
                    quantity,
                    price,
                    des,
                    img,
                    solde,
                    active,
                    ref,
                    date)
            );
            tabViewProd.setItems(listM);
            UpdateTable();
        /*}else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert");
                alert.setHeaderText("Veiller entrer correctement les coordonnées");

                VBox dialogPaneContent = new VBox();
                // Set content for Dialog Pane
                alert.getDialogPane().setContent(dialogPaneContent);

                alert.showAndWait();

            }*/

        }
    }

    @FXML
    void accessProd(ActionEvent event) throws IOException {
        rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("Store.fxml")));
    }

    public static boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }

    public static boolean isNumber(String s) {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }

    public static boolean isFloat(String s) {
        if (s.matches("[-+]?[0-9]*\\.?[0-9]+"))
        {
            return true;
        } else {
            return false;
        }
    }

    ObservableList<Produit> listM;
    int index1 = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void UpdateTable() {
        colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        colActive.setCellValueFactory(new PropertyValueFactory<Produit, String>("active"));
        colImage.setCellValueFactory(new PropertyValueFactory<Produit, String>("image"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Produit, Float>("price"));
        colRef.setCellValueFactory(new PropertyValueFactory<Produit, String>("referance"));
        comSolde.setCellValueFactory(new PropertyValueFactory<Produit, Float>("solde"));
        listM = mysqlconnect.getDatausers();
        tabViewProd.setItems(listM);
    }

    @FXML
    public void editProd() {
        addProduitButton.setVisible(false);
    }

    @FXML
    public void editer(ActionEvent actionEvent) throws SQLException {
        ServiceProduit serviceProduit = new ServiceProduit();
        Produit prod = tabViewProd.getSelectionModel().getSelectedItem();
        if (actionEvent.getSource() == editer) {
            boolean active = true;
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String nom = textNom.getText();
            String des = textDesc.getText();
            int quantity = Integer.parseInt(textQuantity.getText());
            float price = Float.parseFloat(tetPrice.getText());
            String img = textImg.getText();
            float solde = Float.parseFloat(testSolde.getText());
            String ref = textRef.getText();
            //if (!(textNom.getText().trim().isEmpty()) && !(textDesc.getText().trim().isEmpty()) && !(textQuantity.getText().trim().isEmpty()) && !(tetPrice.getText().trim().isEmpty()) && !(textImg.getText().trim().isEmpty()) && !(testSolde.getText().trim().isEmpty()) && !(textRef.getText().trim().isEmpty()) && isFullname(nom) && isNumber(String.valueOf(quantity)) && isFullname(des) && isFloat(String.valueOf(price)) && isFloat(String.valueOf(solde)) && solde >0 && solde <99 && price >0){
            prod.setQuantity(quantity);
            prod.setNom(nom);
            prod.setPrice(price);
            prod.setImage(img);
            prod.setSolde(solde);
            prod.setReferance(ref);
            prod.setUpdatedAt(date);
            prod.setActive(true);
            serviceProduit.editProduit(prod);
            tabViewProd.setItems(listM);
            editer.setVisible(false);
            addProduitButton.setVisible(true);
            textRef.clear();
            textImg.clear();
            textQuantity.clear();
            textNom.clear();
            textDesc.clear();
            tetPrice.clear();
            testSolde.clear();
            textActive.setSelected(false);
            UpdateTable();
        }
    }
    @FXML
    void accessCommande(ActionEvent event) throws IOException {
        rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("DashboardCommande.fxml")));
    }

    @FXML
    public void deleteProd() {
        ServiceProduit serviceProduit = new ServiceProduit();
        Produit prod = tabViewProd.getSelectionModel().getSelectedItem();
        serviceProduit.deleteProduit(prod);
        UpdateTable();
    }

    @FXML
    public void fasa5(){
        ServiceProduit serviceProduit = new ServiceProduit();
        Produit prod = tabViewProd.getSelectionModel().getSelectedItem();
        serviceProduit.deleteProduit(prod);
        UpdateTable();
    }
}
