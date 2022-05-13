package Esprit.Services;

import Esprit.Connection.MyConnection;
import Esprit.Entities.Games;
import Esprit.Entities.Reclamation;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceGames {

    private Connection cnx;
    private PreparedStatement ste;


    public ServiceGames() {
        cnx = MyConnection.getInstance().getConnection();
    }




    /* ----------------- Create ----------------- */
    public void addGame(Games g) {
        try {
            String query = "INSERT INTO jeux (nom,description) VALUES(?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);

            pst.setString(1, g.getNom());
            pst.setString(2, g.getDescription());


            pst.executeUpdate();
            System.out.println(g);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Games> gameList() {
        List<Games> myList = new ArrayList<Games>();
        String query = "SELECT * from jeux";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Games games = new Games();
                games.setId(rs.getInt(1));
                games.setNom(rs.getString(2));
                games.setDescription(rs.getString(3));

                myList.add(games);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    /* ----------------- Update ----------------- */
    public void editGame(Games g)  {
        String requete = "UPDATE jeux SET nom=?, description=?  WHERE id=?";
        try {
            ste = cnx.prepareStatement(requete);

            ste.setInt(3, g.getId());
            ste.setString(1, g.getNom());
            ste.setString(2, g.getDescription());

            ste.executeUpdate();
            System.out.println("Game Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Game non Modfié !");
        }
    }


    public void exportTable(int test) throws SQLException, FileNotFoundException, DocumentException {
        Statement st =cnx.createStatement();


        String query = "SELECT jeux.nom,jeux.description from jeux where id="+test;

        ResultSet rs = st.executeQuery(query);
        Document my_pdf_report = new Document() {};
        int min = 10000;
        int max = 99999;
        int random_id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:\\Users\\Yasine\\OneDrive\\Bureaublad\\Dorra" + random_id + ".pdf"));
        my_pdf_report.open();
        my_pdf_report.add(new Paragraph("Orders report", FontFactory.getFont("Arial", 20)));
        my_pdf_report.add(new Paragraph("  "));
        my_pdf_report.add(new Paragraph("  "));
        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(3);
        //create a cell object
        PdfPCell table_cell;
        table_cell = new PdfPCell(new Phrase("jeux"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("quantite"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("description"));
        my_report_table.addCell(table_cell);
        while (rs.next()) {
            String nom = rs.getString("nom");
            table_cell = new PdfPCell(new Phrase(nom));

            my_report_table.addCell(table_cell);
            String quantite = rs.getString("description");
            table_cell = new PdfPCell(new Phrase(String.valueOf(quantite)));
            my_report_table.addCell(table_cell);
            String total = rs.getString("description");
            System.out.println(total);
            table_cell = new PdfPCell(new Phrase(String.valueOf(total)));
            my_report_table.addCell(table_cell);


        }

        my_pdf_report.addTitle("Orders report");
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();
        System.out.println("ijij");
    }
    /* ----------------- Delete ----------------- */
    public void deleteGame(Games g) {
        String query = "DELETE FROM jeux WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, g.getId());
            ste.executeUpdate();
            System.out.println("Game Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
