package Esprit.Services;



import Esprit.Connection.MyConnection;
import Esprit.Entities.Evenement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEvenements {
    private Connection cnx;
    private PreparedStatement ste;

    public ServiceEvenements() {
        cnx = MyConnection.getInstance().getConnection();
    }


    public void createEvenement(Evenement E) {
        try {
            String req = "INSERT INTO `evenement`(`nom`, `organisateur`,  `description`, `image`,`date`) VALUES (?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, E.getNom());
            st.setString(2, E.getOrganisateur());
            st.setString(3, E.getDescription());
            st.setString(4, E.getImage());
            st.setDate(5, E.getDate());

            st.executeUpdate();
            System.out.println("evenement ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }

    public void modifyEvenement(Evenement e) {
        String sql = "UPDATE evenement SET nom=?, organisateur=?, description=?, image=?, date=? WHERE id=?";
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, e.getNom());
            ste.setString(2, e.getOrganisateur());
            ste.setString(3, e.getDescription());
            ste.setString(4, e.getImage());
            ste.setDate(5, e.getDate());
            ste.setInt(6, e.getId());

            ste.executeUpdate();
            System.out.println("modification avec succees !");
            System.out.println(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEvenement(Evenement e) {
        try {

            if (e.getId() != 0) {
                String sql = "delete from evenement WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, e.getId());
                st.executeUpdate();
                System.out.println("deleted !");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Evenement> readEvenements() {
        ArrayList<Evenement> events = new ArrayList();
        String req = "SELECT * FROM evenement";
        try {
            Statement st;

            st= MyConnection.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                events.add(
                        new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6)));

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }

    public Evenement readEvenementById(int id) {

        try {
            String req = "SELECT * FROM evenement where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return (new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("organisateur"), rs.getString("description"), rs.getString("image"), rs.getDate("date")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public  String getNameFromID(Integer id){
        String name = "";
        String query = "SELECT nom from evenement WHERE id=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                name = (rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return name;
    }
    public  int getIdFromName(String name){
        int i = 0;
        String query = "SELECT id from evenement WHERE nom=?";
        try {
            ste = cnx.prepareStatement(query);
            ste.setString(1, name);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                i = (rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }

    public String[] getWeatherData() throws MalformedURLException, IOException {

        try
        {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Tunis&appid=1370ad4dc8c21ab9229ba0ba15eef487&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            ObjectMapper mapper = new ObjectMapper();
            String[] sa = new String[7];
            JsonNode jsonNode = mapper.readTree(connection.getInputStream());
            String temperature = jsonNode.get("main").get("temp").toString();
            sa[0] = temperature;
            String humidity = jsonNode.get("main").get("humidity").toString();
            sa[1] = humidity;
            String description = jsonNode.get("weather").get(0).get("description").toString();
            sa[2] = description;
            String pressure = jsonNode.get("main").get("pressure").toString();
            sa[3] = pressure;
            String main = jsonNode.get("weather").get(0).get("main").toString();
            sa[4] = main;
            sa[5] = jsonNode.get("weather").get(0).get("icon").toString().replace("\"", "").replace("\"", "");

            return sa;
        } catch (Exception ex) {
            Logger.getLogger(ServiceEvenements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
