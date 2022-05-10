/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevselim;

import java.util.Date;

/**
 *
 * @author sallo
 */
public class Blogs {
    private Integer id;
    private String titre;
    private String contenu;
    private Date post_date;

    public Blogs(Integer id, String titre, String contenu, Date post_date) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.post_date = post_date;
    }

    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return post_date;
    }
    
}
