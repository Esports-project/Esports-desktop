package Esprit.tests;

import Esprit.Connection.MyConnection;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import Esprit.entities.Messages;
import Esprit.entities.Produit;
import Esprit.entities.Reclamation;
import Esprit.services.ServiceMessage;
import Esprit.services.ServiceProduit;
import Esprit.services.ServiceReclamation;

/**
 * @author Rayen BOURGUIBA
 */
public class ConnexionTest {



    public static void main(String[] args) throws SQLException {


        MyConnection mc = MyConnection.getInstance();
        Scanner sc = new Scanner(System.in);
        int choice = 100;
        while (choice != 0) {
            System.out.println("1- Gestion Produits et Commandes :");
            System.out.println("2- Gestion Reclamation et Messages :");
            System.out.println("******************************************");
            System.out.println("pour quitter taper 0 :");
            System.out.println("******************************************");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1: productCommand(); break;
                case 2: reclamationMessage(); break;
                default: break;
            }

        }

    }
    static void reclamationMessage() throws SQLException  {

        ServiceReclamation reclamation = new ServiceReclamation();
        ServiceMessage message = new ServiceMessage();
        Scanner sc = new Scanner(System.in);
        int choix = 100;
        System.out.println("------- Gestion Reclamation et Messages -------");
        System.out.println("1- Reclamation :");
        System.out.println("2- Messages :");
        System.out.println("0- Return <- :");

        choix = Integer.parseInt(sc.nextLine());
        while (choix != 0){
            switch (choix) {
                case 1: {
                    System.out.println("------- Gestion Reclamation -------");
                    System.out.println("1- Create Reclamation :");
                    System.out.println("2- Read Reclamation :");
                    System.out.println("3- Update Reclamation :");
                    System.out.println("4- Delete Reclamation :");
                    System.out.println("5- Return <- :");
                    choix = Integer.parseInt(sc.nextLine());

                    switch (choix) {
                        case 1:
                            java.util.Date date = new java.util.Date();
                            int status = 0;

                            System.out.println("*** Create Reclamation ***");
                            System.out.println("User ID:");
                            int user_id = Integer.parseInt(sc.nextLine());
                            System.out.println("Subject:");
                            String subject = sc.nextLine();
                            System.out.println("Email:");
                            String email = sc.nextLine();
                            System.out.println("Description:");
                            String description = sc.nextLine();
                            System.out.println("Category ID:");
                            int category_id = Integer.parseInt(sc.nextLine());

                            Reclamation r = new Reclamation(user_id,subject,email,description,date,status,category_id);
                            reclamation.addReclamation(r);
                            System.out.println(r);
                            break;

                        case 2:
                            System.out.println("*** Read All Reclamation ***");
                            reclamation.reclamationList().forEach(e->System.out.println(e));
                            break;

                        case 3:
                            System.out.println("*** Update Reclamation ***");
                            System.out.println("ID of reclamation to edit :");
                            Integer y = Integer.parseInt(sc.nextLine());
                            System.out.println("User ID:");
                            user_id = Integer.parseInt(sc.nextLine());
                            System.out.println("Subject:");
                            subject = sc.nextLine();
                            System.out.println("Email:");
                            email = sc.nextLine();
                            System.out.println("Description:");
                            description = sc.nextLine();
                            System.out.println("Status:");
                            status = Integer.parseInt(sc.nextLine());
                            System.out.println("Category ID :");
                            category_id = Integer.parseInt(sc.nextLine());

                            Reclamation r2= new Reclamation (y,user_id,subject, email,description,status,category_id );
                            reclamation.editReclamation(r2);
                            System.out.println(r2);
                            break;
                        case 4:
                            System.out.println("*** Delete Reclamation ***");
                            System.out.println("ID of reclamation to delete :");
                            Integer x = Integer.parseInt(sc.nextLine());

                            Reclamation rec = new Reclamation(x);
                            reclamation.deleteReclamation(rec);
                            break;
                    }
                    break;
                }
                case 2: {
                    System.out.println("------- Gestion Message -------");
                    System.out.println("1- Create Message :");
                    System.out.println("2- Read Message :");
                    System.out.println("3- Update Message :");
                    System.out.println("4- Delete Message :");
                    System.out.println("5- Return <- :");
                    choix = Integer.parseInt(sc.nextLine());

                    switch (choix) {
                        case 1:
                            java.util.Date date = new java.util.Date();
                            int status = 0;

                            System.out.println("*** Create Message ***");
                            System.out.println("Sender ID:");
                            int sender_id = Integer.parseInt(sc.nextLine());
                            System.out.println("Receiver ID:");
                            int receiver_id = Integer.parseInt(sc.nextLine());
                            System.out.println("Message:");
                            String content = sc.nextLine();
                            System.out.println("Seen 0 = no | 1 = yes:");
                            int seen = Integer.parseInt(sc.nextLine());

                            Messages m = new Messages(sender_id,receiver_id,content,date,seen);
                            message.addMessage(m);
                            System.out.println(m);
                            break;

                        case 2:
                            System.out.println("*** Read All Messages ***");
                            message.messagesList().forEach(e->System.out.println(e));
                            break;

                        case 3:
                            System.out.println("*** Update Message ***");
                            System.out.println("ID of message to edit :");
                            Integer y = Integer.parseInt(sc.nextLine());
                            System.out.println("Sender ID:");
                            sender_id = Integer.parseInt(sc.nextLine());
                            System.out.println("Receiver ID:");
                            receiver_id = Integer.parseInt(sc.nextLine());
                            System.out.println("Message:");
                            content = sc.nextLine();
                            System.out.println("Seen:");
                            seen = Integer.parseInt(sc.nextLine());


                            Messages m2= new Messages (y,sender_id,receiver_id,content,seen);
                            message.editMessage(m2);
                            System.out.println(m2);
                            break;
                        case 4:
                            System.out.println("*** Delete Message ***");
                            System.out.println("ID of message to delete :");
                            Integer x = Integer.parseInt(sc.nextLine());

                            Messages ms = new Messages(x);
                            message.deleteMessage(ms);
                            break;
                    }
                    break;
                }
            }
        }
    }
       static void productCommand() throws SQLException  {

        ServiceProduit product = new ServiceProduit();
        Scanner sc = new Scanner(System.in);
        int choix = 100;
       System.out.println("------- Gestion Produits et Commandes -------");
       System.out.println("1- Produits :");
       System.out.println("2- Commandes :");

       System.out.println("0- Return <- :");
       choix = Integer.parseInt(sc.nextLine());
            while (choix != 0){
                switch (choix) {
                    case 1: {
                        System.out.println("------- Gestion Produits -------");
                        System.out.println("1- Create Product :");
                        System.out.println("2- Read Products :");
                        System.out.println("3- Update Product :");
                        System.out.println("4- Delete Products :");
                        System.out.println("5- Return <- :");
                        choix = Integer.parseInt(sc.nextLine());

                        switch (choix) {
                            case 1:
                                System.out.println("*** Create Product ***");
                                System.out.println("Nom:");
                                String nomProd = sc.nextLine();
                                System.out.println("Quantite:");
                                int quantityProd = Integer.parseInt(sc.nextLine());
                                System.out.println("Prix:");
                                float prixProd = Float.parseFloat(sc.nextLine());
                                System.out.println("Description:");
                                String descProd = sc.nextLine();
                                System.out.println("Image:");
                                String imageProd = sc.nextLine();
                                System.out.println("y a-t-il un solde? :");
                                float soldeProd = Float.parseFloat(sc.nextLine());
                                System.out.println("Publié : Oui / Non");
                                boolean isActiveProd = Boolean.parseBoolean(sc.nextLine());
                                System.out.println("Referance");
                                String refProd = sc.nextLine();
                                java.util.Date date = new java.util.Date();

                                Produit prod = new Produit(nomProd,quantityProd,prixProd,descProd,imageProd,soldeProd,isActiveProd,refProd,date);
                                product.addProduit(prod);
                                System.out.println(prod);
                                break;

                            case 2:
                                System.out.println("*** Read All Products ***");
                                product.produitList().forEach(e->System.out.println(e));
                                break;

                            case 3:
                                System.out.println("*** Update Product ***");
                                System.out.println("ID of product to edit :");
                                int id = Integer.parseInt(sc.nextLine());
                                System.out.println("Nom:");
                                String nom = sc.nextLine();
                                System.out.println("Quantite:");
                                int quantity = Integer.parseInt(sc.nextLine());
                                System.out.println("Prix:");
                                float prix = Float.parseFloat(sc.nextLine());
                                System.out.println("Description:");
                                String desc = sc.nextLine();
                                System.out.println("Image:");
                                String image = sc.nextLine();
                                System.out.println("y a-t-il un solde? :");
                                float solde = Float.parseFloat(sc.nextLine());
                                System.out.println("Publié : Oui / Non");
                                boolean isActive = Boolean.parseBoolean(sc.nextLine());
                                System.out.println("Referance");
                                String ref = sc.nextLine();
                                java.util.Date dateup = new java.util.Date();
                                Produit p2= new Produit (id,nom,quantity,prix,desc,image,solde,isActive,ref,dateup);
                                product.editProduit(p2);
                                System.out.println(p2);
                                break;
                            case 4:
                                System.out.println("*** Delete Product ***");
                                System.out.println("ID of product to delete :");
                                int idProd = Integer.parseInt(sc.nextLine());
                                Produit produit = new Produit(idProd);
                                product.deleteProduit(produit);
                                break;
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("------- Gestion Commandes -------");
                        System.out.println("Rigel L panier se3a");
                        break;
                    }
                }
            }

       }

   }
