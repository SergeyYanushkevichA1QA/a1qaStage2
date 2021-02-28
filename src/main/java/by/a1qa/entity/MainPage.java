package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import by.a1qa.service.StringGenerator;
import org.openqa.selenium.By;

public class MainPage {
    private static String randomString = StringGenerator.getAlphaNumericString();
    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private IButton highlighTxtButton = elementFactory.getButton(By.xpath("//div[contains(@role, 'button') and contains(text(), 'p')]"), "highligh Text Button");
    private IButton boldButton = elementFactory.getButton(By.xpath("//button[@title='Bold']"), "Bold button");
    private IButton boldButtonTxt = elementFactory.getButton(By.xpath("//div[contains(@role, 'button') and contains(text(), 'strong')]"), "bold button beneath frame");
    private ITextBox inputField = elementFactory.getTextBox(By.xpath("//body[@id='tinymce']/p"), "Input Field");


    private void switchToFrame() {
        AqualityServices.getLogger().info("Switching to frame");
        AqualityServices.getBrowser().getDriver().switchTo().frame("mce_0_ifr");
    }

    private void leaveFrame() {
        AqualityServices.getLogger().info("Leaving frame");
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }

    public void clearInput() {
        switchToFrame();
        AqualityServices.getLogger().info("Clear input in frame");
        inputField.clearAndType("");
        leaveFrame();
    }

    public void clickBoldButton() {
        boldButton.click();
    }

    public void highlightText() {
        highlighTxtButton.click();
    }
    public void inputRandomText() {
        switchToFrame();
        inputField.type(randomString);
        leaveFrame();
    }

    public String getInputTxt() {
        switchToFrame();
        String text = inputField.getText();
        leaveFrame();
        return text;
    }

    public static String getRandomString() {
        return randomString;
    }

    public boolean isBoldTxt() {
        return boldButtonTxt.getElement().isDisplayed();
    }

}
