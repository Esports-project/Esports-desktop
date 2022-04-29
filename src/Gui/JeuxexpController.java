/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Jeux;
import Services.JeuxServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class JeuxexpController implements Initializable {

    @FXML
    private ImageView imageproduitfront;
    @FXML
    private Label nomproduitfront;
    @FXML
    private Label marqueproduitfront;
    @FXML
    private Label prixproduitfront;
    @FXML
    private Label descriptionproduitfront;
    @FXML
    private Button more;
    @FXML
    private Label idppp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // idppp.setVisible(false);
    }    
    
    public void test( String nom , int id  , String type  )
    {
        
         JeuxServices serveJ = new JeuxServices();   
                  
          
                nomproduitfront.setText(nom);
                
                descriptionproduitfront.setText(type);
                idppp.setText(String.valueOf(id));
                File file1 = new File("C:/Users/dorra/Documents/NetBeansProjects/PidevDorra/src/image/valorant.jpg");
                
        System.out.println(file1);
        Image img1=new Image(file1.toURI().toString());
        imageproduitfront.setImage(img1);
                
            
           }



    @FXML
    private void detail(ActionEvent event) {
        JeuxServices serveJ = new JeuxServices();   
        Jeux J = new Jeux() ;
                 J.setTest(Integer.parseInt(idppp.getText()));
                 try {
              Parent root = FXMLLoader.load(getClass().getResource("detailjeux.fxml"));
          Stage stage = new Stage();
     
          stage.setTitle("commentaire");
                    stage.setScene(new Scene(root)); 
                    stage.show();
     
        } catch (IOException ex) {
               System.out.println("can't load comrnt window");
        }
    }
    
}
