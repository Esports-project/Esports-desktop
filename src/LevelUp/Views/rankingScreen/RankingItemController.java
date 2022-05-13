package Esprit.Views.rankingScreen;

import Esprit.Entities.Classement;

import Esprit.Services.ServiceClassement;
import Esprit.Services.ServiceEquipes;
import Esprit.Services.ServiceEvenements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class RankingItemController {

    @FXML
    private Label teamname;

    @FXML
    private Label eventname;

    @FXML
    private Label rank;


    // Fill card with data
    public void setData(Classement cls ){
        ServiceEvenements sev = new ServiceEvenements();
        ServiceEquipes seq = new ServiceEquipes();
    rank.setText(String.valueOf(cls.getId()));
    eventname.setText(sev.getNameFromID(cls.getEvenement_id()));
    teamname.setText(seq.getNameFromID(cls.getEquipe_id()));
    }

}
