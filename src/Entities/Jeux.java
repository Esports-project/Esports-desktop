/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author dorra
 */
public class Jeux {
    private int id;
    private String nom ;
    private String Description;
    static int test;

    public static int getTest() {
        return test;
    }

    public static void setTest(int test) {
        Jeux.test = test;
    }

    public Jeux() {}

    public Jeux(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Jeux(int id, String nom, String Description) {
        this.id = id;
        this.nom = nom;
        this.Description = Description;
    }

    public Jeux(String nom, String Description) {
        this.nom = nom;
        this.Description = Description;
    }

    public Jeux(String valorant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Jeux{" + "id=" + id + ", nom=" + nom + ", Description=" + Description + '}';
    }
    
}
