/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author dorra
 */
public class review {
    int id;
    int jeux_id_id;
    double note;
    String avis;

    @Override
    public String toString() {
        return "review{" + "id=" + id + ", jeux_id_id=" + jeux_id_id + ", note=" + note + ", avis=" + avis + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public review() {
    }

    public int getJeux_id_id() {
        return jeux_id_id;
    }

    public void setJeux_id_id(int jeux_id_id) {
        this.jeux_id_id = jeux_id_id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public review(int jeux_id_id, double note, String avis) {
        this.jeux_id_id = jeux_id_id;
        this.note = note;
        this.avis = avis;
    }

    public review(int id, int jeux_id_id, double note, String avis) {
        this.id = id;
        this.jeux_id_id = jeux_id_id;
        this.note = note;
        this.avis = avis;
    }
  
}
