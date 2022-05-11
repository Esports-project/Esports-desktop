package Esprit.Views.rankingScreen;

import Esprit.Entities.Classement;

import Esprit.Services.ServiceClassement;
import Esprit.Services.ServiceEquipes;
import Esprit.Services.ServiceEvenements;
import Esprit.Views.reclamationScreen.ReclamationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RDashboardItem implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox equipeList;

    @FXML
    private ComboBox eventList;

    @FXML
    private TextField rank;

    private Classement classement;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Generate event options
        ServiceEvenements sev = new ServiceEvenements();
        sev.readEvenements().forEach(eve -> {
            eventList.getItems().addAll(eve.getNom());
        });

        // Generate equipe options
        ServiceEquipes seq = new ServiceEquipes();
        seq.equipesList().forEach(eqe -> {
            equipeList.getItems().addAll(eqe.getNom());
        });


    }
    public void setData(Classement cl){
        ServiceEvenements sev = new ServiceEvenements();
        ServiceEquipes seq = new ServiceEquipes();

        equipeList.setValue(seq.getNameFromID(cl.getEquipe_id()));
        eventList.setValue(sev.getNameFromID(cl.getEvenement_id()));
        rank.setText(String.valueOf(cl.getRang()));
        classement = cl;

    }


    public void modifyClassement(ActionEvent e){
        ServiceClassement sc = new ServiceClassement();
        ServiceEvenements sev = new ServiceEvenements();
        ServiceEquipes seq = new ServiceEquipes();

        String rangVal = rank.getText();
        String equipeVal = equipeList.getValue().toString();
        String evenementVal = eventList.getValue().toString();

        int rang = Integer.parseInt(rangVal);
        int equipeId = seq.getIdFromName(equipeVal);
        int evenementId = sev.getIdFromName(evenementVal);

        sc.modifyClassement(new Classement(
                classement.getId(),
                rang,
                equipeId,
                evenementId
        ));
    }


    public void deleteClassement(ActionEvent e) throws IOException {
        ServiceClassement sc = new ServiceClassement();


        sc.DeleteClassement(classement.getId());
        rootPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("ranking-dashboard.fxml")));


    }

}
