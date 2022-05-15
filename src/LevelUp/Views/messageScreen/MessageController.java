package Esprit.Views.messageScreen;

import Esprit.Entities.Messages;
import Esprit.Services.ServiceMessage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MessageController implements Initializable {

    @FXML
    private Button sendBtn;

    @FXML
    private TextField textField;

    @FXML
    private VBox vBox;

    @FXML
    private ScrollPane scrollPane;

    private Server server;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
        try{

            server = new Server(new ServerSocket(6969));

        }catch (IOException e){

            e.printStackTrace();
        }

        vBox.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue((Double) newValue));

        server.receiveMessageFromClient(vBox);

        sendBtn.setOnAction(event -> {
            String messageToSend = textField.getText();
            if(!messageToSend.isEmpty()){

                Date date = new Date(Calendar.getInstance().getTime().getTime());
                ServiceMessage sm = new ServiceMessage();
                Messages message = new Messages(
                        0,
                        0,
                        messageToSend,
                        date,
                        0
                );

                sm.addMessage(message);
                HBox hBox =new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5,5,5,10));

                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);

                textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                        "-fx-background-color: rgb(15,125,242);" +
                        "-fx-background-radius: 20px;");
                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.934,0.945,0.996));

                hBox.getChildren().add(textFlow);
                vBox.getChildren().add(hBox);

                server.sendMessageToClient(messageToSend);
                textField.clear();
            }
        });
        }).start();

    }
    public static void addLabel(String messageFromClient, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(messageFromClient);
        TextFlow textFlow =new TextFlow(text);
        textFlow.setStyle( "-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> vBox.getChildren().add(hBox));

    }
}
