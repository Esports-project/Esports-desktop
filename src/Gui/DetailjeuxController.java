/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Jeux;
import Entities.review;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

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
    private ListView<String> listreview;
    @FXML
    private ImageView imagedetail;
Jeux jjj= new Jeux();
    @FXML
    private TextField avisjeux;
    @FXML
    private Button submitrating;
    @FXML
    private Rating ratingjeux;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iddetailjeux.setVisible(false);
        Jeux j=null;
        JeuxServices jj = new JeuxServices();
        ObservableList<Jeux> ll = FXCollections.observableArrayList();
        int tet=(jjj.getTest());

        System.out.println(tet);
        try {
            j=jj.getById(tet);

                    System.out.println(j);

        } catch (SQLException ex) {
            Logger.getLogger(DetailjeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        descriptiondetail.setText(j.getDescription());
        nomdetail.setText(j.getNom());
        iddetailjeux.setText(String.valueOf(j.getId()));
         File file1 = new File("C:/Users/dorra/Documents/NetBeansProjects/PidevDorra/src/image/valorant.jpg");
                
        System.out.println(file1);
        Image img1=new Image(file1.toURI().toString());
        imagedetail.setImage(img1);
        int iiddr = Integer.parseInt(iddetailjeux.getText());
        reviewServices rev = new reviewServices();
                     ObservableList<String> list = FXCollections.observableArrayList();

        try {
            for(int i=0;i<rev.getreviews(iiddr).size();i++)
            {
                list.add(rev.getreviews(iiddr).get(i).getAvis());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailjeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listreview.setItems(list);
    }    

  

    @FXML
    private void submrate(ActionEvent event) throws SQLException {
        //start notif
         Notifications notificationBuilder=Notifications.create()
             .title("avis enregistre mercii")
             .graphic(null)
             .hideAfter(Duration.seconds(5))
             .position(Pos.TOP_RIGHT);
             notificationBuilder.showConfirm();
             // end notif 
                     ObservableList<String> list = FXCollections.observableArrayList();

             
        reviewServices rev = new reviewServices();
        double x = ratingjeux.getRating();
        String idr = iddetailjeux.getText();
        System.out.println(x);

        int iiddr = Integer.parseInt(iddetailjeux.getText());
        review r1= new review(iiddr,x*2,avisjeux.getText());
       rev.createReview(r1);
        for(int i=0;i<rev.getreviews(iiddr).size();i++)
            {
                list.add(rev.getreviews(iiddr).get(i).getAvis());
            }
            listreview.setItems(list);

    }
}
