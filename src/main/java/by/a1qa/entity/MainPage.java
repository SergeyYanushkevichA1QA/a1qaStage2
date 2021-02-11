package by.a1qa.entity;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import by.a1qa.service.StringGenerator;
import org.openqa.selenium.By;


public class MainPage {
    public Browser browser;
    private static String randomString = StringGenerator.getAlphaNumericString();
    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private IButton jsAllertButton = elementFactory.getButton(By.xpath("//button[@onclick='jsAlert()']"), "JS alert button");
    private IButton jsConfirmButton = elementFactory.getButton(By.xpath("//button[@onclick='jsConfirm()']"), "JS confirm button");
    private IButton jsPromptButton = elementFactory.getButton(By.xpath("//button[@onclick='jsPrompt()']"), "JS prompt button");
    private ITextBox resultText = elementFactory.getTextBox(By.xpath("//p[@id='result']"), "Result Text");

    public MainPage(Browser browser) {
        this.browser = browser;
    }



    public void clickJsAlertButton() {
        jsAllertButton.click();
    }

    public void clickJsConfirmButton() {
        jsConfirmButton.click();
    }

    public void clickJsPromptButton() {
        jsPromptButton.click();
    }

    public void AcceptAlert() {
        browser.handleAlert(AlertActions.ACCEPT);
    }

    public String getAlertText() {
        return browser.getDriver().switchTo().alert().getText();
    }

    public String getResultText() {
        return resultText.getText();
    }

    public void typeRandomTextInPrompt() {
       browser.getDriver().switchTo().alert().sendKeys(randomString);
    }

    public String getRandomText() {
        return randomString;
    }

}
