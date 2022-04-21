

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;



/**
 *
 * @author admin
 */
public class Classement {

    //var
    private int id;  
    private int rang;
    private int equipe_id;
    private int evenement_id;
   ;

    //Constructor
    public Classement() {

    }
    //..

    public Classement(int id,int rang,int equipe_id,int evenement_id) {
        this.id = id;
        this.rang = rang;
        this.equipe_id = equipe_id;
        this.evenement_id = evenement_id;
    }

    //..
    public Classement(int rang,int equipe_id,int evenement_id) {
        this.rang = rang;
        this.equipe_id = equipe_id;
        this.evenement_id = evenement_id;
    }

    //GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

   
  


    @Override
    public String toString() {
        return "Classement{" + "id=" + id + ", Rang=" + rang + ", equipe_id=" + equipe_id + ", evenement_id=" + evenement_id + '}';
    }

}