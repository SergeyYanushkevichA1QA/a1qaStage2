package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;


public class CookieManager extends Form {

    public CookieManager() {
        super(By.className("cookies"), "Cookies");
    }

    public void addCookieByKeyandName(String key, String name) {
        AqualityServices.getLogger().info("Adding cookie by key and name");
        AqualityServices.getBrowser().getDriver().manage().addCookie(new Cookie(key, name));
        AqualityServices.getLogger().info("Added cookie with key = " + key + " " + "Name = " + name);
    }

    public Cookie getCookieByName(String name) {
        return AqualityServices.getBrowser().getDriver().manage().getCookieNamed(name);
    }

    public void getAllCokies() {
        AqualityServices.getLogger().info("getting all cookie");
        if(AqualityServices.getBrowser().getDriver().manage().getCookies().isEmpty()) {
            AqualityServices.getLogger().info("All cookies were deleted");
        } else {
            for(Cookie element:AqualityServices.getBrowser().getDriver().manage().getCookies()) {
                 AqualityServices.getLogger().info(String.valueOf(element.toJson()));
            }
        }
    }

    public boolean isCookiesEmpty() {
        boolean flag = false;
        if(AqualityServices.getBrowser().getDriver().manage().getCookies().isEmpty()) {
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
        AqualityServices.getBrowser().getDriver().manage().deleteAllCookies();
    }

    public void deleteCookieByKey(String key) {
        AqualityServices.getLogger().info("deleting cookie by key= " + key);
        AqualityServices.getBrowser().getDriver().manage().deleteCookieNamed(key);

    }

    public void changeCookieValue(String key, String newValue) {
        AqualityServices.getLogger().info("Changing value of cookie with key= " + key);
        AqualityServices.getBrowser().getDriver().manage().deleteCookieNamed(key);
        AqualityServices.getBrowser().getDriver().manage().addCookie(new Cookie(key, newValue));
    }


}
