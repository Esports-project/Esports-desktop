package LevelUp.Views.eventScreen;

import LevelUp.Entities.Evenement;
import LevelUp.Services.ServiceEvenements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class EventDashboardController implements Initializable {
    @FXML
    private VBox eventList;

    @FXML
    private TextField nom;

    @FXML
    private TextField organisateur;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button filepicker;

    private String imageFile;

    private Evenement ev;
    @FXML
    private StackedBarChart<String, Integer> statsEvents;
    @FXML
    private NumberAxis statsNumberAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceEvenements se = new ServiceEvenements();
        se.readEvenements().forEach(evenement -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("dashboard-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                DashboardItemController itemController = fxmlLoader.getController();
                itemController.setData(evenement);
                eventList.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
              statsEvents.setTitle("Evenements par mois"); 

        Map<String,Integer> theStats = se.readStatsPerMonth();
                XYChart.Series<String, Integer> series = new XYChart.Series<>(); 
        List<String> monthsList = new ArrayList<String>();
        for (Map.Entry<String,Integer> entry : theStats.entrySet())
        {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            monthsList.add(entry.getKey());
        }
        statsNumberAxis.setAutoRanging(false);
        statsNumberAxis.setTickUnit(2);
        statsNumberAxis.setLowerBound(0);
        statsNumberAxis.setUpperBound(12);
        ((CategoryAxis)statsEvents.getXAxis()).setCategories(FXCollections.<String>observableArrayList(monthsList));
        statsEvents.getData().addAll(series);
        
    }

    @FXML
    public void ajoutImage(ActionEvent e){
        FileChooser filechooser = new FileChooser();
        File f = filechooser.showOpenDialog(null);

        if(f != null){
            imageFile = f.getAbsolutePath();
        }
    }

    @FXML
    public void ajoutEvenement(ActionEvent e) throws ParseException {
        ServiceEvenements evenements = new ServiceEvenements();
        java.util.Date date =
                java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Evenement evenement = new Evenement(
                nom.getText(),
                organisateur.getText(),
                description.getText(),
                imageFile,
                sqlDate
        );
        evenements.createEvenement(evenement);
    }



}
