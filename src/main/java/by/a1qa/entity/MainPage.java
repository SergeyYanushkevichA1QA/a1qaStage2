package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.IElement;


public class MainPage {
    public Browser browser;
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public MainPage(Browser browser) {
        this.browser = browser;
    }

    public void basicAuth() {
        AqualityServices.getLogger().info("Login with username " + environment.getValue("/testdata/username").toString() + " and password "
                + environment.getValue("/testdata/password").toString());
        browser.goTo(environment.getValue("/testdata/https").toString() + environment.getValue("/testdata/username").toString() + ":"
                + environment.getValue("/testdata/password").toString() + "@"
                    + environment.getValue("/testdata/url").toString().replace(environment.getValue("/testdata/https").toString(), ""));

    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

}
