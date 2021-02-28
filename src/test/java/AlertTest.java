import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import by.a1qa.service.TestData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlertTest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static String url = environment.getValue("/url").toString();

    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        mainPage = new MainPage();
    }

    @Test
    public void alertTest() {
        mainPage.clickJsAlertButton();
        Assert.assertEquals(mainPage.getAlertText(), TestData.getJSAlertText());
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), TestData.getJSAlertResultText());
        mainPage.clickJsConfirmButton();
        Assert.assertEquals(mainPage.getAlertText(), TestData.getConfirmAlertText());
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), TestData.getConfirmAlertResultText());
        mainPage.clickJsPromptButton();
        Assert.assertEquals(mainPage.getAlertText(), TestData.getPromptAlertText());
        mainPage.typeRandomTextInPrompt();
        mainPage.AcceptAlert();
        Assert.assertEquals(mainPage.getResultText(), TestData.getPromptAlertResultText(mainPage.getRandomText()));
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
