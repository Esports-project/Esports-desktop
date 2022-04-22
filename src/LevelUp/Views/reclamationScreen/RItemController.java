package Esprit.Views.reclamationScreen;

import Esprit.Services.ServiceReclamation;

import Esprit.Entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class RItemController {

    @FXML
    private Label title;

    @FXML
    private Label content;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button reply;

    private Reclamation rec;


    public void deleteReclamation(ActionEvent e){
        ServiceReclamation sr = new ServiceReclamation();
        sr.deleteReclamation(rec);
        System.out.println("sdfs");
        RDashboardController rd = new RDashboardController();

    }
    public void setData(Reclamation r){
        title.setText(r.getSubject());
        content.setText(r.getDescription());
        rec = r;
    }

}
