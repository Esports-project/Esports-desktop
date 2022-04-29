/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Entities.Equipe;
import Services.EquipeServices;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author dorra
 */
public class EquipeController implements Initializable {

    @FXML
    private TextField Nomequipe;
    @FXML
    private TableView<Equipe> tableauEquipe;
    @FXML
    private TableColumn<Equipe, Integer> idtableau;
    @FXML
    private TableColumn<Equipe,String> nomtableau;
    @FXML
    private Button addEquip;
    @FXML
    private Button modifierEquipe;
    @FXML
    private Button supprimerEquipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EquipeServices serveE=new EquipeServices () ;
         idtableau.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
        nomtableau.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
        tableauEquipe.setItems(serveE.readEquipe());
        
        supprimerEquipe.setOnMouseClicked((MouseEvent event) -> {
                  //  stackedb.getData().clear();
            
            Equipe equipe = tableauEquipe.getSelectionModel().getSelectedItem();
            String supp ="Voulez-vous supprimer l'equipe ?" +Nomequipe.getText();
            
            int I =JOptionPane.showConfirmDialog(null,supp);
            if(I!=1)
            {
                serveE.DeleteEquipe(equipe.getId());

            }
            tableauEquipe.setItems(serveE.readEquipe());
            
            //FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
        });
    }    

    @FXML
    private void addEquipe(ActionEvent event) {
        EquipeServices serveE=new EquipeServices () ;
        
        String resID = Nomequipe.getText();
        Equipe E = new Equipe(resID);
        serveE.createEquipe(E);
        tableauEquipe.setItems(serveE.readEquipe());
    }

    @FXML
    private void modifier(ActionEvent event) {
        nomtableau.setCellFactory(TextFieldTableCell.forTableColumn());
        nomtableau.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        tableauEquipe.setEditable(true);
        
        modifierEquipe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                   //     stackedb.getData().clear();

                Equipe equipe = tableauEquipe.getSelectionModel().getSelectedItem();
             //   System.out.println(categorie.getId());
             EquipeServices serveE=new EquipeServices () ;
             serveE.modifierEquipe(equipe,equipe.getId());
             tableauEquipe.setItems(serveE.readEquipe());

            }
        });

        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
    }
    
}
