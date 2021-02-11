package by.a1qa.entity;

import aquality.selenium.browser.Browser;
import by.a1qa.service.CookieManager;


public class MainPage extends CookieManager {
    public Browser browser;

    public MainPage(Browser browser) {
        super(browser);
        this.browser = browser;
    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

}
