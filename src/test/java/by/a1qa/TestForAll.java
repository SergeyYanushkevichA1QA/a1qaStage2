package by.a1qa;

import aquality.selenium.browser.AqualityServices;
import by.a1qa.quest.task1.Task1;
import by.a1qa.quest.task2.Task2;
import by.a1qa.quest.task3.Task3;
import by.a1qa.quest.task4.Task4;
import org.testng.annotations.Test;

import java.sql.SQLException;


public class TestForAll {



    @Test
    public void ask1Test() throws SQLException {
        AqualityServices.getLogger().info("Task1 DB");
        Task1.logAnswer(Task1.runAndReturnAnswer());
    }

    @Test
    public void ask2Test() throws SQLException {
        AqualityServices.getLogger().info("Task2 DB");
        Task2.logAnswer(Task2.runAndReturnAnswer());
    }

    @Test
    public void ask3Test() throws SQLException {
        AqualityServices.getLogger().info("Task3 DB");
        Task3.logAnswer(Task3.runAndReturnAnswer());
    }


    @Test
    public void ask4Test() throws SQLException {
        AqualityServices.getLogger().info("Task4 DB");
        Task4.logAnswer(Task4.runAndReturnAnswer());
    }


}
