package by.a1qa.entity;

import aquality.selenium.browser.Browser;
import by.a1qa.service.ConfProperties;
import by.a1qa.service.CookieManager;


public class MainPage {
    public Browser browser;
    public CookieManager cookieManager;

    public MainPage(Browser browser) {
        browser.maximize();
        browser.goTo(ConfProperties.getProperty("mainpage"));
        browser.waitForPageToLoad();
        cookieManager = new CookieManager(browser);
        this.browser = browser;
    }

    public String getCurrentUrl() {

        return browser.getCurrentUrl();
    }

    public void driverDown() {
        browser.getDriver().quit();
    }
}
