package Esprit.Entities;

public class LigneCommande {

    /* ------------------------------------ Attributes ------------------------------------ */
    private int id;
    private int idCommande;
    private int idProduit;
    private int quantite;

    /* ------------------------------------ Getters & Setters ------------------------------------ */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }


    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
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
    public LigneCommande(int id, int idCommande, int idProduit, int quantite) {
        this.id = id;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    //No ID
    public LigneCommande(int idCommande, int idProduit, int quantite) {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    //None
    public LigneCommande() {
    }

    /* ------------------------------------ ToString ------------------------------------ */
    @Override
    public String toString() {
        return "LigneCommande{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", idProduit=" + idProduit +
                ", quantite=" + quantite +
                '}';
    }
}