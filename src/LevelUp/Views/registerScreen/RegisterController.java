package Esprit.Views.registerScreen;

import Esprit.Connection.MyConnection;
import Esprit.Services.ServiceMailer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.sql.*;

public class RegisterController {

    private Connection cnx;
    private PreparedStatement ste;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private PasswordField passwordField;

    ServiceMailer mailer = new ServiceMailer();

    public ServiceMailer getMailer() {
        return mailer;
    }


    public RegisterController() {
        cnx = MyConnection.getInstance().getConnection();
    }


    public void onRegBtnClick(ActionEvent event) throws SQLException {

        String username = usernameField.getText();
        String email = emailField.getText();
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        String pw = passwordField.getText();


        if(checkFields(username,email, firstname, lastname, pw))
        {

            String qry = String.format("INSERT INTO user (username,email,prenom,nom,password, roles, banned, phone) VALUES ('%s','%s','%s','%s','%s', 0,0,0)", username, email, firstname, lastname,pw);
            Statement stm = null;
            try {
                stm = cnx.createStatement();
                stm.executeUpdate(qry);
                clearFields();
                ServiceMailer.sendMail(email);
                Notifications notificationBuilder = Notifications.create()
                        .title("Succes").text("Registred successfullly").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked on");
                            }});
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../loginScreen/hello-view.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public boolean checkFields(String username, String email, String firstname, String lastname, String pw){
        if(username.length()==0 || email.length()==0 || firstname.length()==0 || lastname.length()==0 || pw.length() ==0){
            showDialog("WARN","Empty Fields!","Please fill the empty fields!");
            return false;
        }
        return true;
    }

    public void clearFields(){
        usernameField.setText("");
        emailField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.clear();
    }

    public void showDialog(String type, String first, String second){
        Alert alert;
        if(type.equals("WARN")) {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        else if(type.equals("INFO")){
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        else return;
        alert.setTitle(first);
        alert.setHeaderText(null);
        alert.setContentText(second);
        alert.showAndWait();
    }

    public void sendEmail() throws AddressException, MessagingException, IOException {

    }



}
