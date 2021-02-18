import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.GamePage;
import by.a1qa.entity.StartPage;
import by.a1qa.service.StringGenerator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UItest {
    private final Browser browser = AqualityServices.getBrowser();
    public static GamePage gamePage;
    public static StartPage startPage;
    private final ISettingsFile environment = new JsonSettingsFile("settings.json");
    private final String url = environment.getValue("/testdata/url").toString();
    private final String gamepageURL = environment.getValue("/testdata/gamepageURL").toString();

    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        startPage = new StartPage(browser);
        Assert.assertEquals(startPage.getURL(), url);
        startPage.clickStartButton();
        gamePage = new GamePage(browser);
        Assert.assertEquals(gamePage.getURL(), gamepageURL);
    }

    @Test
    public void TC1() {
        gamePage.setPassword(StringGenerator.getAlphaNumericString());
        gamePage.setEmail(gamePage.getPassword().charAt(0) + StringGenerator.getAlphaNumericString());
        gamePage.setDomain(StringGenerator.getAlphaNumericString());
        gamePage.selectRandomDropDownDomain();
        gamePage.acceptTermsAndConditions();
        gamePage.clickNextButton();
        gamePage.checkThreeInterests();
        gamePage.uploadImage();
        gamePage.clickNextButtonAt3rd();
        Assert.assertTrue(gamePage.checkThirdCard());
    }

    @Test
    public void TC2() {
        AqualityServices.getLogger().info("Hiding help form");
        gamePage.getHelpForm().hide();
        Assert.assertTrue(gamePage.getHelpForm().isHidden());
    }

    @Test
    public void TC3() {
        AqualityServices.getLogger().info("Accepting cookies");
        gamePage.getCookiesForm().acceptCookies();
        AqualityServices.getLogger().info("Checking cookies form is it displayed");
        Assert.assertFalse(gamePage.getCookiesForm().state().isDisplayed());
    }

    @Test
    public void TC4() {
        AqualityServices.getLogger().info("Checking timer");
        Assert.assertEquals(gamePage.getTimerValue(), environment.getValue("/testdata/timer").toString());
    }

    @AfterClass
    public static void tearDown() {

    }
}
