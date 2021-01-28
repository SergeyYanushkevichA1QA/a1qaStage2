package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.openqa.selenium.Cookie;


public class CookieManager {
    public Browser browser;

    public CookieManager(Browser browser) {
        this.browser = browser;
    }


    public void addCookieByKeyandName(String key, String name) {
        AqualityServices.getLogger().info("Adding cookie by key and name");
        browser.getDriver().manage().addCookie(new Cookie(key, name));
        AqualityServices.getLogger().info("Added cookie with key = " + key + " " + "Name = " + name);
    }

    public Cookie getCookieByName(String name) {
        return browser.getDriver().manage().getCookieNamed(name);
    }

    public void getAllCokies() {
        AqualityServices.getLogger().info("getting all cookie");
        if(browser.getDriver().manage().getCookies().isEmpty()) {
            AqualityServices.getLogger().info("All cookies were deleted");
        } else {
            for(Cookie element:browser.getDriver().manage().getCookies()) {
                 AqualityServices.getLogger().info(String.valueOf(element.toJson()));
            }
        }
    }

    public boolean isCookiesEmpty() {
        boolean flag = false;
        if(browser.getDriver().manage().getCookies().isEmpty()) {
            flag = true;
        }
        return flag;
    }

    public boolean isDeletedCookie(String key) {
        AqualityServices.getLogger().info("Checking deleted cookie");
        boolean flag = true;
        if(getCookieByName(key) != null) {
            flag = false;
        }
        return flag;
    }

    public void deleteAllCookies() {
        AqualityServices.getLogger().info("deleting all cookie");
        browser.getDriver().manage().deleteAllCookies();
    }

    public void deleteCookieByKey(String key) {
        AqualityServices.getLogger().info("deleting cookie by key= " + key);
        browser.getDriver().manage().deleteCookieNamed(key);

    }

    public void changeCookieValue(String key, String newValue) {
        AqualityServices.getLogger().info("Changing value of cookie with key= " + key);
        browser.getDriver().manage().deleteCookieNamed(key);
        browser.getDriver().manage().addCookie(new Cookie(key, newValue));
    }



}
