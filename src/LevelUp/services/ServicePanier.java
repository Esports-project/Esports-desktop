package Esprit.services;

import Esprit.entities.Panier;
import Esprit.entities.Produit;
import Esprit.Connection.MyConnection;
import java.sql.Connection;
import java.lang.Integer;
import javafx.collections.ObservableMap;

public class ServicePanier {
    Connection cnx;

    public static Panier lc = new Panier();

    public ServicePanier() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void ajouterLigneDeCommande(Produit p, int qt) {
        if (lc.getLignedeCommande().containsKey(p)) {
            lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) + qt);
        } else {
            lc.getLignedeCommande().put(p, qt);
        }
        System.out.println("Produit ajouté!");
//        HttpCookie session = new HttpCookie("panier", null);
//        session = HttpCookie.parse("panier");
//        System.out.println(session.getValue());
    }

    public boolean supprimerLigneDecommande(Produit p, int qt) {
        if(lc.getLignedeCommande().containsKey(p))
        {
            if (lc.getLignedeCommande().get(p) >= qt) {
                lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) - qt);
                if(lc.getLignedeCommande().get(p)<=0)
                {
                    lc.getLignedeCommande().remove(p);
                }
                return true;
            }
        }
        return false;

    }

    public ObservableMap<Produit, Integer> consulterLigneDeCommandes() {
        return (ObservableMap) lc.getLignedeCommande();
    }

    public void modifierLigneDeCommande(Produit p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
