package by.a1qa.quest.task4;

import by.a1qa.entity.JDBCconnection;
import by.a1qa.quest.task1.Task1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task4 {

    private static final Logger LOGGER = LogManager.getLogger(Task4.class);

    public static void runTask() throws SQLException {
        Connection conn = JDBCconnection.getConnection();
        LOGGER.info("task 4 DB");
        String ask = "SELECT COUNT(*) FROM test WHERE browser = 'chrome' " +
                "UNION SELECT COUNT(*) FROM test WHERE browser = 'firefox' ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ask);
        while(rs.next())
        {
            LOGGER.info(rs.getString("COUNT(*)"));
        }
    }
}
