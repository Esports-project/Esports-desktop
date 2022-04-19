/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 *
 * @author dorra
 */
public class MyDB {

    final String url = "jdbc:mysql://localhost:3306/levelup";
    final String user = "root";
    final String password = "";

    public static MyDB instance;
    Connection connection;

    private MyDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Problème de connexion");
            System.out.println(ex.getMessage());
        }
    }

   
    //Getters
    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCnx() {
        return connection;
    }
}
