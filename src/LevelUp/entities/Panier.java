package Esprit.entities;

import com.sun.javafx.collections.ObservableMapWrapper;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Lignecommande")
@XmlAccessorType (XmlAccessType.FIELD)
public class Panier implements Serializable{

    @XmlElement(name = "Ligne")
    private Map<Produit,Integer> LigneCommande = new HashMap<Produit, Integer>() ;

    public Panier() {
    }

    public Panier(Map<Produit, Integer> LigneCommande) {
        this.LigneCommande = LigneCommande;
    }

    public Map<Produit, Integer> getLignedeCommande() {
        return LigneCommande;
    }

    public void setLigneCommande(Map<Produit, Integer> LigneCommande) {
        this.LigneCommande = LigneCommande;
    }
}