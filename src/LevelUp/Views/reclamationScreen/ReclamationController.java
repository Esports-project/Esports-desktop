package Esprit.Views.reclamationScreen;


import Esprit.Entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import Esprit.Services.ServiceCategory;
import Esprit.Services.ServiceReclamation;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationController implements Initializable {

    @FXML
    private TextArea contentArea;

    @FXML
    private TextArea subjectArea;

    @FXML
    private Button sendBtn;

    @FXML
    private ComboBox articleArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceCategory sc = new ServiceCategory();
        sc.categoryList().forEach(category -> {
            articleArea.getItems().addAll(category.getName());
        });

    }

     public void handleClick(ActionEvent actionEvent){
        ServiceReclamation sr = new ServiceReclamation();
        ServiceCategory sc = new ServiceCategory();
        if (actionEvent.getSource() == sendBtn) {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String subject = subjectArea.getText();
            String content = contentArea.getText();
            String val = articleArea.getValue().toString();
            System.out.println(val);
            int valId = sc.getIdFromName(val);
            System.out.println(valId);
            sr.addReclamation(new Reclamation(  0,
                    subject,
                    "email",
                    content,
                    date,
                    0,
                    valId)
                  );

        }
    }
}

