package Esprit.entities;

public class Panier {
    /* ------------------------------------ Attributes ------------------------------------ */
    private Produit idProduit;
    private int quantite;

    /* ------------------------------------ Getters & Setters ------------------------------------ */
    public Produit getProduit() {
        return idProduit;
    }

    public void setProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /* ------------------------------------ Constructors ------------------------------------ */
    //All
    public Panier(Produit idProduit, int quantite) {
        this.idProduit = idProduit;
        this.quantite = quantite;
    }
    //None
    public Panier() {
    }

    /* ------------------------------------ ToString ------------------------------------ */
    @Override
    public String toString() {
        return "LigneCommande{" +
                ", idProduit=" + idProduit +
                ", quantite=" + quantite +
                '}';
    }
}