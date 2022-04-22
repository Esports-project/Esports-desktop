package Esprit.Views.rankingScreen;

import Esprit.Entities.Classement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class RankingItemController {

    @FXML
    private Label teamname;

    @FXML
    private Label eventname;

    @FXML
    private Label rank;


    public void setData(Classement cls ){
    rank.setText(String.valueOf(cls.getId()));
    eventname.setText(String.valueOf(cls.getEvenement_id()));
    teamname.setText(String.valueOf(cls.getEquipe_id()));
    }

}
