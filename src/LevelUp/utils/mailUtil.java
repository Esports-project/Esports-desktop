package Esprit.utils;

import Esprit.entities.Commande;
import com.itextpdf.text.pdf.PdfDocument;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mailUtil {
    public static void sendMail(String recip, Commande commande) throws MessagingException {
        System.out.println("Preparing to send email...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "runtimeerrorlevelup@gmail.com";
        String password = "runtime404";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recip, commande);

        Transport.send(message);
        System.out.println("email sent");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recip, Commande comm){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recip));
            message.setSubject("Merci Pour Votre Commande !");

            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart=new MimeBodyPart();
            textBodyPart.setContent("<h6>Invoice "+ comm.getId() +"</h6>" +
                    "<p>Issued: "+ comm.getDate()+"</p></br>\n" +
                    /*"{% for ligne in commande.ligneCommandes %}\n" +
                    "{% set i = 0 %}\n" +
                    "{% set i = ligne.produit.price * ligne.quantite %}\n" +
                    "{% endfor %}\n" +
                    "\n" +*/
                    "<h2>Dear Admin, Thank You For Shopping With LevelUp !</h2>\n" +
                    "<h4>This Email is a confirmation that you ordered a total of <b>" + comm.getQuantite() + "</b> products priced at <b>"+comm.getPrix_total() +" TND</b> </h4>\n" +
                    "<h2>We hope to see you again soon!</h2>","text/html");
            MimeBodyPart pdfPart = new MimeBodyPart();
            String name ="Invoice"+comm.getId()+".pdf";
            String fileName = "D:\\pdfJava\\"+name;
            pdfPart.attachFile(fileName);
            emailContent.addBodyPart(pdfPart);
            emailContent.addBodyPart(textBodyPart);
            message.setContent(emailContent);

            return message;
        } catch (Exception e) {
            Logger.getLogger(mailUtil.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}
