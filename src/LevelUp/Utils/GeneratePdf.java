package Esprit.Utils;
import Esprit.Entities.Commande;
import Esprit.Entities.LigneCommande;
import Esprit.Entities.Produit;
import Esprit.Services.ServiceLigneCommande;
import Esprit.Services.ServiceProduit;
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
            Paragraph price = new Paragraph(new Phrase("\n\nPrix Total : "+commande.getPrix_total(), FontFactory.getFont(FontFactory.HELVETICA, 22))+"\n\n\n");
            Image img=Image.getInstance("src\\LevelUp\\Resources\\logo-dark.png");
            img.setAlignment(Element.ALIGN_CENTER);
            PdfPTable table=new PdfPTable(3);
            PdfPCell c1=new PdfPCell(new Phrase("Produit"));
            table.addCell(c1);
            PdfPCell c2=new PdfPCell(new Phrase("Quantité"));
            table.addCell(c2);
            PdfPCell c3=new PdfPCell(new Phrase("Prix"));
            table.addCell(c3);
            table.setHeaderRows(1);

            ServiceLigneCommande serviceLigneCommande =new ServiceLigneCommande();
            ServiceProduit serviceProduit =new ServiceProduit();
            List<LigneCommande> list = serviceLigneCommande.LigneCommandeList();
            List<Produit> listProd = serviceProduit.ProduitList();
            float prix=0;
            for (int i=0; i<list.size();i++){
                LigneCommande ligneCommande = list.get(i);
                for (int j=0; j<listProd.size();j++){
                    Produit produit =listProd.get(j);
                    if (produit.getId()==ligneCommande.getIdProduit()){
                        prix=produit.getPrice();
                    }
                }
                if (ligneCommande.getIdCommande()==commande.getId()){
                    System.out.println(ligneCommande);
                    PdfPCell c4=new PdfPCell(new Phrase(""+ligneCommande.getIdProduit()));
                    table.addCell(c4);
                    PdfPCell c5=new PdfPCell(new Phrase(""+ligneCommande.getQuantite()));
                    table.addCell(c5);
                    prix=prix*ligneCommande.getQuantite();
                    PdfPCell c6=new PdfPCell(new Phrase(""+prix));
                    table.addCell(c6);
                }}
            document.add(paragraph);
            document.add(img);
            document.add(price);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}