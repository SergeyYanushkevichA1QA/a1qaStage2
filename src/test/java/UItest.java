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
    private final ISettingsFile testdata = new JsonSettingsFile("testdata.json");
    private final String url = environment.getValue("/url").toString();
    private final String gamepageURL = environment.getValue("/gamepageURL").toString();

    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        startPage = new StartPage();
        Assert.assertTrue(startPage.isPage());
        startPage.clickStartButton();
        gamePage = new GamePage();
        Assert.assertTrue(gamePage.isPage());
    }

    @Test
    public void TC1() {
        gamePage.getLoginForm().setPassword(StringGenerator.getAlphaNumericString());
        gamePage.getLoginForm().setEmail(gamePage.getLoginForm().getPassword().charAt(0) + StringGenerator.getAlphaNumericString());
        gamePage.getLoginForm().setDomain(StringGenerator.getAlphaNumericString());
        gamePage.getLoginForm().selectRandomDropDownDomain();
        gamePage.getLoginForm().acceptTermsAndConditions();
        gamePage.getLoginForm().clickNextButton();
        int numberOfInterests = 3;
        gamePage.getProfileForm().checkInterests(numberOfInterests);
        gamePage.getProfileForm().uploadImage();
        gamePage.getProfileForm().clickNextButton();
        int pageNumber = 3;
        System.out.println(gamePage.getCurrentPageNumber());
        Assert.assertEquals(gamePage.getCurrentPageNumber(), pageNumber);
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
        Assert.assertEquals(gamePage.getTimerValue(), testdata.getValue("/testdata/timer").toString());
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
