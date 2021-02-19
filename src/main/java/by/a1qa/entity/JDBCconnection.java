package by.a1qa.entity;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCconnection {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static Connection getConnection() {
        try {
            String url = environment.getValue("/testdata/url").toString();
            String username = environment.getValue("/testdata/username").toString();
            String password = environment.getValue("/testdata/password").toString();
            Class.forName(environment.getValue("/testdata/nameForInstance").toString()).getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception ex) {
            return null;
        }
    }
}
