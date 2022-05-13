package Esprit.Entities;

public class Equipes {

    private Integer id;

    private String nom;

    public Equipes(Integer id){
        this.id = id;
    }

    public Equipes(String name){
        this.nom = name;
    }

    public Equipes(Integer id, String name){
        this.id =id;
        this.nom = name;
    }

    public Equipes(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
