package Esprit.Entities;


import java.sql.Date;

public class Blog {

    private Integer id;

    private String titre;

    private String contenu;

    private String image;

    private Date post_date;



    //Complete constructor
    public Blog(Integer id) {
        this.id = id;
    }

    //Complete constructor
    public Blog(Integer id, String titre, String contenu, String image, Date post_date) {
        this.id = id;
        this.contenu = contenu;
        this.titre = titre;
        this.image = image;
        this.post_date = post_date;
    }
    //Constructor without ID
    public Blog(  String titre, String contenu, String image, Date post_date){
        this.titre = titre;
        this.contenu = contenu;
        this.image = image;
        this.post_date = post_date;
    }



    public Blog(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



}
