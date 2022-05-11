/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LevelUp.Views.eventScreen;

import LevelUp.Services.ServiceEvenements;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class WeatherViewController implements Initializable {

    @FXML
    private Label humiditytxt;
    @FXML
    private Label pressuretxt;
    @FXML
    private Label desctxt;
    @FXML
    private Label temptxt;
    @FXML
    private ImageView iconimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            String[] wdata = new ServiceEvenements().getWeatherData();
            temptxt.setText(wdata[0]);
            humiditytxt.setText(wdata[1]);
            desctxt.setText(wdata[2]);
            pressuretxt.setText(wdata[3]);
            System.out.println("image link = http://openweathermap.org/img/wn/"+wdata[5]+"@2x.png");
            iconimg.setImage(new Image("http://openweathermap.org/img/wn/"+wdata[5]+"@2x.png"));
        }catch(Exception e)
        {
            
        }
    }    
    
}
