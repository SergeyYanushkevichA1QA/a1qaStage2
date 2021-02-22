import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IFrameTest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;
    private final ISettingsFile environment = new JsonSettingsFile("settings.json");
    private final String url = environment.getValue("/testdata/url").toString();


    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        mainPage = new MainPage(browser);
    }

    @Test
    public void iFrameTest() {
        AqualityServices.getLogger().info("Checking browser url");
        Assert.assertEquals(mainPage.getCurrentUrl(), url);
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
    public void tearDown() {
        browser.quit();
    }
}
