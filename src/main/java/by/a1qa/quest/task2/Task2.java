package by.a1qa.quest.task2;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.JDBCconnection;
import by.a1qa.models.Project;
import by.a1qa.models.Projects;
import by.a1qa.models.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static List<Project> runAndReturnAnswer() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(environment.getValue("/testdata/asksForDB/ask2").toString());
        List<Project> projects = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString(1);
            int count = rs.getInt(2);
            Project project = new Project(name);
            project.setTestCount(count);
            projects.add(project);
        }
        conn.close();
        return projects;
    }

    public static void logAnswer(List<Project> data) {
        for(int i = 0; i < data.size(); i++) {
            AqualityServices.getLogger().info(data.get(i).getName() + " " + data.get(i).getTestCount());
        }
    }
}
