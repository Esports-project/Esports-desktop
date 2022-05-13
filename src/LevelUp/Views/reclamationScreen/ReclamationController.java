package Esprit.Views.reclamationScreen;


import Esprit.Entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import Esprit.Services.ServiceCategory;
import Esprit.Services.ServiceReclamation;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ReclamationController implements Initializable {

    @FXML
    private TextArea contentArea;

    @FXML
    private TextArea subjectArea;

    @FXML
    private Button sendBtn;

    @FXML
    private TextArea emailArea;

    @FXML
    private ComboBox articleArea;

    @FXML
    private Label lblEmailError;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceCategory sc = new ServiceCategory();
        sendBtn.setDisable(true);
        sc.categoryList().forEach(category -> {
            articleArea.getItems().addAll(category.getName());
        });


        UnaryOperator<TextFormatter.Change> filterEmail = (change ->{

            if(change.getControlNewText().matches("^(.+)@(.+)$*"))
            {
                lblEmailError.setVisible(false);
                emailArea.setBorder(null);
                sendBtn.setDisable(false);

                return change;

            }


            else
            {
                lblEmailError.setText("Invalid Email");
                lblEmailError.setVisible(true);
                emailArea.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2), new Insets(-2))));
                sendBtn.setDisable(true);

                return change;
            }


        });

        if(contentArea.getText().isEmpty() || subjectArea.getText().isEmpty() || emailArea.getText().isEmpty()){
            sendBtn.setDisable(true);
        }else sendBtn.setDisable(false);

        TextFormatter <String> tf = new TextFormatter<String>(filterEmail);
        emailArea.setTextFormatter(tf);


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
                    emailArea.getText(),
                    content,
                    date,
                    0,
                    valId)
                  );

        }
    }
}

