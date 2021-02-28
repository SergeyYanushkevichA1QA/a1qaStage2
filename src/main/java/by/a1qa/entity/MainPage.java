package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import by.a1qa.service.CookieManager;
import org.openqa.selenium.By;


public class MainPage extends Form {
    private CookieManager cookieManager = new CookieManager();
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");
    private final ILabel lblTextOnPage = elementFactory.getLabel(By.xpath("//div[contains(@title, '" +
            testdata.getValue("/testdata/title").toString() + "')]"), "Text On Page");


    public MainPage() {
        super(By.xpath("/html"), "Main page");
    }

    public CookieManager getCookieManager() {
        return cookieManager;
    }

}
