/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Jeux;
import Services.JeuxServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class JeuxfrontController implements Initializable {

    @FXML
    private Hyperlink cartButton;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg1;
    @FXML
    private Label pnamed;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JeuxServices serveJ = new JeuxServices();   
                  ObservableList<Jeux> list = FXCollections.observableArrayList();
        int column=0;
        int row=3;
        for(int i=0 ; i<serveJ.readJeux().size();i++){
            list.add(serveJ.readJeux().get(i));
            
            //  System.err.println("sfiudfsidufhsiudfhdsiufj");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("jeuxexp.fxml"));
            
            try {
                System.out.println("hhhh");
                AnchorPane anchorPane = fxmlLoader.load();
                
                
                
                JeuxexpController itemController = fxmlLoader.getController();
                itemController.test(list.get(i).getNom(),list.get(i).getId(),list.get(i).getDescription());
                
//          itemController.setData(11,"ghj","jnui","hhhnu","hhuhuh","huiu",5);
if(column == 2){
    column=0;
    row++;
}
//Node anchorPane;
grid.add(anchorPane, column++, row); //Child , column , row

//Set Item Grid Width
grid.setMinWidth(Region.USE_COMPUTED_SIZE);
grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//
////Set Item Grid Height
grid.setMinHeight(Region.USE_COMPUTED_SIZE);
grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
grid.setMaxHeight(Region.USE_PREF_SIZE);




GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(JeuxfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
 
    }    

    @FXML
    private void gotoStore(ActionEvent event) {
    }

    @FXML
    private void gotoLibrary(ActionEvent event) {
    }

    @FXML
    private void gotoAccount(ActionEvent event) {
    }

    @FXML
    private void showCart(ActionEvent event) {
    }

    @FXML
    private void showHelp(ActionEvent event) {
    }

    @FXML
    private void proccessLogout(ActionEvent event) {
    }
    
}
