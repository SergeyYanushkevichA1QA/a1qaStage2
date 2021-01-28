import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.entity.MainPage;
import by.a1qa.service.ConfProperties;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlertTest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;

    @BeforeClass
    public void setup() {
        mainPage = new MainPage(browser);
    }

    @Test
    public void alertTest() {
        mainPage.clickJsAlertButton();
        Assert.assertEquals(mainPage.getAlertText(), ConfProperties.getProperty("jsAlertText"));
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), ConfProperties.getProperty("jsAlertResultText"));
        mainPage.clickJsConfirmButton();
        Assert.assertEquals(mainPage.getAlertText(), ConfProperties.getProperty("jsConfirmText"));
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), ConfProperties.getProperty("jsConfirmResultText"));
        mainPage.clickJsPromptButton();
        Assert.assertEquals(mainPage.getAlertText(), ConfProperties.getProperty("jsPromptText"));
        mainPage.typeRandomTextInPrompt();
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), "You entered: " + mainPage.getRandomText());
    }

    @AfterClass
    public static void tearDown() {
        mainPage.driverDown();
    }
}
