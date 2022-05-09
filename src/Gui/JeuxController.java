/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Equipe;
import Entities.Jeux;
import Services.EquipeServices;
import Services.JeuxServices;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class JeuxController implements Initializable {

    @FXML
    private AnchorPane tableauJeux;
    @FXML
    private TextField nomjeux;
    @FXML
    private TextField descriptionjeux;
    @FXML
    private Button addJeu;
    @FXML
    private Button modifierjeux;
    @FXML
    private Button supprimerjeux;
    @FXML
    private TableColumn<Jeux, Integer> idtableau;
    @FXML
    private TableColumn<Jeux,String > nomtableau;
    @FXML
    private TableColumn<Jeux,String> descriptableau;
    @FXML
    private TableView<Jeux> testtableau;
    @FXML
    private TableColumn<Jeux, Float> rate;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JeuxServices serveJ=new JeuxServices () ;
        idtableau.setCellValueFactory(new PropertyValueFactory<Jeux, Integer>("id"));
        nomtableau.setCellValueFactory(new PropertyValueFactory<Jeux, String>("nom"));
        descriptableau.setCellValueFactory(new PropertyValueFactory<Jeux, String>("description"));
                rate.setCellValueFactory(new PropertyValueFactory<Jeux, Float>("m"));

        testtableau.setItems(serveJ.readJeux());
        
        supprimerjeux.setOnMouseClicked((MouseEvent event) -> {
                  //  stackedb.getData().clear();
            
        Jeux jeu = testtableau.getSelectionModel().getSelectedItem();
            String supp ="Voulez-vous supprimer un jeu ?" +nomjeux.getText();
            
            int I =JOptionPane.showConfirmDialog(null,supp);
            if(I!=1)
            {
                serveJ.DeleteJeux(jeu.getId());

            }
            testtableau.setItems(serveJ.readJeux());
            
            //FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
        });
        
        JeuxServices serv = new JeuxServices();
            List<String> a;
     
        try {
            a = serv.getCat();
        } catch (SQLException ex) {
            Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            TextFields.bindAutoCompletion(recherche, serv.getCat());
        } catch (SQLException ex) {
            Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }

         
          //  nom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom"));

       
            testtableau.setItems(serv.readJeux());
       

            FilteredList<Jeux> filteredData;
       
            filteredData = new FilteredList<>(serv.readJeux(), b -> true);
       

            recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
                filteredData.setPredicate(Categorie -> {
                   
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    String searchkeyword = newValue.toLowerCase();
                    if (Categorie.getNom().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;        // Means we found a match in ProductName
                    } else {
                        return false; // no match found
                    }
                });
            });
           
            SortedList<Jeux> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(testtableau.comparatorProperty());
            testtableau.setItems(sortedData);
    }    

      

    @FXML
    private void addJeux(ActionEvent event) {
        JeuxServices serveJ=new JeuxServices () ;
        String s= descriptionjeux.getText();
        String resID = nomjeux.getText();
        Jeux J = new Jeux(resID,s);
        serveJ.createJeux(J);
        testtableau.setItems(serveJ.readJeux());
    }

    @FXML
    private void modifier(ActionEvent event) {
        nomtableau.setCellFactory(TextFieldTableCell.forTableColumn());
        nomtableau.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
         
        });
        descriptableau.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptableau.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue());
        });
        testtableau.setEditable(true);
        
        modifierjeux.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                   //     stackedb.getData().clear();

                Jeux jeu = testtableau.getSelectionModel().getSelectedItem();
             //   System.out.println(categorie.getId());
             JeuxServices serveJ=new JeuxServices () ;
             serveJ.modifyJeux(jeu,jeu.getId());
             testtableau.setItems(serveJ.readJeux());
      }
        });

    
    }
    @FXML
    private void supprimer(ActionEvent event) {
        
        
    
    }
    
    
}

