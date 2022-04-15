package Esprit.tests;

import Esprit.Connection.MyConnection;

import java.sql.SQLException;
import java.util.Scanner;

import Esprit.entities.Messages;
import Esprit.entities.Panier;
import Esprit.entities.Produit;
import Esprit.entities.Reclamation;
import Esprit.services.ServiceMessage;
import Esprit.services.ServicePanier;
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
            switch (choice) {
                case 1:
                    productCommand();
                    break;
                case 2:
                    reclamationMessage();
                    break;
                default:
                    break;
            }

        }

    }

    static void reclamationMessage() throws SQLException {

        ServiceReclamation reclamation = new ServiceReclamation();
        ServiceMessage message = new ServiceMessage();
        Scanner sc = new Scanner(System.in);
        int choix = 100;
        System.out.println("------- Gestion Reclamation et Messages -------");
        System.out.println("1- Reclamation :");
        System.out.println("2- Messages :");
        System.out.println("0- Return <- :");

        choix = Integer.parseInt(sc.nextLine());
        while (choix != 0) {
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

                            Reclamation r = new Reclamation(user_id, subject, email, description, date, status, category_id);
                            reclamation.addReclamation(r);
                            System.out.println(r);
                            break;

                        case 2:
                            System.out.println("*** Read All Reclamation ***");
                            reclamation.reclamationList().forEach(e -> System.out.println(e));
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

                            Reclamation r2 = new Reclamation(y, user_id, subject, email, description, status, category_id);
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

                            Messages m = new Messages(sender_id, receiver_id, content, date, seen);
                            message.addMessage(m);
                            System.out.println(m);
                            break;

                        case 2:
                            System.out.println("*** Read All Messages ***");
                            message.messagesList().forEach(e -> System.out.println(e));
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


                            Messages m2 = new Messages(y, sender_id, receiver_id, content, seen);
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

    static void productCommand() throws SQLException {
        ServiceProduit product = new ServiceProduit();
        Scanner sc = new Scanner(System.in);
        int choix = 100;
        ServicePanier cart = new ServicePanier();

        System.out.println("------- Gestion Produits et Commandes -------");
        System.out.println("1- Produits :");
        System.out.println("2- Commandes :");
        System.out.println("0- Return <- :");
        choix = Integer.parseInt(sc.nextLine());

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

                        Produit prod = new Produit(nomProd, quantityProd, prixProd, descProd, imageProd, soldeProd, isActiveProd, refProd, date);
                        product.addProduit(prod);
                        System.out.println(prod);
                        break;

                    case 2:
                        System.out.println("*** Read All Products ***");
                        product.produitList().forEach(e -> System.out.println(e));
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
                        System.out.println("Publié : true / false");
                        boolean isActive = Boolean.parseBoolean(sc.nextLine());
                        System.out.println("Referance");
                        String ref = sc.nextLine();
                        java.util.Date dateup = new java.util.Date();
                        Produit p2 = new Produit(id, nom, quantity, prix, desc, image, solde, isActive, ref, dateup);
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
                if (cart.getOrderCount() == 0) {
                    System.out.println("        ****       ");
                    System.out.println("Your cart is empty.");
                    System.out.println("        ****       ");
                } else {
                    System.out.println("Your cart contains these items:");
                    for (Panier order : cart.getOrders()) {
                        System.out.println(order.getProduit() +
                                "\tx" + order.getQuantite());
                    }
                }
                System.out.println("1- Add A Product To The Cart :");
                System.out.println("2- Remove A Product From The Cart :");
                System.out.println("3- Change A Product's Quantity :");
                System.out.println("4- See How Much My Total Order Costs :");
                System.out.println("5- Check Out And Purchase Everything :");
                System.out.println("6- Return <- :");
                choix = Integer.parseInt(sc.nextLine());
                while (choix != 6) {
                    switch (choix) {
                        case 1:
                            System.out.println("What's The Product's ID Would You Like To Add To The Cart?");
                            int idProd = Integer.parseInt(sc.nextLine());
                            Produit produit = product.getProduitById(idProd);

                            if (cart.hasProduct(produit)) {
                                System.out.println("That product is already in the cart.");
                            }

                            System.out.println("How many would you like to add?");
                            int quantity = Integer.parseInt(sc.nextLine());
                            cart.addItem(produit, quantity);

                            if (cart.getOrderCount() == 0) {
                                System.out.println("        ****       ");
                                System.out.println("Your cart is empty.");
                                System.out.println("        ****       ");
                            } else {
                                System.out.println("Your cart contains these items:");
                                for (Panier order : cart.getOrders()) {
                                    System.out.println(order.getProduit() +
                                            "\tx" + order.getQuantite());
                                }
                            }
                            break; // We are done with this iteration - start over

                        case 2:
                            System.out.println("What product would you like to remove?");
                            int idDelete = Integer.parseInt(sc.nextLine());
                            Produit produitDelete = product.getProduitById(idDelete);

                            if (cart.hasProduct(produitDelete)) {
                                cart.removeItem(produitDelete);
                                System.out.println("Product removed successfully.");
                            } else {
                                System.out.println("That product does not exist.");
                            }
                            break;

                        case 3:
                            // Change an item's quantity
                            System.out.println("What product would you like to update?");
                            int idAdd = Integer.parseInt(sc.nextLine());
                            Produit produitAdd = product.getProduitById(idAdd);

                            if (!cart.hasProduct(produitAdd)) {
                                System.out.println("That product is not in the cart.");
                                continue; // We are done with this iteration - start over
                            }
                            System.out.println("You currently have " + cart.getProductQuantity(produitAdd));
                            System.out.println("How many would you like instead?");
                            int quantityAdd = Integer.parseInt(sc.nextLine());
                            cart.changeQuantity(produitAdd, quantityAdd);
                            System.out.println("You now have " + quantityAdd + " " + product + "s.");
                            break;

                        case 4:
                            // See how much the total order costs
                            System.out.println("Your order comes to a grand total of $" + cart.getTotalOrderCost());
                            break;

                        case 5:
                            // Check out and purchase everything
                            System.out.println("You paid the total amount of $" + cart.getTotalOrderCost() + ".");
                            break;
                    }
                    break;
                }
            }
        }
    }

}
