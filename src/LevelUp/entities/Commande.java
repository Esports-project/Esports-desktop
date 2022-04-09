package Esprit.entities;

import java.util.Date;

public class Commande {
    /* ------------------------------------ Attributes ------------------------------------ */
    private int id;
    private Date date;
    private int quantite;
    private float prix_total;
    private int user_id;
    private String status;

    /* ------------------------------------ Getters & Setters ------------------------------------ */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /* ------------------------------------ Constructors ------------------------------------ */
    //All
    public Commande(int id, Date date, int quantite, float prix_total, int user_id, String status) {
        this.id = id;
        this.date = date;
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.user_id = user_id;
        this.status = status;
    }

    //none
    public Commande() {
    }

    //no ID
    public Commande(Date date, int quantite, float prix_total, int user_id, String status) {
        this.date = date;
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.user_id = user_id;
        this.status = status;
    }

    //No ID No Date
    public Commande(int quantite, float prix_total, int user_id, String status) {
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.user_id = user_id;
        this.status = status;
    }

    /* ------------------------------------ ToString ------------------------------------ */
    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", date=" + date +
                ", quantite=" + quantite +
                ", prix_total=" + prix_total +
                ", user_id=" + user_id +
                ", status='" + status + '\'' +
                '}';
    }
}
