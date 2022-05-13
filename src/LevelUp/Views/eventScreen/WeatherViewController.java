
package Esprit.Views.eventScreen;

import Esprit.Services.ServiceEvenements;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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