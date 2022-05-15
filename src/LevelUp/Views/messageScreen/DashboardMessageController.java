package Esprit.Views.messageScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Classement;
import Esprit.Entities.Messages;
import Esprit.Services.ServiceMessage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardMessageController implements Initializable {

    @FXML
    private Button sendBtn;

    @FXML
    private TextField textField;

    @FXML
    private VBox vBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colMsg;

    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateTable();
        try{
            client = new Client(new Socket("localhost", 6969));
            System.out.println("Connected to server");
        }catch (IOException e){
            e.printStackTrace();
        }

        vBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scrollPane.setVvalue((Double) newValue);

            }
        });

        client.receiveMessageFromServer(vBox);


        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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

                    Notifications notificationBuilder=Notifications.create()
                            .title("You received a message")
                            .graphic(null)
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT);
                    notificationBuilder.showConfirm();

                    sm.addMessage(message);
                    HBox hBox = new HBox();
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

                    client.sendMessageToServer(messageToSend);
                    textField.clear();
                    updateTable();
                }
            }
        });
    }

    public static void addLabel(String msgFromServer, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));


        Text text = new Text(msgFromServer);
        TextFlow textFlow =new TextFlow(text);
        textFlow.setStyle( "-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                vBox.getChildren().add(hBox);

            }
        });

    }
    ObservableList<Messages> listM;
    public void updateTable() {
        colId.setCellValueFactory(new PropertyValueFactory<Messages, Integer>("id"));
        colMsg.setCellValueFactory(new PropertyValueFactory<Messages, String>("message"));
        listM = MySqlConnect.getMessages();
        tableView.setItems(listM);
    }
}
