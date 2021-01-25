package by.a1qa.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCconnection {

    private static final Logger LOGGER = LogManager.getLogger(JDBCconnection.class);

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/union_reporting";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            LOGGER.info("Connected to union_reporting DB");
            return conn;
        } catch (Exception ex) {
            LOGGER.info("Connection failed...");
            LOGGER.info(ex);
            return null;
        }
    }
}
