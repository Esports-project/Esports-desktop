package Esprit.gui;

import Esprit.Connection.mysqlconnect;
import Esprit.entities.Produit;
import Esprit.services.ServiceCommande;
import Esprit.services.ServiceProduit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardProduitController {

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

    public void showProds(){
        /*ServiceProduit serviceProduit = new ServiceProduit();
        ObservableList<Produit> list = serviceProduit.produitList();
        colNom.setCellFactory(new PropertyValueFactory<Produit, String>();
*/
    }

    public void AddProd(ActionEvent actionEvent){
        ServiceProduit sr = new ServiceProduit();
        boolean active=true;
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

    public static boolean isNumber(String s)
    {

        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.

        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(s);

        // Returning boolean value
        return (m.matches());
    }

    public static boolean isFloat(String s){
        if (s.matches("[-+]?[0-9]*\\.?[0-9]+")) { // You can use the `\\d` instead of `0-9` too!
            return true;
        }else{return  false;}
    }


    //affichage bl tall
    ObservableList<Produit> listM;
    int index1 = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void UpdateTable(){
        colNom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        colActive.setCellValueFactory(new PropertyValueFactory<Produit,String>("active"));
        colImage.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Produit,Float>("price"));
        colRef.setCellValueFactory(new PropertyValueFactory<Produit,String>("referance"));
        comSolde.setCellValueFactory(new PropertyValueFactory<Produit,Float>("solde"));

        listM = mysqlconnect.getDatausers();
        tabViewProd.setItems(listM);
    }

    @FXML
    public void editProd(){
        ServiceProduit serviceProduit = new ServiceProduit();
        Produit prod = tabViewProd.getSelectionModel().getSelectedItem();
        serviceProduit.deleteProduit(prod);
    }




}
