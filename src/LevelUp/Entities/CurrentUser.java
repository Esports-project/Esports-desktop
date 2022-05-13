package Esprit.Entities;

public class CurrentUser {
    private boolean set;
    private int id;
    private String fullname;
    private String username;
    private int role;

    public boolean getSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private CurrentUser(){
        setSet(false);
    }

    public void setUserInfo(int id, String fullname, String username, int role){
        setSet(true);
        setId(id);
        setFullname(fullname);
        setUsername(username);
        setRole(role);
    }

    public void removeUserInfo(){
        setSet(false);
    }

    private static final CurrentUser instance = new CurrentUser();
    public static CurrentUser get(){
        return instance;
    }
}
