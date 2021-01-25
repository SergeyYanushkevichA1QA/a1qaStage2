package by.a1qa.quest.task2;

import by.a1qa.entity.JDBCconnection;
import by.a1qa.quest.task1.Task1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task2 {

    private static final Logger LOGGER = LogManager.getLogger(Task2.class);

    public static void runTask() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        LOGGER.info("task 2 DB");
        String ask = "SELECT DISTINCT project.name, COUNT(*) FROM project " +
                "JOIN test ON project.id = test.project_id GROUP BY project.name";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ask);
        while(rs.next())
        {
            LOGGER.info(rs.getString("project.name") + " " + rs.getString("COUNT(*)"));
        }
    }
}
