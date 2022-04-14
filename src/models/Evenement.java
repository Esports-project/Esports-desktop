/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Evenement {

    //var
    private int id;
    private String nom;
   private String organisateur;
    private String Description;
    private Date date;
    private String image;

    //Constructor
    public Evenement() {

    }
    //..

    public Evenement(int id, String nom, String organisateur,String Description, String image, Date date) {
        this.id = id;
        this.nom = nom;
        this.organisateur = organisateur;
        this.Description = Description;
        this.image = image;
        this.date = date;
       
    }

    //..
    public Evenement(String nom, String organisateur,String Description, String image, Date date) {
       
        this.nom = nom;
        this.organisateur = organisateur;
        this.Description = Description;
        this.image = image;
        this.date = date;
       
    }

    //GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", Nom=" + nom +", Organisateur="+organisateur+ ", Description=" + Description + ", Date=" + date +'}';
    }

}