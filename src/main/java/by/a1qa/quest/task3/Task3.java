package by.a1qa.quest.task3;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.JDBCconnection;
import by.a1qa.models.Projects;
import by.a1qa.models.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static List<Test> runAndReturnAnswer() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(environment.getValue("/testdata/asksForDB/ask3").toString());
        List<Test> tests = new ArrayList<>();
        Projects projects = new Projects();
        while (rs.next()) {
            Test test = new Test();
            String projectName = rs.getString(1);
            String testName = rs.getString(2);
            String dateTime = rs.getString(3);
            test.setProject(projects.getProject(projectName));
            test.setName(testName);
            test.setStartTime(dateTime);
            tests.add(test);
        }
        conn.close();
        return tests;
    }

    public static void logAnswer(List<Test> data) {
        for(int i = 0; i < data.size(); i++) {
            AqualityServices.getLogger().info(data.get(i).getProject() + " " + data.get(i).getName() + " " + data.get(i).getStartTime());
        }
    }
}
