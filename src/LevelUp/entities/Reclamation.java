package LevelUp.Entities;


import java.util.Date;

public class Reclamation {
    private Integer id;

    private Integer user_id;

    private String subject;

    private String email;

    private String description;

    private Date date;

    private Integer status;

    private Integer category_id;

    //Complete constructor
    public Reclamation(Integer id) {
        this.id = id;
    }

//Complete constructor
    public Reclamation(Integer id, Integer user_id, String subject, String email, String description, Date date, Integer status, Integer category_id) {
        this.id = id;
        this.user_id = user_id;
        this.subject = subject;
        this.email = email;
        this.description = description;
        this.date = date;
        this.status = status;
        this.category_id = category_id;
    }
//Constructor without ID
    public Reclamation( Integer user_id, String subject, String email, String description, Date date, Integer status, Integer category_id){
        this.user_id = user_id;
        this.subject = subject;
        this.email = email;
        this.description = description;
        this.date = date;
        this.status = status;
        this.category_id = category_id;
    }

    //Constructor without Date
    public Reclamation( Integer id, Integer user_id, String subject, String email, String description,  Integer status, Integer category_id){
        this.id = id;
        this.user_id = user_id;
        this.subject = subject;
        this.email = email;
        this.description = description;
        this.status = status;
        this.category_id = category_id;
    }

    public Reclamation(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "reclamation{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", subject=" + subject +
                ", email=" + email +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", category_id=" + category_id +
                '}';
    }

}
