package by.a1qa.quest.task3;

import by.a1qa.entity.JDBCconnection;
import by.a1qa.quest.task1.Task1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task3 {

    private static final Logger LOGGER = LogManager.getLogger(Task3.class);

    public static void runTask() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        LOGGER.info("task 3 DB");
        String ask = "SELECT project.name, test.name, test.start_time FROM project" +
                " JOIN test ON project.id = test.project_id WHERE test.start_time >='2015-11-7' " +
                "GROUP BY test.name ORDER by project.name ASC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ask);
        while(rs.next())
        {
            LOGGER.info(rs.getString("project.name") + " " +rs.getString("test.name") + " " +
                    rs.getString("test.start_time"));
        }
    }
}
