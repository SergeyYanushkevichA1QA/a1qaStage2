package by.a1qa;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import by.a1qa.service.getData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class BasicAuthTest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static String url = environment.getValue("/testdata/url").toString();

    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        mainPage = new MainPage(browser);
    }

    @Test
    public void BasicAuthTest() throws IOException {
        Assert.assertEquals(mainPage.getCurrentUrl(), url);
        mainPage.basicAuth();
        getData.getUserAndAuth();
        Assert.assertEquals(getData.getUsername(), environment.getValue("/testdata/username"));
        Assert.assertTrue(getData.getIsAuth());

       
    }

    @AfterClass
    public void tearDown() {
      //  browser.quit();
    }
}
