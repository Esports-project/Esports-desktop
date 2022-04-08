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


import Esprit.entities.Produit;
import Esprit.services.ServiceProduit;

/**
 * @author Rayen BOURGUIBA
 */
public class ConnexionTest {

    public static void main(String[] args) throws SQLException {


        MyConnection mc = MyConnection.getInstance();
        Scanner sc = new Scanner(System.in);
        ServiceProduit product = new ServiceProduit();

        int choix = 100;
        while (choix != 0) {
            System.out.println("1- Gestion Produits :");
            System.out.println("2- Gestion Commandes :");
            System.out.println("******************************************");
            System.out.println("pour quitter taper 0 :");
            System.out.println("******************************************");
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 1: {
                    System.out.println("------- Gestion Produits -------");
                    System.out.println("1- Create Product :");
                    System.out.println("2- Read Products :");
                    System.out.println("3- Update Product :");
                    System.out.println("4- Delete Products :");
                    System.out.println("4- Return <- :");
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
