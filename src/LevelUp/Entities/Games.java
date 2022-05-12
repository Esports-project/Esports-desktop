package Esprit.Entities;

import java.util.Date;

public class Games {

    private Integer id;



    private String nom;

    private String description;



    //Complete constructor
    public Games(Integer id) {
        this.id = id;
    }

    //Complete constructor
    public Games(Integer id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;

    }
    //Constructor without ID
    public Games( String nom, String description){
        this.nom = nom;

        this.description = description;

    }



    public Games(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
