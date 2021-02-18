package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;

public class StartPage {

    public Browser browser;

    private IElementFactory elementFactory = AqualityServices.getElementFactory();
    private IButton startButton = elementFactory.getButton(By.xpath("//a[@class='start__link']"), "start button");

    public StartPage(Browser browser) {
        this.browser = browser;
    }

    public void clickStartButton() {
        startButton.click();
    }

    public String getURL() {
        return browser.getCurrentUrl();
    }

}
