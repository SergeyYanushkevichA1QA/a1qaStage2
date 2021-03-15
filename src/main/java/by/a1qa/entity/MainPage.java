package by.a1qa.entity;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.service.StringGenerator;
import org.openqa.selenium.By;


public class MainPage extends Form {
    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static final String btnLocator = "//button[@onclick='%s()']";
    private IButton jsAllertButton = getJsButton("jsAlert", "Alert");
    private IButton jsConfirmButton = getJsButton("jsConfirm", "Confirm");
    private IButton jsPromptButton = getJsButton("jsPrompt", "Prompt");
    private ITextBox resultText = elementFactory.getTextBox(By.xpath("//p[@id='result']"), "Result Text");

    public MainPage() {
        super(By.id("content"), "Main");
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
        AqualityServices.getBrowser().handleAlert(AlertActions.ACCEPT);
    }

    public String getAlertText() {
        return AqualityServices.getBrowser().getDriver().switchTo().alert().getText();
    }

    public String getResultText() {
        return resultText.getText();
    }

    public void typeRandomTextInPrompt(String randomString) {
        AqualityServices.getBrowser().getDriver().switchTo().alert().sendKeys(randomString);
    }


    private IButton getJsButton(String locatorName, String name) {
        return elementFactory.getButton(By.xpath(String.format(btnLocator, locatorName)), name);
    }

}
