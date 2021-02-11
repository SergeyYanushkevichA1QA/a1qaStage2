import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlertTest {
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
    public void alertTest() {
        mainPage.clickJsAlertButton();
        Assert.assertEquals(mainPage.getAlertText(), environment.getValue("/testdata/alertsText/jsAlertText").toString());
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), environment.getValue("/testdata/alertsText/jsAlertResultText").toString());
        mainPage.clickJsConfirmButton();
        Assert.assertEquals(mainPage.getAlertText(), environment.getValue("/testdata/alertsText/jsConfirmText").toString());
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), environment.getValue("/testdata/alertsText/jsConfirmResultText").toString());
        mainPage.clickJsPromptButton();
        Assert.assertEquals(mainPage.getAlertText(), environment.getValue("/testdata/alertsText/jsPromptText").toString());
        mainPage.typeRandomTextInPrompt();
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), "You entered: " + mainPage.getRandomText());
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
