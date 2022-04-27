package Esprit.Views.rankingScreen;

import Esprit.Entities.Classement;

import Esprit.Services.ServiceClassement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RDashboardItem {

    @FXML
    private TextField equipe;

    @FXML
    private TextField event;

    @FXML
    private TextField rank;

    private Classement classement;

    public void setData(Classement cl){
        equipe.setText(String.valueOf(cl.getEquipe_id()));
        event.setText(String.valueOf(cl.getEvenement_id()));
        rank.setText(String.valueOf(cl.getRang()));
        classement = cl;

    }


    public void modifyClassement(ActionEvent e){
        ServiceClassement sc = new ServiceClassement();
        Classement c = new Classement(
                classement.getId(),
                Integer.parseInt(rank.getText()) ,
                Integer.parseInt(event.getText()) ,
                Integer.parseInt(equipe.getText())
        );

        sc.modifyClassement(c);
    }


    public void deleteClassement(ActionEvent e){
        ServiceClassement sc = new ServiceClassement();

        sc.DeleteClassement(classement.getId());
    }

}
