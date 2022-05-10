/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevselim;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author sallo
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfContenu;
    @FXML
    private TextField tfPhoto;
    @FXML
    private TextField tfDate;
    @FXML
    private TableView<Blogs> tvBlogs;
    @FXML
    private TableColumn<Blogs, Integer> tvID;
    @FXML
    private TableColumn<Blogs, String> tvTitre;
    @FXML
    private TableColumn<Blogs, String> tvContenu;
    @FXML
    private TableColumn<Blogs, Date> tvDate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button evfront;
    
    
    
    PreparedStatement pst= null;
    Connection conn = null;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    
    public Connection getConnection(){
       Connection conn;
       try{
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/levelup", "root","");
           return conn;
       }catch(Exception ex){
           System.out.print("Error: " + ex.getMessage());
           return null;
       }
    }
    public ObservableList<Blogs> getBlogsList(){
        ObservableList<Blogs> blogList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM blog";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Blogs blogs;
            while(rs.next()){
                blogs = new Blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("contenu"), rs.getDate("post_date"));
                blogList.add(blogs);
            }
        }catch(Exception ex){
            ex.printStackTrace();
    }
        return blogList;
    }
    
    
   
    public void showBlogs(){
        ObservableList<Blogs> list = getBlogsList();
        
        tvID.setCellValueFactory(new PropertyValueFactory<Blogs, Integer>("id"));
        tvTitre.setCellValueFactory(new PropertyValueFactory<Blogs, String>("titre"));
        tvContenu.setCellValueFactory(new PropertyValueFactory<Blogs, String>("contenu"));
        tvDate.setCellValueFactory(new PropertyValueFactory<Blogs, Date>("post_date"));
        
        tvBlogs.setItems(list);
    }
     @FXML
    private void insertRecord(ActionEvent event){
         
        conn = getConnection();
        String sql = "insert into blog ( titre, contenu) values (?,?)";
         try{
             pst =conn.prepareStatement(sql);
             
             pst.setString(1,tfTitre.getText());
             pst.setString(2,tfContenu.getText());
             
             pst.execute();
             
             
             JOptionPane.showMessageDialog(null, "saved");
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }       
              
        
        
        
        
        
        showBlogs();
         
         
       /*  String query = "insert into blog values ("+ tfTitre.getText()+","+tfContenu.getText()+","+tfDate.getText()+")";
         executeQuery(query);
         showBlogs();

*/
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBlogs();
    }  
    @FXML
    private void updateRecord(ActionEvent event){
        String query = "UPDATE blog SET titre = '" + tfTitre.getText() + "', contenu = '" + tfContenu.getText() + "' WHERE id = "+ tfID.getText() +"";
        executeQuery(query);
        showBlogs();
    }
@FXML
    private void deletebtn(ActionEvent event){
        String query= "DELETE FROM blog WHERE id ="+ tfID.getText()+"";
        executeQuery(query);
        showBlogs();
    }
    @FXML
    private void gotoFront(){
        evfront.getScene().getWindow().hide();
                Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
             Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
           ex.printStackTrace();
        }
    }
}
