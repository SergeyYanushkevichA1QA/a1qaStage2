import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CookieTest {
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
    public void cookieTest() {
        Assert.assertEquals(mainPage.getCurrentUrl(), url);
        mainPage.addCookieByKeyandName(environment.getValue("/testdata/keys/key1").toString(),
                environment.getValue("/testdata/values/value1").toString());
        mainPage.addCookieByKeyandName(environment.getValue("/testdata/keys/key2").toString(),
                environment.getValue("/testdata/values/value2").toString());
        mainPage.addCookieByKeyandName(environment.getValue("/testdata/keys/key3").toString(),
                environment.getValue("/testdata/values/value3").toString());
        mainPage.getAllCokies();
        mainPage.deleteCookieByKey(environment.getValue("/testdata/keys/key1").toString());
        Assert.assertTrue(mainPage.isDeletedCookie(environment.getValue("/testdata/keys/key1").toString()),
                "Cookie was deleted");
        mainPage.changeCookieValue(environment.getValue("/testdata/keys/key3").toString(),
                environment.getValue("/testdata/values/newValue").toString());
        mainPage.getAllCokies();
        mainPage.deleteAllCookies();
        Assert.assertTrue(mainPage.isCookiesEmpty(), "Cookie is empty");
        mainPage.getAllCokies();
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
