import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.entity.MainPage;
import by.a1qa.service.ConfProperties;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CookieTest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;

    @BeforeClass
    public void setup() {
        mainPage = new MainPage(browser);
    }

    @Test
    public void cookieTest() {
        Assert.assertEquals(mainPage.getCurrentUrl(), ConfProperties.getProperty("mainpage"));
        mainPage.cookieManager.addCookieByKeyandName("example_key_1","example_value_1");
        mainPage.cookieManager.addCookieByKeyandName("example_key_2","example_value_2");
        mainPage.cookieManager.addCookieByKeyandName("example_key_3","example_value_3");
        mainPage.cookieManager.getAllCokies();
        mainPage.cookieManager.deleteCookieByKey("example_key_1");
        Assert.assertTrue(mainPage.cookieManager.isDeletedCookie("example_key_1"), "Cookie was deleted");
        mainPage.cookieManager.changeCookieValue("example_key_3",  "example_value_300");
        mainPage.cookieManager.getAllCokies();
        mainPage.cookieManager.deleteAllCookies();
        Assert.assertTrue(mainPage.cookieManager.isCookiesEmpty(), "Cookie is empty");
        mainPage.cookieManager.getAllCokies();
    }

    @AfterClass
    public static void tearDown() {
        mainPage.driverDown();
    }
}
