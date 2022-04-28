
package LevelUp.Services;

import LevelUp.Connection.MyConnection;
import LevelUp.Entities.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceMessage {

    private Connection cnx;
    private PreparedStatement ste;
    Statement st;
    ResultSet rs;

    public ServiceMessage() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /* ----------------- Create ----------------- */
    public void addMessage(Messages m) {
        try {
            String query = "INSERT INTO message (sender_id,receiver_id,contenu,date,seen) VALUES(?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            pst.setInt(1, 0);
            pst.setInt(2, 0);
            pst.setString(3, m.getMessage());
            pst.setDate(4, date);
            pst.setInt(5,0);

            pst.executeUpdate();
            System.out.println(m);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /* ----------------- Read ----------------- */
    public List<Messages> messagesList() {
        List<Messages> myList = new ArrayList<Messages>();
        String query = "SELECT * from message";
        try {
            Statement st;
            st = MyConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Messages messages = new Messages();
                messages.setId(rs.getInt(1));
                messages.setSender_id(rs.getInt(2));
                messages.setReceiver_id(rs.getInt(3));
                messages.setMessage(rs.getString(4));
                messages.setDate(rs.getDate(5));
                messages.setSeen(rs.getInt(6));

                myList.add(messages);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    /* ----------------- Update ----------------- */
    public void editMessage(Messages m) throws SQLException {
        String query = "UPDATE message SET sender_id=?, receiver_id=? ,contenu=? , seen=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            ste.setInt(5, m.getId());
            ste.setInt(1, m.getSender_id());
            ste.setInt(2, m.getReceiver_id());
            ste.setString(3, m.getMessage());
            ste.setInt(4, m.getSeen());

            ste.executeUpdate();
            System.out.println("Message Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Message non Modfié !");
        }
    }


    /* ----------------- Delete ----------------- */
    public void deleteMessage(Messages m) {
        String query = "DELETE FROM message WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, m.getId());
            ste.executeUpdate();
            System.out.println("Message Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
