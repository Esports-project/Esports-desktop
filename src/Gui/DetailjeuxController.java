/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Jeux;
import Services.JeuxServices;
import Services.reviewServices;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class DetailjeuxController implements Initializable {

    @FXML
    private Label descriptiondetail;
    @FXML
    private Label nomdetail;
    @FXML
    private Label iddetailjeux;
    @FXML
    private TextField avisdetail;
    @FXML
    private Button submitdetail;
    @FXML
    private ListView<String> listreview;
    @FXML
    private ImageView imagedetail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iddetailjeux.setVisible(false);
        Jeux jj = null;
        JeuxServices p1 = new JeuxServices();
   ObservableList<Jeux> comdata = FXCollections.observableArrayList();
       // comdata.addAll(p1.afficher());
        int tet=(jj.getTest());
        System.err.println(tet);
        try {
          jj=  p1.getById(tet);
        } catch (SQLException ex) {
            Logger.getLogger(DetailjeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        descriptiondetail.setText(jj.getNom());
       // idproducttest.fontProperty();
                iddetailjeux.setText(String.valueOf(jj.getId()));
        descriptiondetail.setText(jj.getDescription());
        File file1 = new File("C:/Users/dorra/Documents/NetBeansProjects/PidevDorra/src/image/valorant.jpg");
                
        System.out.println(file1);
        Image img1=new Image(file1.toURI().toString());
        imagedetail.setImage(img1);
        reviewServices rev = new reviewServices();
        ObservableList<String> list = FXCollections.observableArrayList();
       
                             int pid=Integer.parseInt(iddetailjeux.getText());
                             System.out.println(pid);
               

        try {
            for(int i=0;i<rev.getreviews(pid).size();i++)
            {
                list.add(rev.getreviews(pid).get(i).getAvis());
            }
                                       
            System.out.println(list);
        } catch (SQLException ex) {
            Logger.getLogger(DetailjeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
            listreview.setItems(list);


    }    
    
}
