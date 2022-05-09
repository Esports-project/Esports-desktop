/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Jeux;
import Entities.review;
import Services.JeuxServices;
import Services.reviewServices;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class DetailjeuxController implements Initializable {

    @FXML
    private Label descriptiondetail;
    @FXML
    private Label nomdetail;
    @FXML
    private Label iddetailjeux;
    @FXML
    private ListView<String> listreview;
    @FXML
    private ImageView imagedetail;
Jeux jjj= new Jeux();
    @FXML
    private TextField avisjeux;
    @FXML
    private Button submitrating;
    @FXML
    private Rating ratingjeux;
    @FXML
    private Button pdf;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iddetailjeux.setVisible(false);
        Jeux j=null;
        JeuxServices jj = new JeuxServices();
        ObservableList<Jeux> ll = FXCollections.observableArrayList();
        int tet=(jjj.getTest());

        System.out.println(tet);
        try {
            j=jj.getById(tet);

                    System.out.println(j);

        } catch (SQLException ex) {
            Logger.getLogger(DetailjeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        descriptiondetail.setText(j.getDescription());
        nomdetail.setText(j.getNom());
        iddetailjeux.setText(String.valueOf(j.getId()));
         File file1 = new File("C:/Users/dorra/Documents/NetBeansProjects/PidevDorra/src/image/valorant.jpg");
                
        System.out.println(file1);
        Image img1=new Image(file1.toURI().toString());
        imagedetail.setImage(img1);
        int iiddr = Integer.parseInt(iddetailjeux.getText());
        reviewServices rev = new reviewServices();
                     ObservableList<String> list = FXCollections.observableArrayList();

        try {
            for(int i=0;i<rev.getreviews(iiddr).size();i++)
            {
                list.add(rev.getreviews(iiddr).get(i).getAvis());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailjeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listreview.setItems(list);
    }    

  

    @FXML
    private void submrate(ActionEvent event) throws SQLException {
        //start notif
         Notifications notificationBuilder=Notifications.create()
             .title("avis enregistre mercii")
             .graphic(null)
             .hideAfter(Duration.seconds(5))
             .position(Pos.TOP_RIGHT);
             notificationBuilder.showConfirm();
             // end notif 
                     ObservableList<String> list = FXCollections.observableArrayList();

             
        reviewServices rev = new reviewServices();
        double x = ratingjeux.getRating();
        String idr = iddetailjeux.getText();
        System.out.println(x);

        int iiddr = Integer.parseInt(iddetailjeux.getText());
        review r1= new review(iiddr,x*2,avisjeux.getText());
       rev.createReview(r1);
        for(int i=0;i<rev.getreviews(iiddr).size();i++)
            {
                list.add(rev.getreviews(iiddr).get(i).getAvis());
            }
            listreview.setItems(list);

    }

    @FXML
    private void imprimer(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {
         JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f= chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        Document document = new Document();
       
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));  
                     document.open();
                      PdfPTable table = new PdfPTable(3); // 3 columns.
                    table.setWidthPercentage(100); //Width 100%
                    table.setSpacingBefore(10f); //Space before table
                    table.setSpacingAfter(10f); //Space after table
                    float[] columnWidths = {1f, 1f, 1f};
                    table.setWidths(columnWidths);
                     PdfPCell cell1 = new PdfPCell(new Paragraph("nom produit"));
                    cell1.setBorderColor(BaseColor.RED);
                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell1);
                   
                    cell1 = new PdfPCell(new Paragraph("PRIX"));
                    cell1.setBorderColor(BaseColor.RED);
                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell1);
                   
                    cell1 = new PdfPCell(new Paragraph("marque"));
                    cell1.setBorderColor(BaseColor.RED);
                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell1);
                    table.setHeaderRows(1);
                   
                    //To avoid having the cell border and the content overlap, if you are having thick cell borders
                    //cell1.setUserBorderPadding(true);
                    //cell2.setUserBorderPadding(true);
                    //cell3.setUserBorderPadding(true);
                    PdfPCell cell2 = new PdfPCell(new Paragraph(nomdetail.getText()));
                    cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell2.setBorderColor(BaseColor.BLUE);
                    table.addCell(cell2);
                    PdfPCell price1 = new PdfPCell(new Paragraph(descriptiondetail.getText()));
                    price1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    price1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    price1.setBorderColor(BaseColor.RED);
                    table.addCell(price1);
                     PdfPCell contrat2 = new PdfPCell(new Paragraph(nomdetail.getText()));
                    contrat2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                    contrat2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    contrat2.setBorderColor(BaseColor.RED);
                    table.addCell(contrat2);
                   
                   
                    com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("C:\\Users\\MSi\\Esports\\src\\Images\\logoesports.png");
                     image1.setAbsolutePosition(100f, 550f);
                   
                    //Scale to new height and new width of image
                    image1.scaleAbsolute(200, 200);
                     document.addTitle("product");
                    document.addAuthor("esports");
                    document.addSubject("fiche technique du produit.");
                    Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
                    Font redFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new CMYKColor(0, 255, 200, 50));
                   
                    Paragraph sectionTitle = new Paragraph("produit disponible dans notre application  ", redFont);
                    Paragraph sectionTitle2 = new Paragraph("ETSPORTS APPLICATION :", redFont);
                   
                    java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    String rescreation = date_sql.toString();
                   
                    Paragraph sectionTitle3 = new Paragraph("created at :" + rescreation.substring(0, 10) + "", blueFont);
                   
                    sectionTitle.setAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
                    sectionTitle2.setAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
                    sectionTitle3.setAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
                   
                    document.add(sectionTitle);
                    document.add(sectionTitle2);
                   
                    document.add(sectionTitle3);
                   
                    image1.setAbsolutePosition(17, 780f);
                    document.add(image1);
                   
                    document.add(table);
                    Paragraph signature = new Paragraph("Produit original", blueFont);
                    Paragraph notice = new Paragraph("cette fiche doit etre presenté dans notre boutique pour passer la commande ", redFont);
                    document.add(notice);
                   
                    document.add(signature);
                   
           // document.addTitle(marquedetail.getText());
            document.add(new Paragraph("fiche technique du "+nomdetail.getText()+"  contient les details du produit "));
           
            document.close();
        
    }
}
