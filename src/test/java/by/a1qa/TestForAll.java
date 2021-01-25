package by.a1qa;

import by.a1qa.quest.task1.Task1;
import by.a1qa.quest.task2.Task2;
import by.a1qa.quest.task3.Task3;
import by.a1qa.quest.task4.Task4;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestForAll {


    @Test
    public void testForAll() throws SQLException {
        Task1.runTask();
        Task2.runTask();
        Task3.runTask();
        Task4.runTask();
    }

}
