package by.a1qa.entity;

import aquality.selenium.browser.Browser;


public class MainPage {
    public Browser browser;

    public MainPage(Browser browser) {
        this.browser = browser;
    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

}
