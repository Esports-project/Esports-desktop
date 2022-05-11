package Esprit.Utils;
import Esprit.Entities.Commande;
import Esprit.Entities.LigneCommande;
import Esprit.Services.ServiceLigneCommande;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class GeneratePdf {
    public static void pdf(Commande commande) throws FileNotFoundException {
        try {
            String name ="Invoice"+commande.getId()+".pdf";
            String fileName = "D:\\pdfJava\\"+name;
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph paragraph=new Paragraph("LevelUp Invoice#"+commande.getId());
            Paragraph price = new Paragraph(new Phrase("\n\nPrix Total : "+commande.getPrix_total(), FontFactory.getFont(FontFactory.HELVETICA, 22))+"\n");
           // Image img=Image.getInstance("C:\\Users\\Rayen BOURGUIBA\\Desktop\\Esports-desktop-Rayen\\src\\LevelUp\\img\\logo-dark.png");
           // img.setAlignment(Element.ALIGN_CENTER);
            PdfPTable table=new PdfPTable(3);
            PdfPCell c1=new PdfPCell(new Phrase("Produit"));
            table.addCell(c1);
            PdfPCell c2=new PdfPCell(new Phrase("Quantité"));
            table.addCell(c2);
            PdfPCell c3=new PdfPCell(new Phrase("Prix"));
            table.addCell(c3);
            table.setHeaderRows(1);

            ServiceLigneCommande serviceLigneCommande =new ServiceLigneCommande();
            List<LigneCommande> list = serviceLigneCommande.LigneCommandeList();
            for (int i=0; i<list.size();i++){
                LigneCommande ligneCommande = list.get(i);
                if (ligneCommande.getIdCommande()==commande.getId()){
                    System.out.println(ligneCommande);
                    PdfPCell c4=new PdfPCell(new Phrase(""+ligneCommande.getIdProduit()));
                    table.addCell(c4);
                    PdfPCell c5=new PdfPCell(new Phrase(""+ligneCommande.getQuantite()));
                    table.addCell(c5);
                    PdfPCell c6=new PdfPCell(new Phrase(""+ligneCommande.getIdCommande()));
                    table.addCell(c6);
                }}

            document.add(paragraph);
           // document.add(img);
            document.add(price);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}