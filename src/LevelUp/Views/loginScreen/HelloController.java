package Esprit.Views.loginScreen;

import Esprit.Connection.MyConnection;
import Esprit.Entities.CurrentUser;
import Esprit.Views.homeScreen.HomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    private Stage mStage;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pwField;

    @FXML
    private Label messageField;

    @FXML
    private Button loginBtn;

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }

    public HelloController() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        validateLogin();
        if (CurrentUser.get().getSet()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../homeScreen/home-view.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                LoadHome(loader.getController());
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSignUpButtonClicked(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../registerScreen/register-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void validateLogin() {

        String verifyLogin = "SELECT * FROM user WHERE username='" + email.getText() + "'";

        try {
            Statement statement = cnx.prepareStatement(verifyLogin);
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getString("password").equals(pwField.getText())) {
                    if (queryResult.getInt("banned") == 1) {
                        messageField.setText("User banned please contact Levelup support");
                        clearFields();
                    }
                    else {
                        CurrentUser.get().setUserInfo(queryResult.getInt("id"),queryResult.getString("username"), queryResult.getString("username"),queryResult.getInt("role") );
                        Notifications notificationBuilder = Notifications.create()
                                .title("Succes").text("Logged in successfullly").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                                .position(Pos.TOP_CENTER)
                                .onAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        System.out.println("clicked on");
                                    }});
                        notificationBuilder.darkStyle();
                        notificationBuilder.show();
                    }
                } else {
                    messageField.setText("Invalid login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void clearFields(){
        email.setText("");
        pwField.clear();
    }

    public void LoadHome(HomeController homeController) {
        homeController.setUiFullName(CurrentUser.get().getUsername());
    }

}
