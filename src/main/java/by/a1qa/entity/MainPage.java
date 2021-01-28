package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import by.a1qa.service.ConfProperties;
import by.a1qa.service.StringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    public Browser browser;
    private static String randomString = StringGenerator.getAlphaNumericString();
    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private IButton highlighTxtButton = elementFactory.getButton(By.xpath("//div[contains(@role, 'button') and contains(text(), 'p')]"), "highligh Text Button");
    private IButton boldButton = elementFactory.getButton(By.xpath("//button[@title='Bold']"), "Bold button");
    private IButton boldButtonTxt = elementFactory.getButton(By.xpath("//div[contains(@role, 'button') and contains(text(), 'strong')]"), "bold button beneath frame");
    private ITextBox inputField = elementFactory.getTextBox(By.xpath("//body[@id='tinymce']/p"), "Input Field");


    public MainPage(Browser browser) {
        browser.maximize();
        browser.goTo(ConfProperties.getProperty("mainpage"));
        browser.waitForPageToLoad();
        this.browser = browser;
    }


    @FindBy(css = "#tox-edit-area>iframe")
    private WebElement iframe;


    private void switchToFrame() {
        AqualityServices.getLogger().info("Switching to frame");
        browser.getDriver().switchTo().frame("mce_0_ifr");
    }

    private void leaveFrame() {
        AqualityServices.getLogger().info("Leaving frame");
        browser.getDriver().switchTo().defaultContent();
    }

    public void clearInput() {
        switchToFrame();
        AqualityServices.getLogger().info("Clear input in frame");
        inputField.clearAndType("");
    }

    public void clickBoldButton() {
        boldButton.click();
    }

    public void highlightText() {
        leaveFrame();
        highlighTxtButton.click();
    }
    public void inputRandomText() {
        inputField.type(randomString);
    }

    public String getInputTxt() {
        return inputField.getText();
    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

    public static String getRandomString() {
        return randomString;
    }

    public boolean isBoldTxt() {
        boolean flag = false;
        if(boldButtonTxt.getElement().isDisplayed())
        {
            flag = true;
        }
        return flag;
    }

    public void driverDown() {
        browser.getDriver().quit();
    }
}
