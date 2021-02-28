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
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");
    private static String url = environment.getValue("/url").toString();

    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        mainPage = new MainPage();
    }

    @Test
    public void cookieTest() {
        AqualityServices.getLogger().info("Checking site form");
        Assert.assertEquals(browser.getDriver().getTitle(), testdata.getValue("/testdata/title").toString());
        mainPage.getCookieManager().addCookieByKeyandName(testdata.getValue("/testdata/keys/key1").toString(),
                testdata.getValue("/testdata/values/value1").toString());
        mainPage.getCookieManager().addCookieByKeyandName(testdata.getValue("/testdata/keys/key2").toString(),
                testdata.getValue("/testdata/values/value2").toString());
        mainPage.getCookieManager().addCookieByKeyandName(testdata.getValue("/testdata/keys/key3").toString(),
                testdata.getValue("/testdata/values/value3").toString());
        mainPage.getCookieManager().getAllCokies();
        mainPage.getCookieManager().deleteCookieByKey(testdata.getValue("/testdata/keys/key1").toString());
        Assert.assertTrue(mainPage.getCookieManager().isDeletedCookie(testdata.getValue("/testdata/keys/key1").toString()),
                "Cookie was deleted");
        mainPage.getCookieManager().changeCookieValue(testdata.getValue("/testdata/keys/key3").toString(),
                testdata.getValue("/testdata/values/newValue").toString());
        mainPage.getCookieManager().getAllCokies();
        mainPage.getCookieManager().deleteAllCookies();
        AqualityServices.getLogger().info("Checking deleted cookies");
        Assert.assertTrue(mainPage.getCookieManager().isCookiesEmpty(), "Cookie is empty");
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
