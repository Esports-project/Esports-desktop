package Esprit.entities;

import java.util.Date;

public class Messages {
    private int id;

    private int sender_id;

    private int receiver_id;

    private String message;

    private Date date;

    private int seen;


    public Messages(int sender_id, int receiver_id, String message, Date date, int seen) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.message = message;
        this.date = date;
        this.seen = seen;
    }

    public Messages(int id, int sender_id, int receiver_id, String message,  int seen) {
        this.id = id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.message = message;
        this.seen = seen;
    }

    public Messages(int id) {
        this.id = id;
    }

    public Messages(){}



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "message{" +
                "id=" + id +
                ", sender_id='" + sender_id + '\'' +
                ", receiver_id=" + receiver_id +
                ", message=" + message +
                ", seen='" + seen + '\'' +
                ", date=" + date +
                '}';
    }
}
