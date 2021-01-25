import by.a1qa.entity.Driver;
import by.a1qa.entity.MainPage;
import by.a1qa.service.ConfProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IFrameTest {
    private static final Logger LOGGER = LogManager.getLogger(IFrameTest.class);
    private Driver automation = Driver.getInstance();
    private WebDriver driver = automation.openBrowser("browser");
    public static MainPage mainPage;

    @BeforeClass
    public void setup() {
        mainPage = new MainPage(driver);
    }

    @Test
    public void iFrameTest() {
        LOGGER.info("Starting Iframe test");
        LOGGER.info("Checking url's");
        Assert.assertEquals(mainPage.getCurrentUrl(), ConfProperties.getProperty("mainpage"));
        LOGGER.info("Clear input");
        mainPage.clearInput();
        LOGGER.info("Input random text");
        mainPage.inputRandomText();
        LOGGER.info("Checking random text");
        Assert.assertEquals(mainPage.getInputTxt(), mainPage.getRandomString());
        LOGGER.info("Highlight whole text");
        mainPage.highlightText();
        LOGGER.info("Doing text BOLD");
        mainPage.clickBoldButton();
        LOGGER.info("Checking if text is bold");
        Assert.assertEquals(mainPage.isBoldTxt(), true);
    }

    @AfterClass
    public static void tearDown() {
        mainPage.driverDown();
    }
}
