package LevelUp.Views.rankingScreen;

import LevelUp.Entities.Classement;
import LevelUp.Entities.Evenement;
import LevelUp.Services.ServiceClassement;
import LevelUp.Services.ServiceEvenements;
import LevelUp.Services.ServiceReclamation;
import LevelUp.Views.eventScreen.DashboardItemController;
import LevelUp.Views.reclamationScreen.RItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;



public class RankingDashboardController implements Initializable {

    @FXML
    private TextField rang;

    @FXML
    private ComboBox equipe;

    @FXML
    private ComboBox evenement;

    @FXML
    private VBox classmentList;
    
    public static RankingDashboardController _instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _instance = this;
        ObservableList<EquipeCombo> equipes = FXCollections.observableArrayList();
        ObservableList<Evenement> events = FXCollections.observableArrayList();
        
        ServiceClassement se = new ServiceClassement();
        
        se.readEquipeCombos().forEach(p-> {
            equipes.add(new EquipeCombo(p.getKey(), p.getValue()));
        });
        
        new ServiceEvenements().readEvenements().forEach(e-> {
            events.add(e);
        });
        
        equipe.setItems(equipes);
        evenement.setItems(events);
        
        evenement.setConverter(new StringConverter<Evenement>() {

            @Override
            public String toString(Evenement object) {
                return object.getNom();
            }

            @Override
            public Evenement fromString(String string) {
                return (Evenement) evenement.getItems().stream().filter(ap -> 
                    ((Evenement)ap).getNom().equals(string)).findFirst().orElse(null);
            }
        });
        
        refreshClassements();
    }
    
    public void refreshClassements()
    {
        classmentList.getChildren().clear();
        ServiceClassement se = new ServiceClassement();
        se.readClassements().forEach(x -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ranking-dashboardItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                RDashboardItem itemController = fxmlLoader.getController();
                itemController.setData(x);
                classmentList.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void clickedAdd(ActionEvent event) {
        new ServiceClassement().createClassement(new Classement(Integer.parseInt(rang.getText()),((EquipeCombo)equipe.getSelectionModel().getSelectedItem()).getID(),((Evenement)evenement.getSelectionModel().getSelectedItem()).getId()));
        refreshClassements();
    }



}
class EquipeCombo {
    private int ID;
    private String name;

    public EquipeCombo(int id, String name) {
        this.ID = id;
        this.name = name;
    }

    public int getID() { return ID; }
    public String getName() { return name; }
    public String toString() { return name; }
}