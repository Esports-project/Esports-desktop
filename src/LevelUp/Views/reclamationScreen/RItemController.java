package LevelUp.Views.reclamationScreen;

import LevelUp.Services.ServiceReclamation;

import LevelUp.Entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class RItemController {

    @FXML
    private TextArea title;

    @FXML
    private TextArea content;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button reply;

    private Reclamation rec;

    private int index;


    public void deleteReclamation(ActionEvent e){
        ServiceReclamation sr = new ServiceReclamation();
        sr.deleteReclamation(rec);
        RDashboardController rd = new RDashboardController();
    }

    public void editReclamation(ActionEvent e){
        title.getText();
        content.getText();
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r = new Reclamation(
                rec.getId(),
                rec.getUser_id(),
                title.getText(),
                rec.getEmail(),
                content.getText(),
                rec.getDate(),
                rec.getStatus(),
                rec.getCategory_id()
        );
        sr.editReclamation(r);
    }
    public void setData(Reclamation r){
        title.setText(r.getSubject());
        content.setText(r.getDescription());
        rec = r;

    }

}
