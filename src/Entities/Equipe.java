/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author dorra
 */
public class Equipe {
    private int id ;
    private String nom;

    public Equipe() {}

    public Equipe(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Equipe(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + '}';
    }
    
}
