package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_connect {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projectpbo";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
