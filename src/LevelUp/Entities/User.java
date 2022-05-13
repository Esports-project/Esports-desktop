package Esprit.Entities;

import java.util.Date;

public class User {

    private int id;
    private int banned;
    private int roles;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private String password;

    public User() {

    }

    public User(int id, int banned, int roles, String username, String email, String name, String lastname, String password) {
        this.id = id;
        this.banned = banned;
        this.roles = roles;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
    }

    public User(int id, String username, String email, String name, String lastname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public User(int id, int roles, String username, String email, String name, String lastname, int banned) {
        this.id = id;
        this.roles = roles;
        this.banned = banned;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public int getBanned() {
        return banned;
    }

    public int getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
