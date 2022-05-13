package Esprit.Views.blogScreen;

import Esprit.Connection.MySqlConnect;
import Esprit.Entities.Blog;
import Esprit.Entities.Evenement;
import Esprit.Entities.Games;
import Esprit.Services.ServiceBlog;
import Esprit.Services.ServiceEvenements;
import Esprit.Services.ServiceGames;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardBlog implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Blog> tableView;

    @FXML
    private TableColumn colTitre;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colImage;

    @FXML
    private TableColumn colContenu;

    @FXML
    private Button ajoutBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button imageBtn;

    @FXML
    private TextField titre;

    @FXML
    private TextField contenu;

    private String imageFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    updateTable();
    }
    ObservableList<Blog> listM;
    public void updateTable(){

        colId.setCellValueFactory(new PropertyValueFactory<Blog, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Blog, String>("titre"));
        colContenu.setCellValueFactory(new PropertyValueFactory<Blog, String>("contenu"));
        colDate.setCellValueFactory(new PropertyValueFactory<Blog, Date>("post_date"));
        colImage.setCellValueFactory(new PropertyValueFactory<Blog, String>("image"));

        listM = MySqlConnect.getBlogs();
        tableView.setItems(listM);
    }

    public void ajoutBlog(ActionEvent e){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ServiceBlog blog = new ServiceBlog();
        Blog b = new Blog(
                titre.getText(),
                contenu.getText(),
                imageFile,
                date
        );
        blog.createBlog(b);
        updateTable();
    }

    public  void deleteBlog(ActionEvent e){
        ServiceBlog sb = new ServiceBlog();
        Blog b = tableView.getSelectionModel().getSelectedItem();
        sb.deleteBlog(b);
        updateTable();
    }

    public void ajoutImage(ActionEvent e){
        FileChooser filechooser = new FileChooser();
        File f = filechooser.showOpenDialog(null);

        if(f != null){
            imageFile = f.getAbsolutePath();
        }
    }
}
