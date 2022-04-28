package LevelUp.Views.eventScreen;

import LevelUp.Entities.Evenement;
import LevelUp.Services.ServiceEvenements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.ZoneId;

public class DashboardItemController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField description;

    @FXML
    private TextField nom;

    @FXML
    private TextField organizateur;

    @FXML
    private DatePicker datePicker;

    private Evenement ev;


    public void setData(Evenement evenement) {
        InputStream stream = null;

        try {
            stream = new FileInputStream(evenement.getImage());
            Image image = new Image(stream);
            nom.setText(evenement.getNom());
            organizateur.setText(evenement.getOrganisateur());
            description.setText(evenement.getDescription());
            datePicker.setValue(evenement.getDate().toLocalDate());
            imageView.setImage(image);
            ev = evenement;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void modifierEvent(ActionEvent e) {
        ServiceEvenements se = new ServiceEvenements();
        java.util.Date date =
                java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());


        Evenement x = new Evenement(
                ev.getId(),
                nom.getText(),
                organizateur.getText(),
                description.getText(),
                ev.getImage(),
                sqlDate
        );
        se.modifyEvenement(x);
    }

    public void deleteEvent(ActionEvent e){
        ServiceEvenements se = new ServiceEvenements();
        se.deleteEvenement(ev);
    }

}
