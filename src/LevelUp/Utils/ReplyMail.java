package Esprit.Utils;

import Esprit.Entities.Commande;
import Esprit.Entities.Reclamation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReplyMail {

    public static void sendMail(String recip, Reclamation reclamation, String reply) throws MessagingException {
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
        Message message = prepareMessage(session, myAccountEmail, recip, reclamation, reply);

        Transport.send(message);
        System.out.println("email sent");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recip, Reclamation r, String reply){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recip));
            message.setSubject("Merci Pour Votre Commande !");

            Date date = new Date(Calendar.getInstance().getTime().getTime());

            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart=new MimeBodyPart();
            textBodyPart.setContent("<h6>Invoice "+ r.getId() +"</h6>" +
                    "<p>Issued: "+ date+"</p></br>\n" +
                    "<h2>Dear client, Thank You For your report !</h2>\n" +
                    "<h4> <b>" + r.getDescription() + "</b> \n" +
                    "<h2>We hope that our answer will help you solve the issue!</h2>\n" +
                    "<h4> <b>" + reply + "</b> \n" ,"text/html");

            emailContent.addBodyPart(textBodyPart);
            message.setContent(emailContent);

            return message;
        } catch (Exception e) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}
