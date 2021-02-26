package by.a1qa.quest.task1;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.JDBCconnection;
import by.a1qa.models.Projects;
import by.a1qa.models.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static List<Test> runAndReturnAnswer() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(environment.getValue("/testdata/asksForDB/ask1").toString());
        List<Test> tests = new ArrayList<>();
        Projects projects = new Projects();
        while (rs.next()) {
            Test test = new Test();
            String projectName = rs.getString(1);
            String testName = rs.getString(2);
            int time = rs.getInt(3);
            test.setProject(projects.getProject(projectName));
            test.setName(testName);
            test.setMinTime(time);
            tests.add(test);
        }
        conn.close();
        return tests;
    }

    public static void logAnswer(List<Test> data) {
        for(int i = 0; i < data.size(); i++) {
            AqualityServices.getLogger().info(data.get(i).getProject() + " " + data.get(i).getName() + " " + data.get(i).getMinTime());
        }
    }
}
