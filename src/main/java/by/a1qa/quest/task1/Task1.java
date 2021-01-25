package by.a1qa.quest.task1;

import by.a1qa.entity.JDBCconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task1 {

    private static final Logger LOGGER = LogManager.getLogger(Task1.class);

    public static void runTask() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        LOGGER.info("task1 DB");
        String ask = "SELECT project.name, test.name, (test.end_time - test.start_time) FROM project " +
                "JOIN test ON project.id = test.project_id GROUP BY test.name ORDER by project.name ASC ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ask);
        while(rs.next())
        {
            LOGGER.info(rs.getString("project.name") + " " + rs.getString("test.name") + " " +
                    rs.getString("(test.end_time - test.start_time)"));
        }
    }
}
