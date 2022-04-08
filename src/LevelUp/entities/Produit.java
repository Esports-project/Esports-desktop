package Esprit.entities;

import java.util.Date;

public class Produit {

    /* ------------------------------------ Attributes ------------------------------------ */
    private int id;
    private String nom;
    private int quantity;
    private float price;
    private String description;
    private String image;
    private float solde;
    private boolean active;
    private String referance;
    private Date updatedAt;

    /* ------------------------------------ Getters & Setters ------------------------------------ */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getReferance() {
        return referance;
    }

    public void setReferance(String referance) {
        this.referance = referance;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /* ------------------------------------ Constructors ------------------------------------ */
    //All
    public Produit(int id, String nom, int quantity, float price, String description, String image, float solde, boolean active, String referance, Date updatedAt) {
        this.id = id;
        this.nom = nom;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.solde = solde;
        this.active = active;
        this.referance = referance;
        this.updatedAt = updatedAt;
    }

    public Produit() {}

    public Produit(int id) {
        this.id = id;
    }

    //No ID
    public Produit(String nom, int quantity, float price, String description, String image, float solde, boolean active, String referance, Date updatedAt) {
        this.nom = nom;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.solde = solde;
        this.active = active;
        this.referance = referance;
        this.updatedAt = updatedAt;
    }

    //No ID No Date
    public Produit(String nom, int quantity, float price, String description, String image, float solde, boolean active, String referance) {
        this.nom = nom;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.solde = solde;
        this.active = active;
        this.referance = referance;
    }

    /* ------------------------------------ ToString ------------------------------------ */
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", solde=" + solde +
                ", active=" + active +
                ", referance='" + referance + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}