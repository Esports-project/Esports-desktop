package Esprit.Entities;

import java.util.Date;

public class User {
    private Integer id;

    private Integer departement_id;

    private Integer equipe_id;

    private String nom;

    private String prenom;

    private String email;

    private Integer phone;

    private Date date_join;

    private String password;

    private String username;

    private Integer banned;

    private String roles;

    public User(Integer id, String nom, String prenom, String email, Integer phone, Date date_join, String password, String username, Integer banned) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.date_join = date_join;
        this.password = password;
        this.username = username;
        this.banned = banned;
    }

    //No Id
    public User(String nom, String prenom, String email, Integer phone, Date date_join, String password, String username, Integer banned, String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.date_join = date_join;
        this.password = password;
        this.username = username;
        this.banned = banned;
        this.roles = roles;
    }

    public User(Integer id, String nom, String prenom, String email, Integer phone, String password, String username) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getDepartement_id() {
        return departement_id;
    }

    public Integer getEquipe_id() {
        return equipe_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPhone() {
        return phone;
    }

    public Date getDate_join() {
        return date_join;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getBanned() {
        return banned;
    }

    public String getRoles() {
        return roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartement_id(Integer departement_id) {
        this.departement_id = departement_id;
    }

    public void setEquipe_id(Integer equipe_id) {
        this.equipe_id = equipe_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setDate_join(Date date_join) {
        this.date_join = date_join;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBanned(Integer banned) {
        this.banned = banned;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", date_join='" + date_join + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", banned=" + banned +
                '}';
    }
}
