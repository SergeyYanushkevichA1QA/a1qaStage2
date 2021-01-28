import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.entity.MainPage;
import by.a1qa.service.ConfProperties;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IFrameTest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;

    @BeforeClass
    public void setup() {
        mainPage = new MainPage(browser);
    }

    @Test
    public void iFrameTest() {
        AqualityServices.getLogger().info("Checking browser url");
        Assert.assertEquals(mainPage.getCurrentUrl(), ConfProperties.getProperty("mainpage"));
        mainPage.clearInput();
        mainPage.inputRandomText();
        AqualityServices.getLogger().info("Checking input text");
        Assert.assertEquals(mainPage.getInputTxt(), mainPage.getRandomString());
        mainPage.highlightText();
        mainPage.clickBoldButton();
        AqualityServices.getLogger().info("Checking if text is bold");
        Assert.assertEquals(mainPage.isBoldTxt(), true);
    }

    @AfterClass
    public static void tearDown() {
        mainPage.driverDown();
    }
}
