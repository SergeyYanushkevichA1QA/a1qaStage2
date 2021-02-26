package by.a1qa.quest.task4;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.JDBCconnection;
import by.a1qa.models.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Task4 {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static List<Integer> runAndReturnAnswer() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(environment.getValue("/testdata/asksForDB/ask4").toString());
        List<Integer> counts = new ArrayList<>();
        while (rs.next()) {
            int count = rs.getInt(1);
            counts.add(count);
        }
        conn.close();
        return counts;
    }

    public static void logAnswer(List<Integer> data) {
        for(int i = 0; i < data.size(); i++) {
            AqualityServices.getLogger().info(data.get(i).toString());
        }
    }
}
